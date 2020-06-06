
public class Add extends Instrucao {

    @Override
    public void execute() {
        int value2 = Main.get_machine().stackOfEvaluation.pop();
        int value1 = Main.get_machine().stackOfEvaluation.pop();
        Main.get_machine().stackOfEvaluation.push(value1+value2);
    }

    @Override
    public String toString() {
        return "Add";
    }
}