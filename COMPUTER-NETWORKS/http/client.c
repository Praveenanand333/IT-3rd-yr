//http client

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>

#define PORT 8081
#define BUFFER_SIZE 1024

int main(){
    int cli_sock;
    struct sockaddr_in serv_addr;
    char sbuf[BUFFER_SIZE],rbuf[BUFFER_SIZE];
    memset(rbuf,0,sizeof(rbuf));
    cli_sock=socket(AF_INET,SOCK_STREAM,0);
    serv_addr.sin_family=AF_INET;
    serv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    serv_addr.sin_port=htons(PORT);
    connect(cli_sock,(struct sockaddr *)&serv_addr,sizeof(serv_addr));
    char filename[BUFFER_SIZE];
    printf("Enter filename to search: ");
    fgets(filename,BUFFER_SIZE,stdin);
    strtok(filename,"\n");
    snprintf(sbuf,sizeof(sbuf),"GET /%s HTTP/1.1\r\nHOST: localhost\r\n\r\n",filename);
    send(cli_sock,sbuf,BUFFER_SIZE,0);
    recv(cli_sock,rbuf,BUFFER_SIZE,0);
    printf("Received message: \n%s\n",rbuf);
    close(cli_sock);
    return 0;
}
