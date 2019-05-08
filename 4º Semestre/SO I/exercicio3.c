#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int forkTest(int n)
{
    int temp = 1;
    pid_t pid;
    pid = fork();

    while(pid != 0)
    {
        temp= temp * n;
        n= n-1;
        if (n == 1)
        {
            printf("O resultado é %d\n",temp);
            return 0;
        }
    }
    return 0;
   
    
}


int forkRecursive(int n)
{
    int temp = 1;
    pid_t pid;

    while(n > 1){
        pid = fork();
        if(pid == 0){
            temp *= n;
            n -=1;
        }
        if (pid > 0 ){
            return 1;
        }

        
    }

    printf("o resultado é %d\n", temp);
    return 0;

}

int main(void)
{
    forkRecursive(4);
    return 0;

}
