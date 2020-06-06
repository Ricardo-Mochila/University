
public class Set_Arg extends Instrucao{
    private int number;

    public Set_Arg(int number)
    {
        this.number=number;
    }

    public int getNumber(){return number;}

    @Override
    public void execute(){
        ArgVarStorage store= new ArgVarStorage();
        store.depth = 0;
        store.position = number;
        store.type = 0;
        store.value = Main.get_machine().stackOfEvaluation.pop();
        Main.get_machine().helpArgVars.add(store);
    }

    @Override
    public String toString() {
        return "Set_Arg{" +
                "number=" + number +
                '}';
    }
}