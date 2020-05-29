
public class Print extends Instrucao{

    int number;

    public int getTopValue(){return number;}

    @Override
    public void execute(){
        int toPrint = stackOfEvaluation.pop();
        System.out.println(toPrint);
    }

    @Override
    public String toString() {
        return "Print{" +
                "number=" + number +
                '}';
    }
}