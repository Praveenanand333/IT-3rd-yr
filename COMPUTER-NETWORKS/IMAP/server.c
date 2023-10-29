#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

// Define a structure to represent email messages
struct EmailMessage {
    char* from;
    char* subject;
    char* body;
};

// Sample mailbox with sample messages
struct EmailMessage mailbox[] = {
    {"sender1@example.com", "Hello", "This is the first message."},
    {"sender2@example.com", "Greetings", "This is the second message."},
};

// Function to simulate listing mailbox contents
void handleLIST(int client_socket) {
    char response[1024];
    response[0] = '\0'; // Initialize the response as an empty string

    // Iterate over the mailbox and concatenate 'from' parts to the response
    for (int i = 0; i < sizeof(mailbox) / sizeof(mailbox[0]); i++) {
        strcat(response, mailbox[i].from);
        strcat(response, "\n");
    }
    send(client_socket, response, strlen(response), 0);
}

// Function to simulate selecting a mailbox and sending a specific message
void handleSELECT(int client_socket, int message_number) {
    char response[1024];
    response[0] = '\0';
    strcat(response,mailbox[message_number].from);
    strcat(response, "\n");
    strcat(response,mailbox[message_number].subject);
    strcat(response, "\n");
    strcat(response,mailbox[message_number].body);
    send(client_socket, response, strlen(response), 0);
}



// Function to simulate deleting a message
void handleDELETE(int client_socket, int index) {
    char response[1024];
    int mailboxSize = sizeof(mailbox) / sizeof(mailbox[0]);
    if (index >= 0 && index < mailboxSize) {
        // Shift elements to remove the mailbox at the given index
        for (int i = index; i < mailboxSize - 1; i++) {
            mailbox[i] = mailbox[i + 1];
        }
        (mailboxSize)--; // Decrement the mailbox size
    }
    strcpy(response,"Mail deleted....\n");
    send(client_socket, response, strlen(response), 0);
}

int main() {
    int server_socket;
    int client_socket;
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Socket creation failed");
        exit(1);
    }

    // Bind the socket to an IP address and port
    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(8080); // Use the port you want
    server_address.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket, (struct sockaddr*)&server_address, sizeof(server_address)) == -1) {
        perror("Socket binding failed");
        exit(1);
    }

    // Listen for incoming connections
    if (listen(server_socket, 5) == -1) {  // Maximum 5 pending connections
        perror("Listening failed");
        exit(1);
    }

    // Main IMAP-like server loop
    while (1) {
        // Accept a client connection
        client_socket = accept(server_socket, NULL, NULL);

        char command[512];
        recv(client_socket, command, sizeof(command), 0);

        if (strstr(command, "LIST") != NULL) {
            handleLIST(client_socket);
        } else if (strstr(command, "SELECT") != NULL) {
            // Extract message number from the SELECT command, e.g., "SELECT 1"
            int message_number = -1;
            if (sscanf(command, "SELECT %d", &message_number) == 1) {
                handleSELECT(client_socket, message_number - 1); // Adjust for 0-based array
            }
        }  else if (strstr(command, "DELETE") != NULL) {
            // Extract message number from the DELETE command, e.g., "DELETE 1"
            int message_number = -1;
            if (sscanf(command, "DELETE %d", &message_number) == 1) {
                handleDELETE(client_socket, message_number - 1); // Adjust for 0-based array
            }
        } else if (strstr(command, "LOGOUT") != NULL) {
            send(client_socket, "* BYE IMAP server signing off\r\n", 30, 0);
            close(client_socket);
            break;
        }

        // Close the client socket when the session is finished
        close(client_socket);
    }

   close(server_socket);

    return 0;
}
