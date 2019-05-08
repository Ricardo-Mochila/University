package hashTables;

public class linHasTable <T> extends hashTable<T> {

    public linHasTable(){

    }

    public linHasTable(int n){

        super(n);
    }


    @Override
    protected int procPros(Object x) {

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
                    pos += 1;
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
                    pos+=1;
                }

                return pos;
            }

        }
    }


}
