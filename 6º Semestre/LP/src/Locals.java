
public class Locals extends Instrucao {

    private int arguments;
    private int variables;

    public Locals(int arguments, int variables)
    {
        this.arguments = arguments;
        this.variables = variables;
    }

    public int getarguments(){return arguments;}
    public int getvariables(){return variables;}

    @Override
    public void execute()
    {

    }

    @Override
    public String toString() {
        return "Locals{" +
                "arguments=" + arguments +
                ", variables=" + variables +
                '}';
    }
}