#include <stdio.h>

int max(int a, int b)
{
  return a > b ? a : b;
}

int min(int a, int b)
{
  return a < b ? a : b;
}

int belaTurn(int size, int arr[size], int start, int end, int play)
{
    int maximum = max(arr[start], arr[end]);
    int minimum = min(arr[start], arr[end]);

    if(play%2 == 0)
    {
        return minimum;
    }
    return maximum;
    
}

void output(int a, int b)
{
    if(a > b)
    {
        printf("Alex ganha com %d contra %d\n", a, b);
    }
    else if (b > a)
    {
        printf("Bela ganha com %d contra %d\n", b, a);
    }
    else
    {
        printf("Alex e Bela empatam a %d\n", a);
    }
}


int game(int size, int arr[size], int start, int end, int alex, int bela, int play)
{   

    if (start <= end)
    {
        

        int alexT = max(arr[start], arr[end]);
        alex += alexT;

        if(alexT == arr[start])
        {
            start += 1;
        }
        else
        {
            end -=1;
        }

        int belaT = belaTurn(size, arr, start, end, play);
        play += 1;
        bela += belaT;

        if(belaT == arr[start])
        {
            start += 1;
        }
        else
        {
            end -=1;
        }

        return game(size, arr, start, end, alex, bela, play);
    }
    output(alex, bela);
    return 0; 
}



int main(void)
{   
    int size = 0;
    int alex = 0;
    int bela = 0;
    
    scanf("%d", &size);
    int array[size];
 
    for(int i = 0; i < size; i++)
    {
        scanf("%d", &array[i]);
        
    }   

    game(size, array, 0, size-1, alex, bela, 0);
    
    return 0;
}