#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define SERVER_IP "127.0.0.1"
#define MAX_FILENAME_SIZE 256

void receive_file(const char *filename, int sockfd) {
    FILE *file = fopen("received_file.txt", "wb");
    if (file == NULL) {
        perror("Error creating file");
        exit(EXIT_FAILURE);
    }

    char buffer[1024];
    int bytesRead;
    bytesRead = recv(sockfd, buffer, sizeof(buffer), 0);
    fwrite(buffer, 1, bytesRead, file);
    // while ((bytesRead = recv(sockfd, buffer, sizeof(buffer), 0)) > 0) {
    //     fwrite(buffer, 1, bytesRead, file);
    // }

    fclose(file);
}

int main() {
    int sockfd;
    struct sockaddr_in server_addr;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) exit(EXIT_FAILURE);

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);

    if (inet_pton(AF_INET, SERVER_IP, &server_addr.sin_addr) <= 0) exit(EXIT_FAILURE);

    if (connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) exit(EXIT_FAILURE);

    char filename[MAX_FILENAME_SIZE];
    memset(filename,'\0',MAX_FILENAME_SIZE);
    printf("Enter the filename to request: ");
    scanf("%s",filename);
    send(sockfd, filename, strlen(filename), 0);
    printf("Requesting file: %s\n", filename);
    receive_file(filename, sockfd);
    close(sockfd);
    printf("File received successfully.\n");
    return 0;
}
