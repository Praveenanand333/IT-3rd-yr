#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#define BUFFER_SIZE 256
int main() {
    char sbuf[BUFFER_SIZE], rbuf[BUFFER_SIZE];
    int socket_desc;
    struct sockaddr_in server_addr;
    socket_desc = socket(AF_INET, SOCK_STREAM, 0);
    if (socket_desc == -1) {
        perror("Socket creation failed");
        exit(1);
    }
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(2000);
    server_addr.sin_addr.s_addr = inet_addr("172.19.194.80");
    if (connect(socket_desc, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Connection failed");
    }
    printf("Enter the message: ");
    scanf("%s", sbuf);
    send(socket_desc, sbuf, BUFFER_SIZE, 0);
    recv(socket_desc, rbuf, BUFFER_SIZE, 0);
    printf("Servers  response: %s\n", rbuf);
    return 0;
}