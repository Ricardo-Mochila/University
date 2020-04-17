#include <stdio.h>
#include "trie.h"

int main()
{
    char array[][7] = {"banana", "bolacha","corona", "pilinha"};
    struct trie *nova = trie_new();
    trie_insert(nova, array);
    trie_print(nova);
    //wordCount(nova);
}/*
int main() 
{ 
    // Input keys (use only 'a' through 'z' and lower case) 
    char keys[][8] = {"the", "a", "there", "answer", "any", 
                     "by", "bye", "their"}; 
  
    char output[][32] = {"Not present in trie", "Present in trie"}; 
  
  
    struct TrieNode *root = getNode(); 
  
    // Construct trie 
    int i; 
    for (i = 0; i < ARRAY_SIZE(keys); i++) 
        trie_insert(root, keys[i]); 
  
    // Search for different keys 
    trie_printf("%s --- %s\n", "the", output[search(root, "the")] ); 
    trie_printf("%s --- %s\n", "these", output[search(root, "these")] ); 
    trie_printf("%s --- %s\n", "their", output[search(root, "their")] ); 
    trie_printf("%s --- %s\n", "thaw", output[search(root, "thaw")] ); 
  
    return 0; 
} */