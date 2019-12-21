#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argr[])
{
    if(argc == 2)
    {
        printf("%s",argr[1]);
    }

    else{
        int a = atoi(argr[2]);
        for(int i = 0; i < a; i++)
        {
            printf("%s\n",argr[1]);
        }
    }
}