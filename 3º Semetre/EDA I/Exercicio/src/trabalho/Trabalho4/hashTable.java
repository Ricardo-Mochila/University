package trabalho.Trabalho4;

import javax.lang.model.element.Element;

public abstract class hashTable<T> {

    int size;
    int counter=0;
    elemento<T>[] table;

    public hashTable(){
        size = 11;
        table = new elemento[size];

    }

    public hashTable(int n){
        size = n;
        table = new elemento[size];
    }

    public int ocupados(){

        return counter;
    }

    public float factorCarga(){

        return counter/size;
    }

    protected abstract int procPros(T x);


    public void alocarTabela (int dim){

        table = new elemento[dim];

    }

    public void tornarVazia(){

        table = new elemento[size];
        counter = 0;
    }

    public T procurar(T x){
        int c = 1;
        if(table[procPros(x)] != null){
            if(table[procPros(x)].object.equals(x)){
                return x;
            }
            else{
                while(table[procPros(x)+c] != null  && procPros(x)+c < size){
                    if(table[procPros(x)+c].object.equals(x)){
                        return x;
                    }
                    else{
                        c += 1;
                    }

                }
            }
        }


        return null;
    }

    public void remove(T x){

        if(table[procPros(x)] != null) {
            if (table[procPros(x)].object.equals(x)) {
                table[procPros(x)].removed = true;
                counter -= 1;
            }
        }

    }

    public void insere(T x){

        elemento n = new elemento();
        n.removed = false;
        n.object = x;

        if(table[procPros(x)] != n){
            table[procPros(x)] = n;
            counter += 1;

        };

        if(factorCarga() >= 0.5){
            rehash();
        }

    }

    private static boolean primeNumber(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;
        }
        return true;
    }

    public void rehash(){

        int size2 = size*2;
        int size3 = size;
        while(!primeNumber(size2)){
            size2 += 1;
        }
        elemento<T>[] tabela2 = new elemento[size];

        for(int i = 0; i < size; i++){
            if(table[i] != null && !table[i].removed){
                tabela2[i] = table[i];
            }
        }

        size = size2;
        table = new elemento[size];
        for(int i = 0; i < size3; i++){
            if(tabela2[i]!=null){
                insere(tabela2[i].object);
            }

        }
    }

    public void print(){

        for (int i = 0; i < size; i++ ) {
            if(table[i] != null) {
                if(!table[i].removed){
                    System.out.println(table[i].object);
                }
            }
        }

    }

}
