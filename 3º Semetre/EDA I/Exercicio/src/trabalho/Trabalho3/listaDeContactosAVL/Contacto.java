package Trabalho3.listaDeContactosAVL;

public class Contacto implements Comparable<Contacto> {

    String Name;
    long Numero1;
    long Numero2;

    public Contacto(){
        Name = "Unknown";
        Numero1 = 000000000;
    }

    public Contacto(String n, long num){
        Name = n.toUpperCase();
        Numero1 = num;
    }

    public Contacto(String n, long num, long num2){
        Name = n.toUpperCase();
        Numero1 = num;
        Numero2 = num2;
    }

    public Contacto(long num){
        Name = "Unknown";
        Numero1 = num;

    }

    public Contacto(String n){
        Name = n.toUpperCase();
        Numero1 = 000000000;

    }

    public Contacto(long num, long num2){
        Name = "Unknown";
        Numero1 = num;
        Numero2 = num2;
    }

    public int compareTo(Contacto o) {

        return this.Name.compareTo(o.Name);

    }
}
