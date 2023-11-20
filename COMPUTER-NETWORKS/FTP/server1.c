#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 12345
#define MAX_BUFFER_SIZE 1024

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_address, client_address;
    char buffer[MAX_BUFFER_SIZE];
    FILE *file;

    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(PORT);

    bind(server_socket, (struct sockaddr*)&server_address, sizeof(server_address));
    listen(server_socket, 5);

    printf("FTP Server listening on port %d...\n", PORT);

    while (1) {
        socklen_t client_address_size = sizeof(client_address);
        client_socket = accept(server_socket, (struct sockaddr*)&client_address, &client_address_size);

        printf("Client connected: %s\n", inet_ntoa(client_address.sin_addr));

        recv(client_socket, buffer, MAX_BUFFER_SIZE, 0);
        printf("Requested file: %s\n", buffer);

        file = fopen(buffer, "rb");
        if (file == NULL) {
            send(client_socket, "File not found", 15, 0);
            printf("File not found. Sent error message to client.\n");
        } else {
            fseek(file, 0, SEEK_END);
            long file_size = ftell(file);
            fseek(file, 0, SEEK_SET);

            send(client_socket, "OK", 2, 0);

            while (file_size > 0) {
                size_t chunk_size = fread(buffer, 1, MAX_BUFFER_SIZE, file);
                send(client_socket, buffer, chunk_size, 0);
                file_size -= chunk_size;
            }

            fclose(file);
            printf("File sent to client.\n");
        }

        close(client_socket);
        printf("Client disconnected.\n");
    }

    close(server_socket);

    return 0;
}
