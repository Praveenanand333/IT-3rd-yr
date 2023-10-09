#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>
#define BUFFER_SIZE 1024
int main(){
    int client_socket;
    char rbuf[BUFFER_SIZE],sbuf[BUFFER_SIZE];
    client_socket=socket(AF_INET,SOCK_STREAM,0);
    struct sockaddr_in serv_addr;
    serv_addr.sin_family=AF_INET;
    serv_addr.sin_port=htons(2000);
    serv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    connect(client_socket,(struct sockaddr *)&serv_addr,sizeof(serv_addr));
    while(1){
    printf("Enter the message:");
    scanf("%s",sbuf);
    send(client_socket,sbuf,strlen(sbuf),0);
    recv(client_socket,rbuf,BUFFER_SIZE,0);
    printf("servers response:%s\n",rbuf);
    memset(rbuf, '\0', BUFFER_SIZE);
    memset(sbuf, '\0', BUFFER_SIZE);
    }
    close(client_socket);
    return 0;
}
