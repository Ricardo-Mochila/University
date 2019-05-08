#define MEM_SIZE 300

struct pcb  //cada processo tem estas variaveis
{
    int pc;
    int pid;
    short state;
};

struct program
{
    struct pcb ppcb;
    int mem[MEM_SIZE];
};

