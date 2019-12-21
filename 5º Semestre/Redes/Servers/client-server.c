#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>

#include <string.h>

int main(int argc, char *argv[]) {
   int sockfd, portno, n;
   struct sockaddr_in serv_addr;
   struct hostent *server;
   int serverSize = sizeof(serv_addr);
   
   char buffer[256];   
   portno = 1300;
   
   /* Create a socket point */
   sockfd = socket(AF_INET, SOCK_DGRAM, 0);
   if (sockfd < 0) {
      perror("ERROR opening socket");
      exit(1);
   }
	
   server = gethostbyname("localhost"); 
   if (server == NULL) { 
     fprintf(stderr,"ERROR, no such host\n");
      exit(1);
   }

   bzero((char *) &serv_addr, sizeof(serv_addr));
   serv_addr.sin_family = AF_INET;
   bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
   serv_addr.sin_port = htons(portno);

   strcpy(buffer, "Coisas");
   
   if (sendto(sockfd, buffer, strlen(buffer), 0, (struct sockaddr *)&serv_addr, serverSize) < 0) {
      perror("ERROR connecting");
      exit(2);
   }

   if(recvfrom(sockfd, buffer, 256, 0, (struct sockaddr *)&serv_addr, (socklen_t *)&serverSize ) < 0)
   {
        perror("receive failed");
        exit(EXIT_FAILURE);
   }
   
   /* Now read server response */
   printf("%s", buffer);
   
   if (n < 0) {
     perror("ERROR reading from socket");
     exit(3);
   }
     
   printf("%s\n",buffer);

   return 0;
}