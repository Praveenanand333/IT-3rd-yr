//FTP Server

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define SERVER_PORT 2121
#define BUFFER_SIZE 1024
#define FILE_PATH "files/"  // Directory containing files to share

void send_file(int client_socket, const char* filename) {
    char filepath[256];
    snprintf(filepath, sizeof(filepath), "%s%s", FILE_PATH, filename);
    
    FILE* file = fopen(filepath, "rb");
    if (file == NULL) {
        perror("File not found");
        return;
    }

    char buffer[BUFFER_SIZE];
    size_t bytes_read;

    while ((bytes_read = fread(buffer, 1, sizeof(buffer), file)) > 0) {
        send(client_socket, buffer, bytes_read, 0);
    }

    fclose(file);
}

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t addr_len = sizeof(client_addr);

    // Create socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }

    // Set up server address
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(SERVER_PORT);

    // Bind the socket
    if (bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) == -1) {
        perror("Socket binding failed");
        exit(EXIT_FAILURE);
    }

    // Listen for incoming connections
    if (listen(server_socket, 5) == -1) {
        perror("Socket listening failed");
        exit(EXIT_FAILURE);
    }

    printf("FTP server listening on port %d...\n", SERVER_PORT);

    while (1) {
        // Accept a client connection
        client_socket = accept(server_socket, (struct sockaddr*)&client_addr, &addr_len);
        if (client_socket == -1) {
            perror("Client connection failed");
            continue;
        }

        char request[BUFFER_SIZE];
        memset(request, 0, sizeof(request));

        // Receive the client's request
        recv(client_socket, request, sizeof(request), 0);
        printf("Received request: %s\n", request);

        // Extract the requested filename
        char* filename = strtok(request, " ");
        filename = strtok(NULL, " ");

        if (filename != NULL) {
            filename++;  // Remove the leading '/'
            send_file(client_socket, filename);
        }

        // Close the client socket
        close(client_socket);
    }

    // Close the server socket (this part of the code is unreachable in this example)
    close(server_socket);

    return 0;
}