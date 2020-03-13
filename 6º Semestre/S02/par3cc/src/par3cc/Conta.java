package par3cc;

public class Conta {

    private double saldo;

    // construtores
    public Conta() {
        saldo = 0;
    }

    public Conta(double saldoInicial) {
        saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void credito(double valor) {
        synchronized (this) {
            System.out.println(" ++credito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);

            double saldoTmp = valor + saldo;
            // simular uma demora
            try { Thread.sleep(1000); } catch (Exception e){}


            saldo = saldoTmp;

            System.out.println("\t ++credito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
            notify();
        }
    }

    public void debito(double valor) throws InterruptedException {
       synchronized (this) {
            System.out.println(" ++ debito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);
            double saldoTmp = saldo - valor;
            
            if(saldoTmp < 0){
                wait();
            }
      
            // simular uma demora
            try { Thread.sleep(1000); } catch (Exception e){}

            saldo = saldoTmp;

            System.out.println("\t ++ debito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
            notify();
        }
    }

}
