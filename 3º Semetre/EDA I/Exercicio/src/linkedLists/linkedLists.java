package linkedLists;

public interface linkedLists<T>
{
    //Um constructor que providencia uma lista vazia

    public java.util.Iterator iterator();

    public int size();

    public boolean isEmpty();

    public void remove(int i);

    public void remove(T x);

    public void add(T x); //adiciona x no fim da lista

    public void add(int i, T x);

    public T get(int i);

    public void set(int i, T y);

    public String toString();
}
