package trabalho.Trabalho4;

public class QuadHashTable<T> extends hashTable<T> {

    public QuadHashTable(){

    }

    public QuadHashTable(int n){

        super(n);
    }

    @Override
    protected int procPros(T x) {

        int pos = x.hashCode() % size;
        int c = 1;
        if(pos<0){
            pos *= -1;

        }
        while(true){
            if (table[pos] != null && table[pos].object.equals(x)){
                break;
            }
            if(table[pos] == null || table[pos].removed){
                break;
            }
            else if(pos >= size-1){
                pos = 0;
            }
            else {
                pos = pos + c*c ;
            }


        }
        return pos;
    }
}
