//http server

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>

#define PORT 8081
#define BUFFER_SIZE 1024

void send_response(int cli_sock,int status,const char *content){
    char sbuf[BUFFER_SIZE];
    memset(sbuf,0,sizeof(sbuf));
    const char *statusmsg="OK";
    if(status==404){
        statusmsg="Not Found";
    }
    snprintf(sbuf,sizeof(sbuf),
        "HTTP/1.1 %d %s\r\n"
        "Content-Length: %zu\r\n"
        "Content-Type: text/plain\r\n\r\n%s",
        status,statusmsg,strlen(content),content);
    send(cli_sock,sbuf,strlen(sbuf),0);
}

int main(){
    int serv_sock,cli_sock;
    struct sockaddr_in serv_addr,cli_addr;
    socklen_t addrlen=sizeof(cli_addr);
    char rbuf[BUFFER_SIZE];
    serv_sock=socket(AF_INET,SOCK_STREAM,0);
    serv_addr.sin_family=AF_INET;
    serv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    serv_addr.sin_port=htons(PORT);
    bind(serv_sock,(struct sockaddr *)&serv_addr,sizeof(serv_addr));
    listen(serv_sock,5);
    printf("Server listening on port %d\n",PORT);
    while(1){
        cli_sock=accept(serv_sock,(struct sockaddr *)&cli_addr,&addrlen);
        memset(rbuf,0,sizeof(rbuf));
        recv(cli_sock,rbuf,sizeof(rbuf),0);
        printf("Received message: \n%s\n",rbuf);
        char file[BUFFER_SIZE];
        if(sscanf(rbuf,"GET /%s HTTP/1.1",file)==1){
            FILE *f=fopen(file,"r");
            if(f!=NULL){
                char content[BUFFER_SIZE];
                fread(content,1,sizeof(content),f);
                fclose(f);

                send_response(cli_sock,200,content);
            }
            else{
                send_response(cli_sock,404,"File not found");
            }
        }
        else{
            send_response(cli_sock,400,"Bad Request");
        }
        close(cli_sock);
    }
    close(serv_sock);
    return 0;
}
