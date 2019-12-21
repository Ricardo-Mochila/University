#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>        

#define PORT 1300

void strdate(char *buffer, int len)
{
    time_t now = time(NULL);
    struct tm *ptm = localtime(&now);
    
    if (ptm == NULL) {
        
        puts("The localtime() function failed");
        return;
    }

    strftime(buffer, len, "%c\n", ptm);

}

int main(int argc, char const *argv[]) 
{ 
    int server_fd; 
    struct sockaddr_in address;
    struct sockaddr_in clientAddr;
    int clientSize;
    
    int opt = 1;      // for setsockopt() SO_REUSEADDR, below
    int addrlen = sizeof(address); 
    char buffer[256];

    strdate(buffer, 256);

    // Creating socket file descriptor 
    if ((server_fd = socket(AF_INET, SOCK_DGRAM, 0)) == 0) 
    { 
        perror("socket failed"); 
        exit(EXIT_FAILURE); 
    } 
       
    // Forcefully attaching socket to the port 1300 
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEPORT, &opt, sizeof(opt))) 
    { 
        perror("setsockopt failed"); 
        exit(EXIT_FAILURE); 
    } 
    address.sin_family = AF_INET; 
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT ); 

    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) 
    { 
        perror("bind failed"); 
        exit(EXIT_FAILURE); 
    } 
       
    //Creating a Udp functionality

    if(recvfrom(server_fd, buffer, 256, 0, (struct sockaddr *)&clientAddr, (socklen_t *)&clientSize ) < 0)
    {
        perror("receive failed");
        exit(EXIT_FAILURE);
    }

    printf("Client connected.\n");

    if(sendto(server_fd, buffer, strlen(buffer), 0, (struct sockaddr *)&clientAddr, clientSize) < 0)
    {
        perror("send failed");
        exit(EXIT_FAILURE);
    }
    //-------------- ** ---------------
    
   
    printf("Date sent to client\n");
    return 0; 
} 