//dnsserver.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>

#define PORT 3000
#define BUFFER_SIZE 1024

int main(){
    FILE *fp;
    int serv_sock;
    struct sockaddr_in serv_addr,cli_addr;
    socklen_t addrlen=sizeof(cli_addr);
    char sbuf[BUFFER_SIZE],rbuf[BUFFER_SIZE],a[BUFFER_SIZE];

    serv_sock=socket(AF_INET,SOCK_DGRAM,0);

    serv_addr.sin_family=AF_INET;
    serv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    serv_addr.sin_port=htons(PORT);

    bind(serv_sock,(struct sockaddr *)&serv_addr,sizeof(serv_addr));

    while(1){
        strcpy(sbuf,"");
        fp=fopen("dns.txt","r");
        recvfrom(serv_sock,rbuf,sizeof(rbuf),0,(struct sockaddr *)&cli_addr,&addrlen);
        printf("Received response: %s\n",rbuf);
        while(!feof(fp)){
            fscanf(fp,"%s",a);
            if(strcmp(a,rbuf)==0){
                fscanf(fp,"%s",sbuf);
                break;
            }
        }
        if(strcmp(sbuf,"")==0){
            strcpy(sbuf,"Not found");
        }
        fclose(fp);
        sendto(serv_sock,sbuf,sizeof(sbuf),0,(struct sockaddr *)&cli_addr,addrlen);
    }
    close(serv_sock);

    return 0;
}