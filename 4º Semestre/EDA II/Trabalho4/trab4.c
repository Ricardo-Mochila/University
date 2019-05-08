#include <stdio.h>
#include <string.h>

void printArray(char text[], int size)
{
    for (int i = 0; i < size; i++)
        printf("%c", text[i]);

    printf("\n");
}
//This function just prints to the console all the elements of an array;

void cleanArray(char text[], int size)
{
    for (int i = 0; i < size; i++)
        text[i] = '-';
}
//This function puts all the characters of an array as "-";

void textManipulation(char text[], int size)
{
    char temp[size];
    char final[size];
    int c = 0;
    int h = 0;

    for (int i = 0; i < size; i++)
    {
        temp[h] = text[i];
        for (int j = 0; j < h; j++)
        {
            if (text[i] == temp[j])
            {
                final[c] = temp[j];
                c++;
                cleanArray(temp, h);
                h = 0;
            }
        }
        h++;
    }
    printArray(final, c);
}
/* 
    This function recives as arguments an array of chars, and the size of that same array
    then it creates another two arrays, one for the outcome (final), and one to hold some 
    characters  (temp), then, in a for loop intruduces one character to the temp array, and the 
    cycles the (temp) array in another for loop if it finds a character in both send that same character 
    to the (final) array, then call the function cleanArray for the temp, now out of the secondary loop, it
    incremets the position of the temp array.
    Finaly out of the primary loop call the funtion printArray with final and c as a parameter
    */

int main(void)
{
    char text[1101];
    int j = 0;
    while (scanf("%[^\n]%*c", text) == 1)
    {
        j = strlen(text);
        textManipulation(text, j);
    }
    return 0;
}
/*
    The main function creates a character array (text) with 1101 positions, and a integer j
    the in a while cycle calls a scanf that will not stop until it is given an empty string,
    and saves the output of the scanf to the text array.
    Inside the while cycle it gets the size of the (text) through the fuction strlen, then calls
    the textManipulation function with text and j as parameters
    */
