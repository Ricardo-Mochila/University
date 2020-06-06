
public class Print extends Instrucao{

    int number;

    public int getTopValue(){return number;}

    @Override
    public void execute(){
        try {
            int toPrint = Main.get_machine().stackOfEvaluation.pop();
            System.out.print(toPrint);
        } catch (Exception e) {
            System.out.println("There is nothing to print");
        }
        ;
        
    }

    @Override
    public String toString() {
        return "Print{" +
                "number=" + number +
                '}';
    }
}