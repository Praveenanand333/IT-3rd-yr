#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 12345
#define MAX_BUFFER_SIZE 1024

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_address, client_address;
    char buffer[MAX_BUFFER_SIZE];
    FILE *file;

    // Create socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Error creating socket");
        exit(EXIT_FAILURE);
    }

    // Set up server address
    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(PORT);

    // Bind the socket
    if (bind(server_socket, (struct sockaddr*)&server_address, sizeof(server_address)) == -1) {
        perror("Error binding socket");
        close(server_socket);
        exit(EXIT_FAILURE);
    }

    // Listen for incoming connections
    if (listen(server_socket, 5) == -1) {
        perror("Error listening for connections");
        close(server_socket);
        exit(EXIT_FAILURE);
    }

    printf("FTP Server listening on port %d...\n", PORT);

    while (1) {
        // Accept a connection
        socklen_t client_address_size = sizeof(client_address);
        client_socket = accept(server_socket, (struct sockaddr*)&client_address, &client_address_size);
        if (client_socket == -1) {
            perror("Error accepting connection");
            continue;
        }

        printf("Client connected: %s\n", inet_ntoa(client_address.sin_addr));

        // Receive filename from the client
        recv(client_socket, buffer, MAX_BUFFER_SIZE, 0);
        printf("Requested file: %s\n", buffer);

        // Open the requested file
        file = fopen(buffer, "rb");
        if (file == NULL) {
            // File not found, send error message to the client
            send(client_socket, "File not found", 15, 0);
            printf("File not found. Sent error message to client.\n");
        } else {
            // File found, send its contents to the client
            fseek(file, 0, SEEK_END);
            long file_size = ftell(file);
            fseek(file, 0, SEEK_SET);

            send(client_socket, "OK", 2, 0); // Signal that the file exists

            // Send file contents in chunks
            while (file_size > 0) {
                size_t chunk_size = fread(buffer, 1, MAX_BUFFER_SIZE, file);
                send(client_socket, buffer, chunk_size, 0);
                file_size -= chunk_size;
            }

            fclose(file);
            printf("File sent to client.\n");
        }

        // Close the client socket
        close(client_socket);
        printf("Client disconnected.\n");
    }

    // Close the server socket
    close(server_socket);

    return 0;
}
