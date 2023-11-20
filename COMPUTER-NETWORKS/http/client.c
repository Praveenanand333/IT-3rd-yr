#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define SERVER_IP "127.0.0.1"

void receive_objects(int sockfd) {
    char buff[1024];
    int n;

    while ((n = recv(sockfd, buff, sizeof(buff), 0)) > 0) {
        fwrite(buff, 1, n, stdout);
    }
}

int main() {
    int sockfd;
    struct sockaddr_in server_addr;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) exit(EXIT_FAILURE);

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);

    if (inet_pton(AF_INET, SERVER_IP, &server_addr.sin_addr) <= 0) exit(EXIT_FAILURE);

    if (connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) exit(EXIT_FAILURE);

    printf("Connected to server at %s:%d\n", SERVER_IP, PORT);

    receive_objects(sockfd);

    close(sockfd);

    return 0;
}
