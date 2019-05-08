package trabalho.Trabalho3.listaDeContactosABP;

import Queues.QueueArray;

import java.util.Iterator;

public class binaryTrees <E extends Comparable<?super E>> implements ABP<E>, Iterable<E> {

    int size;
    bNodes<E> root;

    public binaryTrees(){
        root = null;
    }


    public binaryTrees( E elem){
        root = new bNodes<E>(elem);
    }

    public binaryTrees( bNodes<E> nodeRoot){
        root = nodeRoot;
    }

    public binaryTrees(bNodes<E> nodeL, E elem, bNodes<E> nodeR){
        root = new bNodes<E>(nodeL, elem, nodeR);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(E x) {

        if(isEmpty()){
            return false;
        }

        return contains(x, root);
    }

    public boolean contains(E x, bNodes<E> cont) {

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

    @Override
    public E findMin(){

        if(isEmpty()){
            return null;
        }
        return findMin(root);
    }

    public E findMin(bNodes<E> min) {

        if(min.left == null){
            return min.element;
        }
        return findMin(min.left);
    }

    @Override
    public E findMax() {

        if(isEmpty()){
            return null;
        }
        return findMax(root);
    }

    public E findMax(bNodes<E> max) {

        if(max.right == null){
            return max.element;
        }
        return findMax(max.left);
    }

    @Override
    public void insert(E x) {

        root = insert(x, root);

    }

    public bNodes<E> insert(E x, bNodes<E> toInser) {

        if(toInser == null){
            toInser = new bNodes<E>(null, x, null);
        }

        else if (toInser.element.compareTo(x) > 0){
            toInser.left = insert(x, toInser.left);
        }

        else if(toInser.element.compareTo(x)<0){
            toInser.right = insert(x, toInser.right);
        }

        return toInser;
    }

    @Override
    public void remove(E x) {

        root = remove(x, root);
    }

    public bNodes<E> remove(E x, bNodes<E> toRemove){

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

    @Override
    public void printEmOrdem() {
        printEmOrdem(root);
    }

    public void printEmOrdem(bNodes node){

        if(node != null){
            printEmOrdem(node.left);
            System.out.print(node.element + " ");
            printEmOrdem(node.right);
        }

    }

    @Override
    public void printPosOrdem() {
        printPosOrdem(root);
    }

    public void printPosOrdem(bNodes node) {


        if(node != null){
            printPosOrdem(node.left);
            printPosOrdem(node.right);
            System.out.print(node.element + " ");
        }
    }

    @Override
    public void printPreOrdem() {
        printPreOrdem(root);
    }

    public void printPreOrdem(bNodes node){

        if(node != null){
            System.out.print(node.element + " ");
            printPreOrdem(node.left);
            printPreOrdem(node.right);
        }
    }

    @Override
    public Iterator<E> iterator() {
        iteratorPreOrdem it = new iteratorPreOrdem(root);
        return it;
    }

    public Iterator<E> iteratorEmOrdem() {
        iteratorEmOrdem it = new iteratorEmOrdem(root);
        return it;
    }


    public static class iteratorPreOrdem<E> implements Iterator<E> {

        trabalho.trabalho1.ArrayStack<bNodes> stack;

        public iteratorPreOrdem(bNodes root) {
            stack = new trabalho.trabalho1.ArrayStack<bNodes>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.empty();
        }

        public E next() {

            bNodes node = stack.pop();
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

    public static class iteratorEmOrdem<E> implements Iterator<E> {

        QueueArray<E> queue;

        public iteratorEmOrdem(bNodes<E> root) {
            queue = new QueueArray<>();
            next(root);
        }

        public boolean hasNext() {
            return !queue.empty();
        }

        public E next() {
            return queue.dequeue();
        }

        public void next(bNodes<E> node) {

            if(node != null){
                next(node.left);
                queue.enqueue(node.element);
                next(node.right);
            }

        }

    }

}
