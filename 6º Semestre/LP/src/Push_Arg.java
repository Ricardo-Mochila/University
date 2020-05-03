

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

    }

    @Override
    public String toString() {
        return "Push_Arg{" +
                "depth=" + depth +
                ", number=" + number +
                '}';
    }
}