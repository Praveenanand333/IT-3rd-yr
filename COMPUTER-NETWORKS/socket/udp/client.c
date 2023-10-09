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

    socket_desc = socket(AF_INET, SOCK_DGRAM, 0); // Use SOCK_DGRAM for UDP
    if (socket_desc == -1) {
        perror("Socket creation failed");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(2000);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    while(1){
    printf("Enter the message: ");
    scanf("%s", sbuf);

    socklen_t server_addr_len = sizeof(server_addr);
    sendto(socket_desc, sbuf, BUFFER_SIZE, 0, (struct sockaddr *)&server_addr, server_addr_len);
    recvfrom(socket_desc, rbuf, BUFFER_SIZE, 0, (struct sockaddr *)&server_addr, &server_addr_len);
    printf("Server's response: %s\n", rbuf);
}
    close(socket_desc);

    return 0;
}
