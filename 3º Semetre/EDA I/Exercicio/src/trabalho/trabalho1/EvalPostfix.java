package trabalho.trabalho1;

public class EvalPostfix extends Main{

    String exp;
    ArrayStack<Integer> numeros;
    boolean verbose;

    public EvalPostfix(){
        exp = "1 2 3 * 4 2 - / +";
        verbose = true;
        numeros= new ArrayStack<>(exp.length());
        obterValor(exp);

    }

    public EvalPostfix(String expr){
        exp = expr;
        verbose = true;
        numeros= new ArrayStack<>(exp.length());
        obterValor(exp);
    }

    public EvalPostfix(String expr, boolean bol){
        exp = expr;
        verbose = bol;
        numeros= new ArrayStack<>(exp.length());
        obterValor(exp);
    }



    public void obterValor(String exp){


        String[] separados = new String[exp.length()];
        separados = exp.split(" ");


        for(int i = 0; i < separados.length; i++){
            if (separados[i].equals("+") || separados[i].equals("*") || separados[i].equals("-") || separados[i].equals("/") ){

                if(!verbose){
                    if (separados[i].equals("+")) {
                        int numero2 = numeros.pop();
                        int numero1 = numeros.pop();
                        int soma = numero1 + numero2;
                        numeros.push(soma);
                    } else if (separados[i].equals("/")) {
                        int numero2 = numeros.pop();
                        int numero1 = numeros.pop();
                        int div = numero1 / numero2;
                        numeros.push(div);

                    } else if (separados[i].equals("*")) {
                        int numero2 = numeros.pop();
                        int numero1 = numeros.pop();
                        int mult = numero1 * numero2;
                        numeros.push(mult);

                    } else if (separados[i].equals("−")) {
                        int numero2 = numeros.pop();
                        int numero1 = numeros.pop();
                        int sub = numero1 - numero2;
                        numeros.push(sub);
                    }
                }

                else{
                    if (separados[i].equals("+")) {
                        int numero2 = numeros.pop();
                        System.out.println("int numeros2 = numeros.pop()");
                        int numero1 = numeros.pop();
                        System.out.println("int numeros1 = numeros.pop()");
                        int soma = numero1 + numero2;
                        numeros.push(soma);
                        System.out.println("numeros.push(numeros1 + numeros2)");

                    } else if (separados[i].equals("/")) {
                        int numero2 = numeros.pop();
                        System.out.println("int numeros2 = numeros.pop()");
                        int numero1 = numeros.pop();
                        System.out.println("int numeros1 = numeros.pop()");
                        int div = numero1 / numero2;
                        numeros.push(div);
                        System.out.println("numeros.push(numeros1 / numeros2)");

                    } else if (separados[i].equals("*")) {
                        int numero2 = numeros.pop();
                        System.out.println("int numeros2 = numeros.pop()");
                        int numero1 = numeros.pop();
                        System.out.println("int numeros1 = numeros.pop()");
                        int mult = numero1 * numero2;
                        numeros.push(mult);
                        System.out.println("numeros.push(numeros1 * numeros2)");

                    } else if (separados[i].equals("-")) {
                        int numero2 = numeros.pop();
                        System.out.println("int numeros2 = numeros.pop()");
                        int numero1 = numeros.pop();
                        System.out.println("int numeros1 = numeros.pop()");
                        int sub = numero1 - numero2;
                        numeros.push(sub);
                        System.out.println("numeros.push(numeros1 - numeros2)");
                    }
                }
            }

            else{
                numeros.push(Integer.parseInt(separados[i]));
                if (verbose){
                    System.out.println("numeros.push("+Integer.parseInt(separados[i])+")");
                }
            }
        }
        System.out.println("Final result: "+numeros.toString());
    }
}
