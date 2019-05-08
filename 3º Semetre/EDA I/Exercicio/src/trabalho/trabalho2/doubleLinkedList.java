package trabalho2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

public class doubleLinkedList<T> implements Iterable<T> {

    //
    //  Implementação de
    //  double nodes
    //

    static class doubleNode<T> {

        T elemento;
        doubleNode<T> next;
        doubleNode<T> prev;


        public doubleNode(T x){
            elemento = x;
            next = null;
            prev = null;
        }

        public doubleNode(){
            elemento = null;
            next = null;
            prev = null;
        }


        public doubleNode(doubleNode<T> p, T x, doubleNode<T> n){
            elemento = x;
            next = n;
            prev = p;
        }

        public T elements() throws InvalidNodeExpression {

            if(elemento == null){
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
            elemento = x;
        }

        public void setNext (doubleNode<T> n) {
            next = n;
        }

        public void setPrev (doubleNode<T> p) {
            prev = p;
        }
    }

    //
    //  Implementação de
    //  double linked lists
    //

    private doubleNode<T> top = new doubleNode<>();
    private doubleNode<T> bottom;
    private int theSize = 0;

    public doubleLinkedList(){
        bottom = top;
    }

    /*public doubleLinkedList(T value){
        throw new ExecutionExcep
    }*/


    public Iterator iterator() {
        Iterador it = new Iterador(top.getNext());
        return it;
    }


    public int size() {
        return theSize;
    }


    public boolean isEmpty() {
        return theSize==0;
    }


    public doubleNode<T> top(){
        return top;
    }


    public doubleNode<T> Bottom(){
        return bottom;
    }


    public void add(T element) {
        doubleNode<T> added = new doubleNode<>(element);

        if(isEmpty()){
            top.setNext(added);
            added.setPrev(top);
            bottom = added;
            theSize += 1;
        }

        else{
            bottom.setNext(added);
            added.setPrev(bottom);
            bottom = added;
            theSize+= 1;
        }

    }


    public void add(int index, T element) {

        doubleNode<T> added = new doubleNode<>(element);

        if(isEmpty() || index == theSize+1){
            add(element);
        }

        else if(index > theSize+1 ){
            throw new IndexOutOfBoundsException();
        }

        else{
            doubleNode<T> prev = get(index).getPrev();
            doubleNode<T> next = get(index);

            added.setNext(next);
            added.setPrev(prev);

            prev.setNext(added);
            next.setPrev(added);

            theSize += 1;
        }

    }


    public void remove(int i) {

        if(i > theSize){
            System.out.println("you stupied");
        }

        else if (isEmpty()){
            System.out.println("nada");
        }

        else{
            doubleNode<T> prev = get(i).getPrev();
            doubleNode<T> next = get(i).getNext();

            if(next == null){
                prev.setNext(null);
                bottom = prev;
            }

            else if(prev == null){
                next.setPrev(top);
            }

            else{
                prev.setNext(next);
                next.setPrev(prev);
            }

            theSize -= 1;

        }

    }


    public void remove(T element) {

        Iterador it = new Iterador(top);
        int c = 1;

        if(bottom.elements() == element){
            remove(theSize);
        }
        else{

            while (it.hasNext()){

                if(it.next() != element){
                    c += 1;
                }

                else{
                    remove(c);
                }
            }
        }
    }


    public doubleNode<T> get(int index) {
        int c = 0;
        doubleNode<T> pos = top;

        if (index == 0 && index > theSize){
            throw new NullPointerException();
        }

        else{
            while(c < index){
                pos = pos.getNext();
                c++;
            }

        }
        return pos;
    }


    public void set(int i, T elemento) {

        if (isEmpty() ){
            throw new IndexOutOfBoundsException();
        }

        else if(i > theSize){
            throw new IndexOutOfBoundsException();
        }

        else{
            get(i).setElemento(elemento);
        }

    }

    public String toString(){

        int i;

        String dll = "";
        Iterador it = new Iterador(top.getNext());

        if(isEmpty())
        {
            return "The Stack is Empty";
        }
        for(i = 1; it.hasNext(); i++){
            dll +=  i + "   " + it.next() + "\n";
        }
        dll +=  i + "   " + bottom.elements() + "\n";

        return dll;
    }


    //
    //  Implementação de
    //  Um Iterador
    //


    static class Iterador<T>  implements Iterator{

        private doubleNode<T> inUse;

        public Iterador(doubleNode<T> n) {
            inUse = n;
        }

        public boolean hasNext() {
            return inUse.getNext() != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = inUse.elements();
            inUse = inUse.getNext();
            return element;
        }

        public boolean hasPrev(){
            return inUse.getPrev() != null;
        }

        public T prev() {
            if(!hasPrev()){
                throw new NoSuchElementException();
            }

            T element = inUse.elements();
            inUse = inUse.getPrev();
            return element;
        }

    }

    //
    //  Implementação de
    //  Invalid node expression
    //

    static class InvalidNodeExpression extends RuntimeException {

        public InvalidNodeExpression(){
            super();
        }
        public InvalidNodeExpression(String x){
            super(x);
        }
    }



}


