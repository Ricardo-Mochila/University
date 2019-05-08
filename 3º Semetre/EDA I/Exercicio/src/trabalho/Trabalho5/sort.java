package trabalho.Trabalho5;

import java.util.Scanner;
import java.util.StringTokenizer;

public class sort<T> {

    Comparable[] array;

    public sort(int size){
        array = (Comparable[]) new Object[size];
    }

    public sort(){
        array = (Comparable[]) new Object[10];
    }

    public static Comparable[] leArray() {
        int c = 0;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);


        System.out.print("How many numbers you wanna sort? ");
        int size = scanner.nextInt();

        System.out.print("What's the numbers you wanna sort: ");
        String cenas = scanner1.nextLine();
        StringTokenizer st = new StringTokenizer(cenas, " ,", false);
        Comparable[] array = new Comparable[size];

        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            array[c] = num;
            c++;
        }

        return array;
    }
    public static void printArray(Comparable[] x){

        System.out.println();
        for(int i = 0; i < x.length; i++){
            System.out.print(x[i] + ", ");
        }


    }

    /*
    *
    *  the next three methods are for the heap sort
    *
    */


    public static void heapify(Comparable[] array, int i, int size){
        int fim = i*2+1;

        if((fim < size) && array[fim].compareTo(array[fim+1]) < 0){
            fim++;
        }
        if((fim <= size) && array[i].compareTo(array[fim]) < 0){
            troca(array, i, fim);
            heapify(array, fim, size);
        }
    }

    private static void build_heap(Comparable[] A){
        for (int i= A.length / 2; i>= 0; i--){
            heapify(A,i,A.length - 1);
        }
    }

    public static void method1(Comparable[] A){
        build_heap(A);
        for (int i=A.length - 1;i> 0;i--){
            troca(A, 0,i);
            heapify(A, 0,i- 1);
        }
    }

    /*
     *
     *  the next three methods are for the quick sort
     *
     */
    private static int particao(Comparable[] a, int esq, int dir){
        int i=esq;
        int j=dir;
        Comparable pivot=a[(esq+dir)/2];
        while (true){
            while (a[i].compareTo(pivot)<0){
                i++;
            }
            while (a[j].compareTo(pivot)>0){
                j--;
            }
            if (i<j){
                troca(a,i,j);
                i++;
                j--;
            }
            else
                break;

        }
        return j;
    }

    private static void qsort(Comparable[] a, int esq, int dir){
        if (esq<dir){
            int k=particao(a,esq,dir);

            qsort(a,esq,k);
            qsort(a,k+1,dir);
        }
    }


    private static void troca(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void method2(Comparable[] A){
        qsort(A, 0, A.length-1);
    }



    public static void main(String[] args) {
        Comparable[] arr = leArray();
        printArray(arr);
        method1(arr);
        printArray(arr);

    }

}
