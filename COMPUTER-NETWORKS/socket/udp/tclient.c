#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>
#define BUFFER_SIZE 1024
int main(){
    char rbuf[BUFFER_SIZE],sbuf[BUFFER_SIZE];
    int client_socket;
    client_socket=socket(AF_INET,SOCK_DGRAM,0);
    struct sockaddr_in serv_addr;
    serv_addr.sin_family=AF_INET;
    serv_addr.sin_port=htons(2000);
    serv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    while(1){
        printf("Enter the message:");
        scanf("%s",sbuf);
        socklen_t addr_len=sizeof(serv_addr);
        sendto(client_socket,sbuf,BUFFER_SIZE,0,(struct sockaddr *)&serv_addr,addr_len);
       recvfrom(client_socket, rbuf, BUFFER_SIZE, 0, (struct sockaddr *)&serv_addr, &addr_len);
       printf("Servers message:%s",rbuf);
    }
    close(client_socket);
    return 0;
}