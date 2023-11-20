#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define MAX_FILENAME_SIZE 256

void send_file(const char *filename, int sockfd) {
    FILE *file = fopen(filename, "rb");
    if (file == NULL) {
        perror("File not found");
        return;
    }

    char buffer[1024];
    size_t bytesRead;
    bytesRead = fread(buffer, 1, sizeof(buffer), file);
    send(sockfd, buffer, bytesRead, 0);
    // while ((bytesRead = fread(buffer, 1, sizeof(buffer), file)) > 0) {
    //     if (send(sockfd, buffer, bytesRead, 0) < 0) {
    //         perror("Error sending file");
    //         fclose(file);
    //         exit(EXIT_FAILURE);
    //     }
    // }

    fclose(file);
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

    printf("FTP Server listening on port %d...\n", PORT);

    while (1) {
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) exit(EXIT_FAILURE);

        char filename[MAX_FILENAME_SIZE];
        memset(filename,'\0',MAX_FILENAME_SIZE);
        if (recv(new_socket, filename, sizeof(filename), 0) <= 0) {
            perror("Error receiving filename");
            close(new_socket);
            continue;
        }
        printf("File request received: %s\n", filename);
 char cwd[1024];
 getcwd(cwd, sizeof(cwd));
  printf("Current working directory: %s\n", cwd);
        send_file(filename, new_socket);

        close(new_socket);
    }
    return 0;
}
