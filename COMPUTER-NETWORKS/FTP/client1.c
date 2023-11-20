#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 12345
#define MAX_BUFFER_SIZE 1024

int main() {
    int client_socket;
    struct sockaddr_in server_address;
    char buffer[MAX_BUFFER_SIZE];
    FILE *file;

    client_socket = socket(AF_INET, SOCK_STREAM, 0);
    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(PORT);
    server_address.sin_addr.s_addr = inet_addr("127.0.0.1");

    connect(client_socket, (struct sockaddr*)&server_address, sizeof(server_address));

    printf("Enter the filename to request: ");
    fgets(buffer, MAX_BUFFER_SIZE, stdin);
    buffer[strcspn(buffer, "\n")] = '\0';

    send(client_socket, buffer, strlen(buffer), 0);
    recv(client_socket, buffer, MAX_BUFFER_SIZE, 0);

    if (strcmp(buffer, "File not found") == 0) {
        printf("File not found on the server.\n");
    } else {
        file = fopen("received_file", "wb");

        while (1) {
            ssize_t bytes_received = recv(client_socket, buffer, MAX_BUFFER_SIZE, 0);
            if (bytes_received <= 0) {
                break;
            }
            fwrite(buffer, 1, bytes_received, file);
        }

        fclose(file);
        printf("File received from the server.\n");
    }

    close(client_socket);

    return 0;
}
