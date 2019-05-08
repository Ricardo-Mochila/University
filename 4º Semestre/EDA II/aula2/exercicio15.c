#include <stdio.h>
#define SIZE  50000

int criarArray(int n, int v[n])
{
    int c = 2;

    for(int i = 0; i < n; i++)
    {
        v[i] = c;
        c += 2;
    }
    
    return v[n];
}

int procura(int n, int s, int v[s])
{
    for(int i = 0; i < s; i++){
    
        if (v[i] == n)
            return i;
    }
    return -1;
}

int main(void)
{   
    int arr[SIZE];
    criarArray(SIZE, arr);
    for (int i = 1; i <= SIZE; ++i)
    {
        int p = procura(2 * i, SIZE, arr);

        if (p == -1)
            printf("Não encontrou %d\n", 2*i);
        else if(arr[p] != 2 * i)
            printf("Encontrou %d na posição errada: %d\n", 2*i, p);
           
    }
    
    return 0;
}