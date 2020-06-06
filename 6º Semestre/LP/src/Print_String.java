
public class Print_String extends Instrucao{

    String stringToPrint;

    public  Print_String(String stringToPrint){this.stringToPrint =  stringToPrint;}

    @Override
    public void execute(){
        System.out.print(stringToPrint);
    }

    @Override
    public String toString() {
        return "Print_String{" +
                "stringToPrint='" + stringToPrint + '\'' +
                '}';
    }
}