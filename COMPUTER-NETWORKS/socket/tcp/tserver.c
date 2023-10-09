#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>
#define BUFFER_SIZE 1024
int main(){
    int server_socket,client_socket;
     char sbuf[BUFFER_SIZE], rbuf[BUFFER_SIZE];
    server_socket=socket(AF_INET,SOCK_STREAM,0);
    struct sockaddr_in server_addr,client_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(2000);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr));
    listen(server_socket, 5);
    printf("server listening...\n");
      while (1) {
        socklen_t addr_size = sizeof(client_addr);
        client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &addr_size);
        recv(client_socket, rbuf, BUFFER_SIZE, 0);
        printf("\nReceived message: %s\n", rbuf);
        printf("Enter the message:");
        scanf("%s",sbuf);
        send(client_socket, sbuf, strlen(sbuf), 0);
        printf("Sent Response : %s\n", sbuf);
        close(client_socket);
}
return 0;
}