#include <stdio.h>
#include <stdlib.h>
#include "abp.h"



struct no *criar(int elem)
{
    struct no *novo = (struct no *)malloc(sizeof(struct no)); //aloca espaço em memória
    novo -> elemento = elem; //guarda o "novo" como elemento da struct
    novo -> esq = NULL;
    novo -> drt = NULL;
    return novo;
}

struct no *insere(int elemento, struct no *guarda)
{
    if(guarda == NULL)
    {
        guarda = criar(elemento);
    }
    else if(elemento > guarda -> elemento)
    {
        guarda -> drt = insere(elemento, guarda -> drt);
    }
    else if(elemento < guarda -> elemento)
    {
        guarda -> esq = insere(elemento, guarda -> esq);
    }
    return guarda;
}

struct no *minimo(struct no *min)
{
    if (min == NULL) 
    {
        return NULL;
    }
    else if(min -> esq == NULL )
    {
        return min; 
    }
    return minimo(min -> esq);
}


struct no *remover(int elemento, struct no *delete)
{
    if(delete == NULL)
    {
        return delete;
    }
    else if(elemento > delete -> elemento)
    {
        delete -> drt = remover(elemento, delete -> drt);
    }
    else if(elemento < delete -> elemento)
    {
        delete -> esq = remover(elemento, delete -> esq);
    }
    else if(delete -> esq != NULL && delete -> drt != NULL)
    {
        struct no *temporario = minimo(delete -> drt);
        delete -> elemento = temporario -> elemento;
        delete -> drt = remover(temporario -> elemento, delete -> drt);
    }
    else if(delete -> esq == NULL)
    {
        struct no *temporario = delete -> drt;
        free(delete);
        return temporario;
    }
    else
    {
        struct no *temporario = delete -> esq;
        free(delete);
        return temporario;
    }
    return delete;
}



void printEmOrdem(struct no *print)
{
    if(print != NULL)
    {
        printEmOrdem(print -> esq);
        printf("%d\n", print -> elemento);
        printEmOrdem(print -> drt);
    }
}

struct no *pesq(int elemento,struct no *pesquisa)
{
    
    if(pesquisa == NULL)
    {
        return NULL;
    }
    if(elemento < pesquisa -> elemento && pesquisa -> esq != NULL)
    {
        return pesq(elemento, pesquisa -> esq);
    }
    else if(elemento > pesquisa -> elemento && pesquisa -> drt != NULL)
    {
        return pesq(elemento, pesquisa -> drt);
    }
    else if(elemento != pesquisa -> elemento)
    {
        return NULL;
    }
    
    
    return pesquisa;   
}
