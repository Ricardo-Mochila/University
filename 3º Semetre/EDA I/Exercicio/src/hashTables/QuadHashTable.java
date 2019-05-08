package hashTables;

public class QuadHashTable<T> extends hashTable<T> {

    public QuadHashTable(){

    }

    public QuadHashTable(int n){

        super(n);
    }

    @Override
    protected int procPros(T x) {

        int pos = x.hashCode() % size;

        if(pos >= size || pos < 0){
            pos = 0;

            if(table[pos] == null ){
                return pos;
            }
            else{
                if(table[pos].object.equals(x)){
                    return pos;
                }
                while(table[pos] != null && pos + 1 < size) {
                    pos *= pos;
                }
                return pos;
            }

        }

        else{
            if(table[pos] == null){
                return pos;
            }
            else{
                if(table[pos].object.equals(x)){
                    return pos;
                }
                while(table[pos] != null && pos+1 < size  ){
                    pos *= pos;
                }

                return pos;
            }

        }
    }
}
