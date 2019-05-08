package frequencia4_2016;

import java.util.*;

public class freq4_2016 {

    public static void main(String[] args) {

        Notas notinhas = new Notas();
        notinhas.setTestes(16,12,14,15,16);
        notinhas.setTrab(12);
        notinhas.setAlea();
        System.out.println("Nota de aluno: "+notinhas.media()+"\nComponente aleatoria:"+notinhas.getAlea());

        Turma turminha = new Turma();
        turminha.insere(1234,13);
        turminha.insere(1267,17);
        System.out.println("\nNotas da turma: "+turminha.media());

        NotasLicenciatura licenciatura = new NotasLicenciatura();
        licenciatura.setAlea();
        licenciatura.setTrab(15);
        licenciatura.setTestes(12,13,14,15,16);
        System.out.println("\nNota de licenciatura: "+licenciatura.media()+"\nComponente aleatoria:"+licenciatura.getAlea());

        NotasMestrado mestrado = new NotasMestrado();
        mestrado.setAlea();
        mestrado.setTrab(15);
        mestrado.setTestes(12,13,14,15,16);
        System.out.println("\nNota de Mestrado: "+mestrado.media()+"\nComponente aleatoria:"+mestrado.getAlea());

        NotasDouturamento douturamento = new NotasDouturamento();
        douturamento.setTrab(16);
        douturamento.setAlea();
        System.out.println("\nNota de douturamento: "+douturamento.media()+"\nComponente aleatoria:"+douturamento.getAlea());

    }
}

class Notas{

    float[] testes = new float[5];
    float trab,alea;
    Random random = new Random();

    public void setTestes(float teste1,float teste2, float teste3, float teste4, float teste5){

        testes[0] = teste1;
        testes[1] = teste2;
        testes[2] = teste3;
        testes[3] = teste4;
        testes[4] = teste5;

        this.testes = testes;

    }

    public void setTrab(float trab){

        this.trab = trab;
    }

    public void setAlea(){

        int rand1 = random.nextInt(6)+1;
        int rand2 = random.nextInt(6)+1;
        int rand3 = random.nextInt(6)+1;

        this.alea = rand1+rand2+rand3;


    }

    public float[] getTestes(){
        return this.testes;

    }

    public float getTrab(){
        return this.trab;
    }

    public float getAlea(){

        return this.alea;
    }

    public int media(){

        float soma = 0;
        int iterador = 0;
        float notaMinima = testes[0];
        float notaMaxima = 0;
        int media;

        for(int i = 0; i < testes.length; i++){

            if(notaMinima > testes[i]){

                notaMinima = testes[i];
            }

            if(notaMaxima < testes[i]){

                notaMaxima = testes[i];
                iterador += 1;
            }
            testes[iterador] = notaMinima;

        }

        for(int i = 0; i < testes.length; i++){

            soma += testes[i];
        }
        soma = soma/5;

        return media = (int)Math.round((trab*0.20)+(soma*0.55)+(alea*0.25));

    }

}
 class Turma{

    Map notasTurma = new TreeMap<Integer, Notas>();

    void insere(int numeros, int notas){

        notasTurma.put(numeros, notas);

    }

    public int media(){

        int soma = 0;

        Set set = notasTurma.entrySet();
        Iterator iterator = set.iterator();

        for(int i = 0; i < notasTurma.size(); i++){

            Map.Entry mentry = (Map.Entry)iterator.next();
            soma += (int)mentry.getValue();

        }
        return soma/notasTurma.size();
    }

 }

 class NotasLicenciatura extends Notas{

     public NotasLicenciatura() {
     }
 }

 class NotasMestrado extends Notas{

    public int media(){

        float melhorNota = 0;
        float soma = 0;
        int media;

        for(int i = 0; i < testes.length; i++){

            if(melhorNota < testes[i]){

                melhorNota = testes[i];
            }

            soma += testes[i];

        }

        soma += melhorNota;
        soma /= 6;

        return media = (int)Math.round((soma*0.55)+(trab*0.40)+(alea*0.05));
    }

 }

 class NotasDouturamento extends Notas{

     @Override
     public void setAlea() {
         super.setAlea();

         int rand = random.nextInt(20)+1;
         this.alea = rand;

     }

     public int media(){

         int media;

         return media = (int)Math.round((alea*0.60)+(trab*0.40));

    }

 }


/*

-------------------------------------------------------------//--------------------------------------------------------------
3-

flow Layout - No flow layout os elementos que forem postos seram introduzidos da esqueda para a direita até já
não caber na janela, quando isto acontece passa para a linha de baixo e continua, porem
quando é so um elememto a ficar numa das linhas esse elemento é centrado, se se esticar a tabela os elementos
vão ficando na linha superior se ouver espaço.

border layout - No border layout são introduzidos elemenstos na posição norte, sul, este, oeste e centro
podem não ser todos ocupados, quando se estica a janela, o elemento na posição norte e sul só aumentam em largura, os de este
e oeste só aumentam em comprimento, e o do centro aumenta em ambos largura e comprimento.

-------------------------------------------------------------//--------------------------------------------------------------
*/