#include <stdio.h>
#include <stdbool.h>
#include "programa.h"
#include "queue.h"


int peek(struct program queueArr[]) 
{
   return queueArr[0].ppcb.pid;
}

bool isEmpty(int queueSize)
{
   return queueSize == 0;
}

bool isFull(int queueSize)
{
   return queueSize == MAX_PROGRAM_SIZE;
}

void enqueue(struct program queueArr[], struct program process, int queueSize)
{
   if(!isFull(queueSize))
   {
      queueArr [queueSize] = process;
   }
}

struct program dequeue(struct program queueArr[], int queueSize)
{
   struct program removedProgram= queueArr[0];
   for(int i = 0; i < queueSize-1; i++)
   {
      queueArr[i]=queueArr[i+1];
   }
   return removedProgram;  
}
