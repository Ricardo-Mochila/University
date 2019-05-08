package trabalho.Trabalho4;

public class linHashTable<T> extends hashTable<T> {

    public linHashTable(){

    }

    public linHashTable(int n){

        super(n);
    }


    @Override
    protected int procPros(Object x) {

        int pos = x.hashCode() % size;
        if(pos < 0){
            pos *= -1;

        }
        while(true){
            if (table[pos] != null && table[pos].object.equals(x)){
                break;
            }
            if(table[pos] == null || table[pos].removed){
                break;
            }
            else if(pos == size-1){
                pos = 0;
            }
            else {
                pos++;
            }


        }
        return pos;

    }


}
