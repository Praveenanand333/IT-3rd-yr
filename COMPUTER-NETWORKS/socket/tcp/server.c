#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#define BUFFER_SIZE 256
int main() {
    struct sockaddr_in server_addr, client_addr;
   int server_socket, client_socket;
   char sbuf[BUFFER_SIZE], rbuf[BUFFER_SIZE];
   server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Socket creation failed");
        exit(1);
    }
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(2000);  // Port number for server1
    server_addr.sin_addr.s_addr =INADDR_ANY; 
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        exit(1);
    }
    if (listen(server_socket, 5) == -1) {
        perror("Listening failed");
        exit(1);
    }
    printf("server listening...\n");
    while (1) {
        socklen_t addr_size = sizeof(client_addr);
        client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &addr_size);
        recv(client_socket, rbuf, BUFFER_SIZE, 0);
        printf("\nReceived request: %s\n", rbuf);
        strcpy(sbuf,"hello all");
        send(client_socket, sbuf, strlen(sbuf), 0);
        printf("Sent Response : %s\n", sbuf);
        close(client_socket);
}
}