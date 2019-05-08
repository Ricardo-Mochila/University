package trabalho.Trabalho3.listaDeContactosAVL;

import trabalho.trabalho1.ArrayStack;

import java.util.Iterator;

public class AvlTree <E extends Comparable<?super E>> implements Iterable<E>{
    avlNodes<E> root;
    int height;

    public AvlTree(){
        root=null;
    }

    public AvlTree(E elem){
        root=new avlNodes<E>(elem);


    }

    public  AvlTree(avlNodes<E> elem){
        root=elem;


    }

    public  AvlTree(avlNodes<E> esq , E elem, avlNodes<E> dir){
        root=new avlNodes<E>(esq,elem,dir);

    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(E x) {

        if(isEmpty()){
            return false;
        }

        return contains(x, root);
    }

    public boolean contains(E x, avlNodes<E> cont) {

        if(cont == null){
            return false;
        }

        else{
            if(cont.element.compareTo(x) > 0){
                return(contains(x, cont.left));
            }

            else if(cont.element.compareTo(x)<0){
                return(contains(x, cont.right));
            }

            else{
                return true;
            }
        }
    }

    public E findMin(){

        if(isEmpty()){
            return null;
        }
        return findMin(root);
    }

    public E findMin(avlNodes<E> min) {

        if(min.left == null){
            return min.element;
        }
        return findMin(min.left);
    }

    public E findMax() {

        if(isEmpty()){
            return null;
        }
        return findMax(root);
    }

    public E findMax(avlNodes<E> max) {

        if(max.right == null){
            return max.element;
        }
        return findMax(max.left);
    }

    public void insert(E x) {

        root = insert(x, root);

    }

    public avlNodes<E> insert(E x, avlNodes<E> toInser) {

        if(toInser == null){
            toInser = new avlNodes<E>(null, x, null);
        }

        else if (toInser.element.compareTo(x) > 0){
            toInser.left = insert(x, toInser.left);
        }

        else if(toInser.element.compareTo(x)<0){
            toInser.right = insert(x, toInser.right);
        }

        return toInser;
    }

    public void remove(E x) {

        root = remove(x, root);
    }

    public avlNodes<E> remove(E x, avlNodes<E> toRemove){

        if (toRemove==null){
            return toRemove;
        }

        else if (toRemove.element.compareTo(x)<0){
            toRemove.right=remove(x,toRemove.right);
        }

        else if(toRemove.element.compareTo(x)>0){
            toRemove.left=remove(x,toRemove.left);
        }

        else if (toRemove.left!=null && toRemove.right!=null){
            E min = findMin(toRemove.right);
            toRemove.element = min;
            toRemove.right=remove(min,toRemove.right);
        }

        else if (toRemove.left==null) {
            toRemove = toRemove.right;
        }

        else{
            toRemove=toRemove.left;
        }

        return toRemove;
    }

    public void printEmOrdem() {
        printEmOrdem(root);
    }

    public void printEmOrdem(avlNodes node){

        if(node != null){
            printEmOrdem(node.left);
            System.out.print(node.element + " ");
            printEmOrdem(node.right);
        }

    }

    public void printPosOrdem() {
        printPosOrdem(root);
    }

    public void printPosOrdem(avlNodes node) {


        if(node != null){
            printPosOrdem(node.left);
            printPosOrdem(node.right);
            System.out.print(node.element + " ");
        }
    }

    public void printPreOrdem() {
        printPreOrdem(root);
    }

    public void printPreOrdem(avlNodes node){

        if(node != null){
            System.out.print(node.element + " ");
            printPreOrdem(node.left);
            printPreOrdem(node.right);
        }
    }

    public Iterator<E> iterator() {
        Iterator it = new iterator(root);
        return it;
    }


    public void update(avlNodes<E> node) {
        int leftNodeHeight;
        int rightNodeHeight;

        if (node.left == null){
            leftNodeHeight = -1;
        }
        else{
            leftNodeHeight = node.left.height;
        }

        if (node.right == null){
            rightNodeHeight = -1;
        }
        else{
            rightNodeHeight = node.right.height;
        }

        node.height = 1 + max(leftNodeHeight, rightNodeHeight);
        node.equil = rightNodeHeight - leftNodeHeight;

    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public avlNodes<E> equilibrio(avlNodes<E> node) {


        if (node.equil == -2) {


            if (node.left.equil <= 0) {
                return doubleLeft(node);

            } else {
                return leftRight(node);
            }


        } else if (node.equil == + 2) {


            if (node.right.equil >= 0) {
                return doubleRight(node);


            } else {
                return rightLeft(node);
            }

        }

        return node;

    }

    private avlNodes doubleLeft(avlNodes node) {
        return rightRotate(node);
    }

    private avlNodes leftRight(avlNodes node) {
        node.left = leftRotate(node.left);
        return doubleLeft(node);
    }

    private avlNodes doubleRight(avlNodes node) {
        return leftRotate(node);
    }

    private avlNodes rightLeft(avlNodes node) {
        node.right = rightRotate(node.right);
        return doubleRight(node);
    }

    private avlNodes leftRotate(avlNodes node) {
        avlNodes<E> n = node.right;
        node.right = n.left;
        n.left = node;
        update(node);
        update(n);
        return n;
    }

    private avlNodes rightRotate(avlNodes node) {
        avlNodes<E> n = node.left;
        node.left = n.right;
        n.right = node;
        update(node);
        update(n);
        return n;
    }

    public static class iterator<E> implements Iterator<E> {

        trabalho.trabalho1.ArrayStack<avlNodes> stack;

        public iterator(avlNodes root) {
            stack = new trabalho.trabalho1.ArrayStack<avlNodes>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.empty();
        }

        public E next() {
            avlNodes node = stack.pop();
            E result = (E) node.element;

            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }


    }

    public static void main(String[] args) {
        AvlTree<Integer> av = new AvlTree<>();
        av.insert(13);
        av.insert(12);
        av.insert(4);
        av.insert(32);
        av.insert(21);
        av.insert(15);
        av.insert(19);
        av.insert(1);
        av.insert(14);
        av.insert(16);
        av.insert(55);
        av.insert(25);
        av.printPosOrdem();
    }
}

