#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define OBJECT_FILE "objects.txt"

void send_object(FILE *fp, int sockfd) {
    char buff[1024];
    int n;

    while ((n = fread(buff, 1, sizeof(buff), fp)) > 0) {
        if (send(sockfd, buff, n, 0) < 0) {
            perror("Error sending file");
            exit(EXIT_FAILURE);
        }
    }
}

int main() {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int addrlen = sizeof(address);

    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) exit(EXIT_FAILURE);
    
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) exit(EXIT_FAILURE);

    if (listen(server_fd, 3) < 0) exit(EXIT_FAILURE);

    printf("Server listening on port %d...\n", PORT);

    while (1) {
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) exit(EXIT_FAILURE);

        FILE *fp = fopen(OBJECT_FILE, "rb");
        if (fp == NULL) exit(EXIT_FAILURE);

        send_object(fp, new_socket);

        fclose(fp);
        close(new_socket);
        printf("Connection closed\n");
    }

    return 0;
}
