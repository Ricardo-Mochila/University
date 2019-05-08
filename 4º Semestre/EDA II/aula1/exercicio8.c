#include <stdio.h>

void triangle(int n){

    int j;
    int i;

    for(i = 1; i <= n; i++){
        for (j = 1; j < i; j++){
            printf("%4d", j*i);
        }
        printf("%4d\n", i*j);
    }
}

int main(void)
{
    triangle(10);
    return 0;
}