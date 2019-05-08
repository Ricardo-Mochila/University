package linkedLists;

import java.util.Iterator;

public class doubleLinkedList<T> implements linkedLists<T> {

    private doubleNode<T> top;
    private doubleNode<T> bottom;
    private int theSize;

    public doubleLinkedList(){

        top = new doubleNode<T>(bottom.next, null, null );
        bottom = new doubleNode<T>(null, null, top.prev);
        theSize = 0;

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return theSize==0;
    }

    public doubleNode<T> top(){
        return top;
    }

    public doubleNode<T> Bottom(){
        return bottom;
    }

    @Override
    public void add(T x) {
        doubleNode<T> added = new doubleNode<>(top.prev.next, x, top);
        theSize += 1;
    }

    @Override
    public void add(int i, T x) {


    }

    @Override
    public void remove(int i) {

    }

    @Override
    public void remove(T x) {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public void set(int i, T y) {

    }
}
