package trabalho.Trabalho3.listaDeContactosABP;


import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class ListaTelefonica {

    binaryTrees<Contacto> lista = new binaryTrees<>();
    int size = 0;


    public void insertContact(String nome, long numero){

        Contacto newContact = new Contacto(nome,numero);
        lista.insert(newContact);
        this.size += 1;
    }

    public void insertContact(String nome, long numero, long numero2){

        Contacto newContact = new Contacto(nome,numero,numero2);
        lista.insert(newContact);
        this.size += 1;
    }

    public void insertContact(long numero){

        Contacto newContact = new Contacto(numero);
        lista.insert(newContact);
        this.size += 1;
    }

    public void insertContact(long numero, long numero2){

        Contacto newContact = new Contacto(numero, numero2);
        lista.insert(newContact);
        this.size += 1;
    }

    public void insertContact(String nome){

        Contacto newContact = new Contacto(nome);
        lista.insert(newContact);
        this.size += 1;
    }

    public void insertContact(){

        Contacto newContact = new Contacto();
        lista.insert(newContact);
        this.size += 1;
    }

    public void removeContact(String name){

        Iterator<Contacto> i = lista.iterator();
        int cis = 0;
        Contacto c;
        while(i.hasNext()){
            c = (Contacto)i.next();
            if(c.Name.equalsIgnoreCase(name)){
                lista.remove(c);
                this.size -= 1;
                break;
            }
            cis++;
        }

    }

    public void removeContact(long num){

        Iterator<Contacto> i = lista.iterator();
        int cis = 0;
        Contacto c;
        while(i.hasNext()){
            c = (Contacto)i.next();
            if(c.Numero1 == num || c.Numero2 == num ){
                lista.remove(c);
                this.size -= 1;
                break;
            }
            cis++;

        }
    }

    public void printContactList(){

        Iterator<Contacto> i = lista.iteratorEmOrdem();
        int cis = 0;
        Contacto c;
        while(i.hasNext()){
            c = (Contacto)i.next();
            if(c.Numero2 != 0){
                System.out.println("Nome: "+c.Name + " -- Numeros: " +c.Numero1 + "; " + c.Numero2 );
                cis++;
            }
            else{
                System.out.println("Nome: "+c.Name + " -- Numero: " +c.Numero1);
                cis++;

            }


        }

    }

    public void editar(String name ){

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("What Changes do You Wanna Make? \n" +
                    "1- Change Number 1 \n" +
                    "2- Change Number 2 \n" +
                    "3- Create a new number \n" +
                    "4- Change contact name");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            Iterator<Contacto> i = lista.iterator();
            int cis = 0;
            Contacto c;


            while (i.hasNext()) {
                c = (Contacto) i.next();
                if (c.Name.equalsIgnoreCase(name)) {
                    try {
                        switch (choice) {
                            case 1:

                                System.out.println("To what number do you wanna change? ");
                                Long num = scanner.nextLong();
                                c.Numero1 = num;
                                break;
                            case 2:

                                System.out.println("To what number do you wanna change? ");
                                num = scanner.nextLong();
                                c.Numero2 = num;
                                break;

                            case 3:

                                System.out.println("What's the new number? ");
                                num = scanner.nextLong();
                                c.Numero2 = num;
                                break;

                            case 4:

                                System.out.println("New Contact Name ");
                                String name1 = scanner.next();
                                long num1 = c.Numero1;
                                long num2 = c.Numero2;
                                lista.remove(c);
                                lista.insert(new Contacto(name1, num1, num2));
                                break;

                            default:

                                System.out.println("--This is not an Option--\n");
                                editar(name);

                        }
                    } catch (InputMismatchException e) {

                        System.out.println("--This is not an Option--\n");
                        editar(name);
                    }
                }
                cis++;
            }
        }catch (InputMismatchException e) {

            System.out.println("--This is not an Option--\n");
            editar(name);
        }

    }

    public void editar(long numb){

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWhat Changes do You Wanna Make? \n" +
                    "1- Change Number 1 \n" +
                    "2- Change Number 2 \n" +
                    "3- Create a new number \n" +
                    "4- Change contact name");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();


            Iterator<Contacto> i = lista.iterator();
            int cis = 0;
            Contacto c;
            while (i.hasNext()) {
                c = (Contacto) i.next();

                if (c.Numero1 == numb || c.Numero2 == numb) {
                    try {
                        switch (choice) {

                            case 1:

                                System.out.print("To what number do you wanna change? ");

                                Long num = scanner.nextLong();
                                c.Numero1 = num;

                                break;
                            case 2:

                                System.out.print("To what number do you wanna change? ");
                                num = scanner.nextLong();
                                c.Numero2 = num;
                                break;

                            case 3:

                                System.out.print("What's the new number? ");
                                num = scanner.nextLong();
                                c.Numero2 = num;
                                break;

                            case 4:

                                System.out.print("New Contact Name: ");
                                String name1 = scanner.next();
                                long num1 = c.Numero1;
                                long num2 = c.Numero2;
                                lista.remove(c);
                                lista.insert(new Contacto(name1, num1, num2));
                                break;

                            default:
                                System.out.println("--This is not an Option--\n");
                                editar(numb);

                        }
                    } catch (InputMismatchException e) {

                        System.out.println("--This is not an Option--\n");
                        editar(numb);
                    }


                    break;
                }
                cis++;
            }
        }catch (InputMismatchException e) {

            System.out.println("--This is not an Option--\n");
            editar(numb);
        }

    }

    public String chamador(long num){

        Iterator<Contacto> i = lista.iterator();
        int cis = 0;
        Contacto c;
        while(i.hasNext()){
            c = (Contacto)i.next();
            if(c.Numero1 == num || c.Numero2 == num ){
                return c.Name +"\n" + num;
            }
            cis++;

        }
        return "Calling\n"+"Unknown Number\n" + num;

    }

    public static void main(String[] args) {

        ListaTelefonica telf = new ListaTelefonica();
        telf.insertContact("Joaquim", 96290192, 981290); //3
        telf.insertContact("manel", 962901911);
        telf.insertContact("francisco", 96290593); //1
        telf.insertContact("Antonio", 96290192, 981290); //3
        telf.insertContact("mariana", 962901911);
        telf.insertContact("ricardo", 96290192, 981290); //3
        telf.insertContact("ines", 962901911);
        telf.insertContact("joao", 96290593); //1
        telf.insertContact("pedro", 96290593); //1

        telf.printContactList();



    }

}

