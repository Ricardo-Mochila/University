#include <stdio.h>


int fibonacciRec(int n)
{   
    if(n < 2){
        return n;
    }   
    return fibonacciRec(n-1) + fibonacciRec(n-2);

}

int main(void)
{   
    int n = 0; 
    while(n < 20){
        printf("%d ",fibonacciRec(n));
        n++;
    }
    printf("%d\n",fibonacciRec(n));
    return 0;
}