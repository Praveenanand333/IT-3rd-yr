
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define MAX_CLIENTS 5
#define BUFFER_SIZE 1024

void handle_client(int client_socket);

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t client_addr_len = sizeof(client_addr);

    // Create socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Error creating server socket");
        exit(EXIT_FAILURE);
    }
    printf("\nSocket created \n");

    // Set server address configuration
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_addr.sin_port = htons(5666); // You can choose any available port

    // Bind the socket
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Error binding server socket");
        exit(EXIT_FAILURE);
    }
    printf("Bind\n");

    // Listen for incoming connections
    if (listen(server_socket, MAX_CLIENTS) == -1) {
        perror("Error listening for connections");
        exit(EXIT_FAILURE);
    }

    printf("Server listening on port 8080...\n");

    while (1) {
        // Accept a client connection
        client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &client_addr_len);
        if (client_socket == -1) {
            perror("Error accepting client connection");
            continue; // Continue and wait for the next connection
        }

        printf("New connection from %s:%d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));

        // Create a new process to handle the client
        pid_t pid = fork();
        if (pid == 0) {
            // In child process, close the server socket and handle the client
            close(server_socket);
            handle_client(client_socket);
            close(client_socket);
            exit(EXIT_SUCCESS);
        } else if (pid < 0) {
            perror("Error forking process");
            close(client_socket);
        } else {
            // In the parent process, close the client socket
            close(client_socket);
        }
    }

    close(server_socket);

    return 0;
}

void handle_client(int client_socket) {
    char buffer[BUFFER_SIZE];
    int bytes_received;

    while (1) {
        memset(buffer, 0, sizeof(buffer));

        // Receive data from the client
        bytes_received = recv(client_socket, buffer, sizeof(buffer), 0);
        if (bytes_received <= 0) {
            // Error or client disconnected
            break;
        }

        printf("%s", buffer);

        // Echo back to the client
        send(client_socket, buffer, bytes_received, 0);
    }
}
