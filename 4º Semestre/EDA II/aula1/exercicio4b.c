#include <stdio.h>

int factorial(int num){

    if(num-1 <= 0)
    {
        return 1;
    }
    else
    {
        return num * factorial(num-1);
    }

}

int main(void)
{   
    printf("%d\n", factorial(10));
    printf("%d\n", factorial(20));
    printf("%d\n", factorial(21));
    return 0;
}