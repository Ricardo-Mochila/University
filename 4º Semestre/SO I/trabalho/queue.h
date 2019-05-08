#define MAX_PROGRAM_SIZE 30

int peek(struct program queueArr[]);

bool isEmpty(int queueSize);

bool isFull(int queueSize);

void enqueue(struct program queueArr[],struct program process, int queueSize);

struct program dequeue(struct program queueArr[], int queueSize);