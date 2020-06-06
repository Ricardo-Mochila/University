

public class Push_Arg extends Instrucao {

    private int depth;
    private int number;

    public Push_Arg(int depth, int number)
    {
        this.depth = depth;
        this.number = number;
    }

    public int getdepth(){return depth;}
    public int getNumber(){return number;}

    @Override
    public void execute()
    {
        if(depth == 0){
            int index = Main.get_machine().otherPointer + 4 + number;
            int value = Main.get_machine().executionMemory.get(index);
            Main.get_machine().stackOfEvaluation.push(value);
        }
        else{
            int indexOfActual = Main.get_machine().pointerPosition.indexOf(Main.get_machine().otherPointer);
            int indexOfPretended = Main.get_machine().pointerPosition.elementAt(indexOfActual-depth);
            int indexOfArgPos = indexOfPretended +  4 + Main.get_machine().executionMemory.get(Main.get_machine().otherPointer+3)+number;
            int value = Main.get_machine().executionMemory.get(indexOfArgPos);
            Main.get_machine().stackOfEvaluation.push(value);
        }
    }

    @Override
    public String toString() {
        return "Push_Arg{" +
                "depth=" + depth +
                ", number=" + number +
                '}';
    }
}