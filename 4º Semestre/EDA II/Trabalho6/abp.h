struct node
{
    unsigned int element;
    struct node *left;
    struct node *right;
};

struct node *newNode(unsigned int element);
struct node *insert(unsigned int element, struct node *new) ;
struct node *findMin(struct node *minimum);
struct node *removed(unsigned int element, struct node *toRemove);
struct node *search(unsigned int toFind, struct node *IWillFindYou);