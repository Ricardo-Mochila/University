#include <stdio.h>

int mdc(int a, int b){
    if(a == b){

        return a;
    }
    else if(b>a){
        return mdc(a,b-a);
    }
    else{
        return mdc(a-b,b);
    }
}

int main(void){

    int a = 12;
    int b = 20;

    printf("%d\n",mdc(a,b));
    return 0;
}