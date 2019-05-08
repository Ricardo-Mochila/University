struct element
{
    short a;
    short b;
    short c;
    short interface;
};

int hashcode(short a, short b, short c);

struct element *search(short a, short b, short c);

void insert(short a, short b, short c, short interface);