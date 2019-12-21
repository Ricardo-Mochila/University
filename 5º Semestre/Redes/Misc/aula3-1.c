#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <time.h>


int main(int argc, char* argv[])
{
    if(argc < 2)
    {
        printf("falta o argumento: hostname");
        exit(1);
    }
    struct hostent *shent = gethostbyname(argv[1]);

    extern char *tzname[2];
    time_t tsl;
    time(&tsl);
    char *tinfo= ctime(&tsl);
    printf("%s\n", tinfo);

    if(!shent)
    {
        herror("gethostbyname");
        exit(1);
    }
    
    printf("The IP address is %s\n", inet_ntoa(*((struct in_addr*) shent -> h_addr_list[0])));
    
    return 0;


}