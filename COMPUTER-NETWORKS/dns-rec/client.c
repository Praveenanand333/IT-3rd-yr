




#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define MAX_BUFFER_SIZE 1024

int main() {
    int client_socket;
    struct sockaddr_in server_addr;
    char buffer[MAX_BUFFER_SIZE];
    const char* server_ip = "127.0.0.1"; // Change this to the server's IP address
    const int server_port = 8080; // Change this to the server's port

    // Create a socket
    client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket < 0) {
        perror("Error in socket creation");
        exit(1);
    }

    printf("Client socket created successfully.\n");

    // Configure server address
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(server_port);
    server_addr.sin_addr.s_addr = inet_addr(server_ip);

    // Connect to the server
    if (connect(client_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        perror("Error in connection");
        exit(1);
    }

    printf("Connected to server %s:%d\n", server_ip, server_port);

    printf("Enter a hostname to resolve: ");
    fgets(buffer, sizeof(buffer), stdin);

    // Send the hostname to the server
    send(client_socket, buffer, strlen(buffer), 0);

    // Receive and display the resolved IP address
    ssize_t bytes_received = recv(client_socket, buffer, sizeof(buffer), 0);
    if (bytes_received > 0) {
        buffer[bytes_received] = '\0';
        printf("Resolved IP address: %s\n", buffer);
    } else {
        perror("Error receiving data from server");
    }

    close(client_socket);

    return 0;
}

	

