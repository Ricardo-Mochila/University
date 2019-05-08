#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define PO_CH_SIZE 56
#define ID_SIZE 7
#define NUMBER_SIZE 10


struct contacts
{
    char id[ID_SIZE];
    char number1[NUMBER_SIZE];
    char number2[NUMBER_SIZE];
    int income;
    char political_orientation[PO_CH_SIZE];
    char consumer_habits[PO_CH_SIZE];
};

void addToFile(FILE *file)
{
    struct contacts cont = {};
    char id[ID_SIZE];
    char number1[NUMBER_SIZE];
    char number2[NUMBER_SIZE];
    int income = 0;
    char political_orientation[PO_CH_SIZE]  ;
    char consumer_habits[PO_CH_SIZE];

    scanf(" %s\n%s %s\n%d\n%[^\n]\n%[^\n]", id,number1,number2,&income,political_orientation,consumer_habits);

    strcpy(cont.id, id);
    strcpy(cont.number1, number1);
    strcpy(cont.number2, number2);
    cont.income = income;
    strcpy(cont.political_orientation, political_orientation);
    strcpy(cont.consumer_habits, consumer_habits);
  
    int num = atoi(id);
    fseek(file,num * sizeof(struct contacts),SEEK_SET);
    fwrite(&cont, sizeof(struct contacts),1, file);
}

void search(FILE *file)
{
    struct contacts cont;
    char idToFind[ID_SIZE];
    
    
    scanf(" %s", idToFind);
    int num = atoi(idToFind);
    fseek(file, num * sizeof(struct contacts), SEEK_SET);
    fread(&cont, sizeof(struct contacts), 1, file);

    int found = atoi(cont.id);
    if(found == num)
    {
        if(cont.number1[0] == '0')
        {
            if(cont.number2[0] == '0')
             printf("+ SUJEITO %s\n0 0\n%d\n%s\n%s\n", cont.id,cont.income,cont.political_orientation,cont.consumer_habits);
            else
                printf("+ SUJEITO %s\n0 %s\n%d\n%s\n%s\n", cont.id,cont.number2,cont.income,cont.political_orientation,cont.consumer_habits);
    
        }
        else if (cont.number2[0] == '0')
            printf("+ SUJEITO %s\n%s 0\n%d\n%s\n%s\n", cont.id,cont.number1,cont.income,cont.political_orientation,cont.consumer_habits);
        
        else
            printf("+ SUJEITO %s\n%s %s\n%d\n%s\n%s\n", cont.id,cont.number1,cont.number2,cont.income,cont.political_orientation,cont.consumer_habits);
    }
    else
        printf("+ SUJEITO %s desconhecido\n", idToFind);
    
}

void removeFromFile(FILE *file)
{
    struct contacts cont;
    struct contacts cont1 = {};
    char idToRemove[ID_SIZE];
    
    scanf(" %s", idToRemove);
    int num = atoi(idToRemove);

    fseek(file, num * sizeof(struct contacts), SEEK_SET);
    fread(&cont, sizeof(struct contacts), 1, file);

    int found = atoi(cont.id);
    
    if(found == num)
    {

        fseek(file, num * sizeof(struct contacts), SEEK_SET);
        fwrite(&cont1, sizeof(struct contacts),1, file);
    }
    else
        printf("+ SUJEITO %s desconhecido\n", idToRemove);
}
    

int main(void)
{

    char operator;

    FILE *file;
    file = fopen("trab7_data.txt", "r+");

    if(file == NULL)
    {
        file = fopen("trab7_data.txt", "w+");
    }
    while (scanf(" %c", &operator) != EOF)
    {
        switch (operator)
        {
        case '+':
            addToFile(file);
            break;

        case '-':
            removeFromFile(file);
            break;

        case '?':
            search(file);
            break;

        default:
            break;
        }
    }

    fclose(file);

    return 0;
}