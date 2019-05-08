package trabalho.Trabalho3.listaDeContactosABP;


public class bNodes<E> {
    E element;
    bNodes<E> left;
    bNodes<E> right;

    bNodes(E elem){
        element = elem;
        left = null;
        right = null;
    }

    bNodes(bNodes<E> esq, E elem , bNodes<E> dir ){
        element = elem;
        this.left = esq;
        this.right = dir;

    }

    public String toString(){
        return element.toString();
    }
}
