package trabalho.trabalho1;

public class ArrayStack<E> implements Stack<E>{

    public int maxSize;
    private int  actualSize= 0;
    E[] stack;

    public ArrayStack (){
        maxSize = 10;
        stack = (E[]) new Object[maxSize];
    }

    public ArrayStack (int s){
        maxSize = s;
        this.stack = (E[]) new Object[s];
    }

    @Override
    public void push(E o) {
        stack[actualSize] = o;
        actualSize+= 1;
    }

    @Override
    public E top() {
        if(actualSize <= 0) {
            throw new IndexOutOfBoundsException("The stack is empty");
        }
        return stack[actualSize-1];
    }

    @Override
    public E pop() {
        if(actualSize > 0){
            E popper = stack[actualSize-1];
            stack[actualSize-1]=null;
            actualSize-=1;
            return popper;
        }
        return null;

    }

    @Override
    public E peek() {
        return stack[0];
    }


    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean empty() {

        if(actualSize == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i= 0; i< stack.length; i++){
            if(stack[i]  != null){
                s += stack[i]+ " ";
            }
        }
        return s;
    }
}

