#include <stdio.h>
#include <stdbool.h>
#include "programa.h"
#include "queue.h"
#include <string.h>


#define QUANTUM 4       //Limitador do numero de ciclos que um processo pode estar em estado RUN
#define NEW 0           //Indentificador do estado NEW
#define WAIT 1          //Indentificador do estado WAIT
#define RUN 2           //Indentificador do estado RUN
#define BLOCK 3         //Indentificador do estado BLOCK
#define EXIT 4          //Indentificador do estado closef

//Variaveis globais
int matrix[30][300];        //Mapa dos processo (Copia do input)
int nextPid=0;              //Variavel global para sabermos o numero do pid a dar a um novo processo.
int clockCycle=0;           //Contador dos ciclos
int cont = 0;

FILE *ficheiro;             //Disco, onde guardamos os valores escritos pela intrução codificado pelo numero 8
int runCounter = 0;         //Este contador é usado para garantir que um processo nao fica mais do que 4 ciclos no estado RUN

int blocktoWait = 0;            //Garante que não passamos do block para Wait e para o Run num unico ciclo
bool flagBlock = false;         //Flag para garantir que a intrução de acesso a disco só é finalizada um ciclo depois de ser decodificada
bool flagExit = false;          //Flag para garantir que a intrução exit só é finalizada um ciclo depois de ser decodificada


struct program newQueue[300];       //Queue do NEW
int newQueueSize = 0;
struct program waitQueue[300];      //Queue do WAIT
int waitQueueSize = 0;
struct program blockQueue[300];     //Queue do BLOCK
int blockQueueSize = 0;
struct program inRun;               //Variavel que indica qual o processo que está em estado RUN (Ganhando acesso direto ao mesmo sem termos de estar a perder tempo a procurar)
struct program toPrint[30];         //Estrutura de armazenamento de todos programas que foram excecutados para fazer print dos estados em que cada um está
struct program exitProcess;


//Guarda o processo que chegou ao exit para que da proxima vez que chamamos o exit possamos alterar o estado do processo que la estava antes

//Modelo de 5 estados daqui para baixo

struct program initProcess(struct program pt)       //Função que inicializa um novo proceoss
{

    pt.ppcb.pid=nextPid;    //dá um PID ao novo processo
    nextPid=nextPid+10;     //Calcula o PID do proximo processo a ser criado
    pt.ppcb.pc=10;          // Posição onde começa as instruções
    pt.ppcb.state=-1;
    for(int i = 0; i < 10; i++)
    {
        pt.mem[i]=0;
    }
    return pt;
}

void print(FILE * fp)
{
    fprintf(fp,"%d ", clockCycle);
    for(int i = 0; i < cont; i++)
    {
        if(toPrint[i].ppcb.state == NEW)
        {
            fprintf(fp,"| new");
        }
        else if(toPrint[i].ppcb.state == WAIT)
        {
            fprintf(fp,"| wait");
        }
        else if(toPrint[i].ppcb.state == RUN)
        {
            fprintf(fp,"| run");
        }
        else if(toPrint[i].ppcb.state == BLOCK)
        {
            fprintf(fp,"| block");
        }
        else if(toPrint[i].ppcb.state == EXIT)
        {
            fprintf(fp,"| exit");
        }
         
    }
    fprintf(fp,"\n");
}

int searchIndex(int pid)            // encontra o indice do pid
{
    int first=0;                    //Indetifica o indice do inicio do array
    int last=cont;                  //Indetifica o indice do fim do array
    int middle= (first+last)/2;     //Indetifica o indice do meio do array
    while(first <= last)
    {
        if (toPrint[middle].ppcb.pid<pid) 
        {
            first= middle+1;
        }else if(toPrint[middle].ppcb.pid==pid)
        {
            return middle;
        }else
        {
            last=middle-1;
        }
        
        middle=(first+last)/2;    
    }
   return -1;
}


void close()                                            //EXIT - Não metemos o nome da função para exit porque o complidador fava warnings por ser um nome parecido a uma função da biblioteca de c
{

    inRun.ppcb.state=EXIT;                              //Mete o estado do progrma para EXIT
    int indexToPrint;                                   //Indice que corresponde ao processo que estamos a trabalhar
    indexToPrint=searchIndex(inRun.ppcb.pid);           //Procura o indice correspondente
    toPrint[indexToPrint].ppcb.state=EXIT;              //Altera o estado do processo no array
    
    inRun.ppcb.pc=10;                                   // Posição onde começa as instruções
    inRun.ppcb.state=-1;
    for(int i = 0; i < 10; i++)
    {
        inRun.mem[i]=0;
    }
    
    runCounter = 0;
}


void block(int x, int y)
{
    
    fseek(ficheiro,(x + inRun.ppcb.pid),SEEK_SET);      //mete o cursor no sitio podemos querer escrever a variavel
    inRun.ppcb.state=BLOCK;
    fprintf(ficheiro, "%d",y);                          //Escreve a info y

    enqueue(blockQueue, inRun, blockQueueSize);
    blockQueueSize++;
    runCounter = 0;

    int indexToPrint;                                   //Indice que corresponde ao processo que estamos a trabalhar
    indexToPrint=searchIndex(inRun.ppcb.pid);           //Procura o indice correspondente
    toPrint[indexToPrint].ppcb.state=BLOCK;             //Altera o estado do processo no array toPrint


   for(int i = 0; i < 10; i++)
    {
        inRun.mem[i]=0;
    }
}

void run()                              //Esta função Run vai agir como um descodificador das instrução
{

    int cod,x,y;
    inRun.ppcb.state=RUN;               //Aletramos o estado do proceso a ser executado para RUN
    cod=inRun.mem[inRun.ppcb.pc+1];     //atribuimos os valores às variaveis
    x=inRun.mem[inRun.ppcb.pc+2];
    y=inRun.mem[inRun.ppcb.pc+3];
    if(flagBlock)                       //garante que só passamos ao block para executar a instrução no ciclo seguinte
    {
        block(x, y);
        flagBlock = false;
        return;
    }
     if(flagExit)                       //garante que só passamos ao exit para executar a instrução no ciclo seguinte
    {
        close();
        flagExit = false;
        return;
    }
    inRun.ppcb.pc=inRun.ppcb.pc+3;      //altera o pc para o inicio da proxima instrução
    switch (cod)
    {
        case 0:
            
            break;
        case 1:
            inRun.mem[x]=y;
            break;
        case 2:
            
            break;
        case 3:
            
            break;
        case 4:
            
            break;
        case 5:
            
            break;
        case 6:
            
            break;
        case 7:
            
            break;
        case 8:
            flagBlock = true;
            break;
        case 9:

            break;
        case 10:
                                                                                                                            
            break;
        case 11:
            flagExit = true;
            break;
        default:
            break;
    } 
}

void runController()
{
   
    if(runCounter == 0  &&  !isEmpty(waitQueueSize))            // wait para o run, se counter =0 significa que run não tem nenhum processo
    {
        inRun = dequeue(waitQueue, waitQueueSize);
        
        int indexToPrint=searchIndex(inRun.ppcb.pid);           //Procura o indice correspondente
        toPrint[indexToPrint].ppcb.state=RUN;                   //Altera o estado do processo no array toPrint
        waitQueueSize --;
        runCounter++;
        run();
        
    }
    else if(runCounter >= QUANTUM && !isFull(waitQueueSize) )   // Quantum break cycle - run para wait
    {
        enqueue(waitQueue, inRun, waitQueueSize);
        waitQueueSize ++;
        int indexToPrint=searchIndex(inRun.ppcb.pid);           //Procura o indice correspondente
        toPrint[indexToPrint].ppcb.state=WAIT;                  //Altera o estado do processo no array toPrint
        runCounter = 0;
        runController();
    }


    else if( runCounter > 0 && runCounter < QUANTUM)
    {
        runCounter ++;
        run();
    }
   
}

int blockController(int blockCounter)
{
    
    if(!isEmpty(blockQueueSize) && blockCounter < 3)
    {
        blockCounter++;
    }
    if(!isEmpty(blockQueueSize) && blockCounter == 3)
    {
        
        struct program moveToWait = dequeue(blockQueue, blockQueueSize);
        blockQueueSize--;
        
        int indexToPrint;                                       //Indice que corresponde ao processo que estamos a trabalhar
        indexToPrint=searchIndex(moveToWait.ppcb.pid);          //Procura o indice correspondente
        toPrint[indexToPrint].ppcb.state=WAIT;                  //Altera o estado do processo no array toPrint
        
        enqueue(waitQueue, moveToWait, waitQueueSize);
        waitQueueSize++;
        blocktoWait ++;
    }
    
    return blockCounter;
}


void new(struct program process)
{
    if(process.ppcb.state == -1)
    {
        process.ppcb.state=NEW;                         //Altera o estado do processo
        enqueue(newQueue,process, newQueueSize);
        newQueueSize++;
        toPrint[cont] = process;
        cont++;
    }

}

void newToWait()
{
    
    while(!isEmpty(newQueueSize) )
    {
        if(!isFull(waitQueueSize))
        {
            struct program removedProgram;
            int indexToPrint;                                       //Indice que corresponde ao processo que estamos a trabalhar

            removedProgram = dequeue(newQueue, newQueueSize);
            indexToPrint=searchIndex(removedProgram.ppcb.pid);      //Procura o indice correspondente

            toPrint[indexToPrint].ppcb.state=WAIT;                  //Altera o estado do processo no array toPrint
            newQueueSize--;
            removedProgram.ppcb.state = WAIT;                       //Altera o estado do processo
            
            enqueue(waitQueue, removedProgram, waitQueueSize);
            waitQueueSize++;
            
        }
    }
}


void controller(int line, int blockCounter, int newCounter, FILE *fp)
{
    struct program p;
                                                            //Verifica por prioridades, desde block, passando por run e finalmente new
                                                            //Garante que em wait entrará o processo prioritário
    blockCounter= blockController(blockCounter);
    
    if(blocktoWait == 1 && waitQueueSize == 1)
    {
        blocktoWait--;

    }else
    {
        runController();  
    }
        
    
    newToWait();

    while(matrix[line][10] == clockCycle && line < 30)                  //Transfere todos os processos que chegam ano ciclo de reologio x para o new
    {
        p=initProcess(p);
        for (int j = 11; j < 300; j++)
        {
            p.mem[j] = matrix[line][j];
        }
        line++;
        new(p);
    }
    
    if(newQueueSize==0 && waitQueueSize ==0 && blockQueueSize==0 && runCounter == 0 && nextPid != 0)        //Se nao houver nada no block/wait/new/run sai da função
    {
        print(fp);
        return;
    }

    print(fp);
    clockCycle++;
    controller(line, blockCounter,newCounter, fp);
}

void readInput()
{   
    char text[300];
    char instante; 
  
    int i = 0;
    for(int h = 0; h < 30; h++)
    {
        for(int g = 0; g < 300; g++)
        {
            matrix[h][g] = -9;
        }
    }

    //O input é recebido por linhae guardado numa matriz bidimensional para o ficheiro de input ser lido apenas uma vez
    //Recebe input referente ao instante"
    
    
    while(scanf("%c",&instante) != EOF)
    {
        if(instante >= '0' && instante <= '9')
        {
            int a = instante - '0';   
            matrix[i][10] = a;       
        }

        // "recebe input com as instrucoes do processo a executar?
        
        
        if(scanf("%[^\n]%*c", text) == 1)
        {
            int j = 0;
            int k = 0;
            while (j < strlen(text))
            {
                 if(text[j] >= '0' && text[j] <= '9')
                {
                    if(text[j+1] >= '0' && text[j+1] <= '9')
                    {
                        int b = text[j] - '0';
                        int c = text[j+1] - '0';
                        b = (b*10) + c;
                        matrix[i][11+k] = b;
                        k++;
                        j+= 2;
                    }
                    else
                    {
                        int b = text[j] - '0';
                        matrix[i][11+k] = b;
                        k++;
                    }
                }
                j++;
            }
        }
        i++;
    }
}

int main()
{
    // Inicia o ficheiro para onde será escrito as variáveis
    
    ficheiro = fopen("file.txt", "r+");
    if (ficheiro == NULL){
        ficheiro=fopen("file.txt" , "w+"); 
    }

    FILE * fp;
    fp =fopen("sheduler_simples.out" , "w+"); //cria ou reescreve o ficheiro de output

    readInput();

    controller(0, 0, 0, fp);       //Inicia a função que irá gerir todo o escalonamento
}
