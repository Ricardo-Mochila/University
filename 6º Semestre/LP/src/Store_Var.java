
public class Store_Var extends Instrucao{
    private int depth;
    private int number;

    public Store_Var(int depth, int number)
    {
        this.depth = depth;
        this.number=number;
    }

    public int getdepth(){return depth;}

    public int getNumber(){return number;}

    @Override
    public void execute(){

    }

    @Override
    public String toString() {
        return "Store_Var{" +
                "depth=" + depth +
                ", number=" + number +
                '}';
    }
}