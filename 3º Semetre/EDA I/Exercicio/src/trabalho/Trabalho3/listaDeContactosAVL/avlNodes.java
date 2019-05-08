package trabalho.Trabalho3.listaDeContactosAVL;

public class avlNodes<E> {
    E element;
    avlNodes<E> left;
    avlNodes<E> right;
    public int equil;
    public int height;

    avlNodes(E elem){
        element = elem;
        left = null;
        right = null;
    }

    avlNodes(avlNodes<E> esq, E elem , avlNodes<E> dir ){
        element = elem;
        this.left = esq;
        this.right = dir;

    }

    public String toString(){
        return element.toString();
    }
}
