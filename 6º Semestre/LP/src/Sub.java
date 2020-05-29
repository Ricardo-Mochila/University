public class Sub extends Instrucao {

    @Override
    public void execute() {
        int value2 = stackOfEvaluation.pop();
        int value1 = stackOfEvaluation.pop();
        stackOfEvaluation.push(value1-value2);
    }

    @Override
    public String toString() {
        return "Sub";
    }
}