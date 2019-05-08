#include <stdio.h>

int factorialIterativamente(int num)
{
    int sum = 1;
    for(int i = 2; i <= num; i++){
        sum = sum * i;

    }
    return sum;
}



int main(void)
{
    printf("%d \n",factorialIterativamente(10));
    printf("%d \n",factorialIterativamente(20));
    printf("%d \n",factorialIterativamente(21));
    return 0;
}