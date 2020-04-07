package paral02;

public class Paral02 {
    private int start;
    private int end;
    
    
    public Paral02(int start, int end) {
        this.start= start;
        this.end= end;
    }
    
    int count_primes(int start, int end) {
        int total = 0;
        int i = start; // check if prime    
        while (i <= end) {
            int c;
            for (c = 2; c <= i - 1; c++) {
                if (i % c == 0) {
                    break;
                }
            }
            if (c == i) {
                //printf("%d\n", i);
                total++;
            }
            i++;   // next prime candidate
        }
        return total;
    }

    public void go() {
	
        int found_primes = count_primes(start, end);
        System.out.println("  found "+found_primes);
    }
    public static void main(String[] args){
        PrimosThread p1 = new PrimosThread(0,50000);
        PrimosThread p2 = new PrimosThread(50000,100000);
        PrimosThread p3 = new PrimosThread(100000,150000);
        PrimosThread p4 = new PrimosThread(150000,200000);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}

class PrimosThread extends Thread {
    int inicio;
    int fim;
    
    public PrimosThread() {
        super();
        this.inicio= 0;
        this.fim= 10;
    }
    public PrimosThread(int inicio, int fim) {
        super();
        this.inicio= inicio;
        this.fim= fim;
    }    
    
    public void run() {
        Paral02 p= new Paral02(inicio,fim);
        p.go();
    }
}





