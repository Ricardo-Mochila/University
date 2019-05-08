package linkedLists;

public class doubleNode<T> {

    T elemento;
    doubleNode<T> next;
    doubleNode<T> prev;


    public doubleNode(T x){
        elemento =x;
        next = null;
        prev = null;
    }
    //construtor vazio
    public doubleNode(){
        this(null);
    }
    //construtor com elemento e next

    public doubleNode(doubleNode<T> p,T x, doubleNode<T> n){
        elemento = x;
        next = n;
        prev = p;
    }

    public T elements() throws InvalidNodeExpression{

        if(this == null){
            throw new InvalidNodeExpression("Null Node");
        }
        return elemento;
        
    }

    public doubleNode<T> getNext() {
        return next;
    }

    public doubleNode<T> getPrev() {
        return prev;
    }

    public void setElemento(T x){
        this.elemento = x;
    }

    public void setNext(doubleNode<T> n) {
        next = n;
    }
}
