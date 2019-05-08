#include <stdio.h>
#include <stdbool.h>

struct node_t
{
    struct node_t * a[26];
    bool aaa;
};

int main(int argc, char const *argv[])
{
    printf("%lu",sizeof(struct node_t));

    return 0;
}
