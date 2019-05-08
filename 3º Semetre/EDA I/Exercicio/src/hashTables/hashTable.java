package hashTables;

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
        if(table[procPros(x)] != null){
            if(table[procPros(x)].object.equals(x)){
                return x;
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

    public boolean primeNumber(int a, int c){

        if (a == 1){
            return true;
        }
        else if ( a <= 0){
            return false;
        }
        else if (a%c == 0 && a != c){
            return false;
        }
        else if(a%c != 0 && a != c){
            return primeNumber(a, c+1);
        }
        return true;


    }

    public void rehash(){

        int size2 = size*2;
        int size3 = size;
        while(!primeNumber(size2, 2)){
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
