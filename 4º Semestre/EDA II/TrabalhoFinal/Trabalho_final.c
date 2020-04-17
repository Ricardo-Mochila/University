#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"
#include "hashtableVoo.h"
#include "hashtableAero.h"

void inserirAeroporto()
{
    char aeroId[5];
    short hora = 0;
    short min = 0;

    struct aeroporto aero;
    NODE* node;
    init(&node);

    scanf("%s %hd:%hd ", aeroId, &hora, &min);

    strcpy(aero.identA, aeroId);
    aero.horaA = hora;
    aero.minA = min;
    
    if(searchAero(aeroId) != NULL)
        printf("+ aeroporto %s existe\n", aeroId);

    else
    {
        insertAero(aero, node);
        printf("+ novo aeroporto %s\n", aeroId);
    }    
}

void inserirVoo()
{
    char vooId[7];
    char aeroIdP[5];
    char aeroIdC[5];
    short hora = 0;
    short min = 0;
    int duracao = 0;

    scanf("%s %s %s %hd:%hd %d ", vooId, aeroIdP, aeroIdC, &hora, &min, &duracao);
    struct voo voo1;
    strcpy(voo1.identV, vooId);
    strcpy(voo1.aeroP, aeroIdP);
    strcpy(voo1.aeroC, aeroIdC);

    voo1.horaV = hora;
    voo1.minV = hora;
    voo1.dur = duracao;


    //struct voo_aero *vooProcura = searchVoo(vooId);
    struct trajecto *aeroP = searchAero(aeroIdP);
    struct trajecto *aeroC = searchAero(aeroIdC);

    

    if(searchVoo(vooId)!= NULL)
    {
        printf("+ voo %s existe\n", vooId);
    }
    else if(aeroP == NULL)
    {
        printf("+ aeroporto %s desconhecido\n", aeroIdP);
    }
    else if(aeroC == NULL)
    {
        printf("+ aeroporto %s desconhecido\n", aeroIdC);
    }
    else
    {
        if(aeroP->node == NULL)
        {
            searchAero(aeroIdP)->node = add(searchAero(aeroIdP)->node, voo1);
            insertVoo(voo1.identV, voo1.aeroP);
            printf("+ novo voo %s\n", vooId);
        }
        else if(searchList(aeroP->node, vooId) == NULL)
        {
            add_at(searchAero(aeroIdP)->node, voo1);
            insertVoo(voo1.identV, voo1.aeroP);
            printf("+ novo voo %s\n", vooId);
        }
        else
        {
            printf("+ voo %s existe\n", vooId);
        }
    }
}

void removerVoo()
{
    char vooId[7];
    char aeroId[5];
    scanf("%s ", vooId);
    
    struct voo_aero *voo = searchVoo(vooId);
    if(voo != NULL)
    {
        strcpy(aeroId,voo->aeroId);
        searchAero(aeroId)->node = remove_node(searchAero(aeroId)->node, vooId);
        deleteVoo(vooId);
        printf("+ voo %s removido\n",vooId);
    }
    else
    {
        printf("+ voo %s inexistente\n",vooId);
    }
}

void calcularViagem()
{
    char aeroP[5];
    char aeroC[5];
    short horaVg = 0;
    short minVg = 0;

    scanf("%s %s %hd:%hd ", aeroP, aeroC, &horaVg, &minVg);
    if(searchAero(aeroP)== NULL)
    {
        printf("+ aeroporto %s desconhecido\n", aeroP);
    }
    else if(searchAero(aeroC)== NULL)
    {
        printf("+ aeroporto %s desconhecido\n", aeroC);
    }
}

void diskWrite(FILE* ficheiro)
{
    char aeroId[5];
    short min; 
    short hor;
    char identV[7];
    short horaV;
    short minV;
    int dur;
    char aeroP[5];
    char aeroC[5];
    for(int i = 0; i < 400009; i++)
    {
        if(hashTableAero[i]!= NULL)
        {    
            strcpy(aeroId, hashTableAero[i]->aero.identA);
            hor = hashTableAero[i]->aero.horaA;
            min = hashTableAero[i]->aero.minA;
            fprintf(ficheiro, "AI %s %.2hd:%.2hd\n", aeroId, hor, min);
            
        }
    }

    for(int i = 0; i < 400009; i++)
    {
        if(hashTableAero[i]!= NULL)
        {    
            while(hashTableAero[i]-> node != NULL)
            {
                
                strcpy(identV, hashTableAero[i]->node->voo.identV);
                horaV = hashTableAero[i]->node->voo.horaV;
                minV = hashTableAero[i]->node->voo.minV;
                dur = hashTableAero[i]->node->voo.dur;
                strcpy(aeroP, hashTableAero[i]->node->voo.aeroP);
                strcpy(aeroC, hashTableAero[i]->node->voo.aeroC);
                fprintf(ficheiro, "FI %s %s %s %.2hd:%.2hd %d\n", identV, aeroP, aeroC, horaV, minV, dur);
                hashTableAero[i]-> node = hashTableAero[i]-> node->next;
            }
        }
    }
    
}

int main()
{
    FILE* ficheiro;
    ficheiro = fopen("file.txt", "r+");
    
    if(ficheiro == NULL)
    {
        ficheiro = fopen("file.txt", "w+");
    }
    
    char pos1;
    char pos2;

    while (fscanf(ficheiro, "%c%c", &pos1, &pos2) != EOF)
    {
        if(pos1 == 'A' && pos2 =='I')
            inserirAeroporto();

        if(pos1 == 'F' && pos2 =='I')
           inserirVoo();
        
        if(pos1 == 'F' && pos2 =='D')
            removerVoo();
        
        if(pos1 == 'T' && pos2 =='R')
            calcularViagem();
    }

    while (scanf("%c%c", &pos1, &pos2) != EOF)
    {
        if(pos1 == 'A' && pos2 =='I')
            inserirAeroporto();

        if(pos1 == 'F' && pos2 =='I')
           inserirVoo();
        
        if(pos1 == 'F' && pos2 =='D')
            removerVoo();
        
        if(pos1 == 'T' && pos2 =='R')
            calcularViagem();
    }

    diskWrite(ficheiro);

    return 0;
}