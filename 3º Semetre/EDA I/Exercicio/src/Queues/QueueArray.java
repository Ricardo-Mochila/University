package Queues;

public class QueueArray<E> implements Queue<E> {


    E[] queue;
    int size;
    int init = 0;
    int end = 0;


    public QueueArray(){
        size = 10;
        queue = (E[]) new Object[size];
    }

    public QueueArray(int dim){
        size = dim;
        queue = (E[]) new Object[size];
    }

    @Override
    public void enqueue(E o) {
        if (end < size){
            queue[end] = o;
            end+=1;
        }
    }

    @Override
    public E front() {
        return queue[init];
    }

    @Override
    public E dequeue() {

        if(end == 0){
            throw new IndexOutOfBoundsException("The queue is empty");
        }

        E removed = queue[init];

        for(int i = 0; i < end-1; i++ ){
            queue[i] = queue[i+1];
        }
        queue[end-1] = null;
        end -= 1;


        return removed;
    }

    @Override
    public int size() {
        return end;
    }

    @Override
    public boolean empty() {
        if(end == 0){
            return true;
        }
        return false;

    }
}
