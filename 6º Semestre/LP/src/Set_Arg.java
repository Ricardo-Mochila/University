
public class Set_Arg extends Instrucao{
    private int number;

    public Set_Arg(int number)
    {
        this.number=number;
    }

    public int getNumber(){return number;}

    @Override
    public void execute(){
       
        Main.get_machine().helpArgVars.add(number);
    }

    @Override
    public String toString() {
        return "Set_Arg{" +
                "number=" + number +
                '}';
    }
}