#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <sys/socket.h>
#include <netinet/ip_icmp.h>
#include <netinet/in.h>
#include <time.h>

#define PACKET_SIZE 64
#define MAX_HOPS 30

// Checksum calculation for ICMP packet
unsigned short calculate_checksum(unsigned short *ptr, int length) {
    unsigned long sum = 0;
    unsigned short checksum;

    while (length > 1) {
        sum += *ptr++;
        length -= 2;
    }

    if (length == 1) {
        sum += *(unsigned char *)ptr;
    }

    sum = (sum >> 16) + (sum & 0xFFFF);
    sum += (sum >> 16);
    checksum = ~sum;

    return checksum;
}

// Send an ICMP echo Request packet
void send_ping(int sockfd, struct sockaddr *dest_addr, int seq) {
    char packet[PACKET_SIZE];
    struct icmphdr *icmp_header = (struct icmphdr *)packet;

    memset(packet, 0, PACKET_SIZE);

    // Set up the ICMP header
    icmp_header->type = ICMP_ECHO;
    icmp_header->code = 0;
    icmp_header->un.echo.id = getpid();
    icmp_header->un.echo.sequence = seq;
    icmp_header->checksum = calculate_checksum((unsigned short *)icmp_header, sizeof(struct icmphdr));

    // Send the ICMP packet
    sendto(sockfd, packet, sizeof(struct icmphdr), 0, dest_addr, sizeof(struct sockaddr));
}

// Receive and process an ICMP Echo Reply
void receive_ping(int sockfd, int seq) {
    char packet[PACKET_SIZE];
    struct sockaddr_in sender_addr;
    socklen_t sender_addr_len = sizeof(sender_addr);

    if (recvfrom(sockfd, packet, PACKET_SIZE, 0, (struct sockaddr *)&sender_addr, &sender_addr_len) < 0) {
        perror("Error receiving packet");
        return;
    }

    // Calculate and print the round-trip time (RTT)
    struct icmphdr *icmp_header = (struct icmphdr *)packet;
    struct timespec send_time;
    memcpy(&send_time, packet + sizeof(struct icmphdr), sizeof(struct timespec));

    struct timespec recv_time;
    clock_gettime(CLOCK_MONOTONIC, &recv_time);

    long rtt = (recv_time.tv_sec - send_time.tv_sec) * 1000 + (recv_time.tv_nsec - send_time.tv_nsec) / 1000000;

    printf("%d bytes from %s: icmp_seq=%d time=%ldms\n",
           PACKET_SIZE, inet_ntoa(sender_addr.sin_addr), seq, rtt);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <hostname or IP address>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    struct addrinfo hints, *res;
    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_INET;

    if (getaddrinfo(argv[1], NULL, &hints, &res) != 0) {
        perror("Error in getaddrinfo");
        exit(EXIT_FAILURE);
    }

    struct sockaddr_in *dest_addr = (struct sockaddr_in *)res->ai_addr;

    int sockfd = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
    if (sockfd < 0) {
        perror("Error in socket");
        exit(EXIT_FAILURE);
    }

    int seq;
    for (seq = 1; seq <= MAX_HOPS; ++seq) {
        send_ping(sockfd, (struct sockaddr *)dest_addr, seq);
        receive_ping(sockfd, seq);
        sleep(1); // Wait before sending the next ICMP packet
    }

    close(sockfd);
    freeaddrinfo(res);

    return 0;
}
