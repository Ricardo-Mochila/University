#include <stdio.h>

int counter(int number)
{
    //Function that counts the number of digits in one integer

    int size = 0;

    if(number == 0)     //Returns 1 if the parameter from function is 0
    {
        return 1;
    }

    while(number != 0)              //Counts the number of times it takes to make the parameter, number, zero
    {                               //and saves it to the variable size
        number = number / 10;
        size++;
    }

    return size;
}


int main(void)
{
    int num;
    scanf("%d", &num);                  //Integer from input saved in num

    printf("%d\n", counter(num));       //Prints to console how many digits are in Integer num
    return 0;
}