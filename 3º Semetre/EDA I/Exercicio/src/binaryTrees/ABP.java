package binaryTrees;

public interface ABP <E extends Comparable<? super E>>{

    public boolean isEmpty();

    public boolean contains(E x);

    public E findMin();

    public E findMax();

    public void insert(E x);

    public void remove(E x);

    public void printEmOrdem();

    public void printPosOrdem();

    public void printPreOrdem();

}
