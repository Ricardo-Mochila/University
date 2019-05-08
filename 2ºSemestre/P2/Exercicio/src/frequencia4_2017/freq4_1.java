package frequencia4_2017;

import java.util.*;

public class freq4_1 {

    public static void main(String[] args) {
        Notas notinhas = new Notas();
        notinhas.setter(14,12,9,6,20,16);
        System.out.println("Nota de um aluno: "+notinhas.notas()+" "+ notinhas.get_dados());

        turma turminhas = new turma();
        turminhas.insere(37762, 20);
        turminhas.insere(20122, 10);

        System.out.println("Media turma: "+turminhas.media());

        NotasDouturamento notinhasdout = new NotasDouturamento();
        notinhasdout.setter(16);
        System.out.println("Nota douturamento: "+ notinhasdout.media());

        NotaMestrado notinhasmest = new NotaMestrado();

        notinhasmest.setter(20,19,15,12,10,15);
        System.out.println("Nota mestrado: "+notinhasmest.media());
    }


}

class Notas extends freq4_1{


    public float[] testes = new float[5];
    public float trab, dados;
    public int media;


    public void setter(float teste1, float teste2, float teste3, float teste4, float teste5 , float trab){

        testes[0] = teste1;
        testes[1] = teste2;
        testes[2] = teste3;
        testes[3] = teste4;
        testes[4] = teste5;

        this.trab = trab;

        Random random = new Random();
        int rand1 = random.nextInt(6)+1;
        int rand2 = random.nextInt(6)+1;

        this.dados = rand1+rand2+6;

    }

    public float get_trab(){

        return this.trab;

    }

    public float[] get_testes(){

        return this.testes;

    }

    public float get_dados(){

        return this.dados;

    }

    public int notas(){

        int soma = 0;

        for(int i= 0; i < testes.length; i++){

            soma += testes[i];

        }

        soma = soma/5;

        return media = (int)Math.round((dados*0.35)+(soma*0.40)+(trab*0.25));

    }


}

class turma{

    Map catalog = new TreeMap<Integer, Notas>();

    void insere(int numeros, int Notas){
        catalog.put(numeros, Notas);
    }

    public int media(){

        int soma = 0;
        Set set = catalog.entrySet();
        Iterator iterator = set.iterator();

        for (int i = 0; i<catalog.size(); i++){
            Map.Entry mentry = (Map.Entry)iterator.next();
            soma += (int)mentry.getValue();

        }

        return soma/catalog.size();

    }

}

class NotaLicenciatura extends Notas {

    public NotaLicenciatura() {
        super();
    }
}

class NotaMestrado extends Notas {


    public int media(){
        float nota_maxima = 0;
        float soma = 0;
        int media = 0;
        for(int i = 0; i<testes.length; i++){

            if(nota_maxima<testes[i]){

                nota_maxima = testes[i];

            }
        }

        for(int i = 0; i<testes.length; i++){

            soma += testes[i];

        }

        soma = (soma + nota_maxima)/6;

        return media = (int)(Math.round((soma*0.55)+(dados*0.05)+(trab*0.45)));


    }
}

class NotasDouturamento extends Notas {

    public void setter(float trab){

        this.trab = trab;

    }

    public int media(){

        Random random = new Random();
        dados = random.nextInt(20)+1;

        return media = (int)(Math.round((dados*0.60)+(trab*0.40)));


    }

}