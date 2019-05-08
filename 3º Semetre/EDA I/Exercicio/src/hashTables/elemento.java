package hashTables;

public class elemento<T> {

    T object;
    boolean removed;

    public elemento(){

    }

    public elemento(T n){
        object = n;
        removed = false;
    }

}
