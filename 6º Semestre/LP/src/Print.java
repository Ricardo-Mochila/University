
public class Print extends Instrucao{

    int number;

    public int getTopValue(){return number;}

    @Override
    public void execute(){

    }

    @Override
    public String toString() {
        return "Print{" +
                "number=" + number +
                '}';
    }
}