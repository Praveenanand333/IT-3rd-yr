#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#define BUFFER_SIZE 256

int main() {
    struct sockaddr_in server_addr, client_addr;
    int server_socket;
    char sbuf[BUFFER_SIZE], rbuf[BUFFER_SIZE];

    server_socket = socket(AF_INET, SOCK_DGRAM, 0); // Use SOCK_DGRAM for UDP
    if (server_socket == -1) {
        perror("Socket creation failed");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(2000);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        exit(1);
    }

    printf("Server listening...\n");

    while (1) {
        memset(rbuf, '\0', sizeof(rbuf));
        socklen_t client_addr_len = sizeof(client_addr);

        ssize_t received_bytes = recvfrom(server_socket, rbuf, BUFFER_SIZE, 0, (struct sockaddr *)&client_addr, &client_addr_len);
        if (received_bytes < 0) {
            perror("Receive failed");
            exit(1);
        }

        printf("Received request: %s\n", rbuf);
        printf("Enter the message:");
        scanf("%s",sbuf);
        sendto(server_socket, sbuf, strlen(sbuf), 0, (struct sockaddr *)&client_addr, client_addr_len);
        printf("Sent Response: %s\n", sbuf);
    }

    close(server_socket);

    return 0;
}