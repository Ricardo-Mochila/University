
public class Locals extends Instrucao {

    private int arguments = 0;
    private int variables = 0;

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
        int controlLink = Main.get_machine().environmentPointer;
        Main.get_machine().otherPointer = Main.get_machine().environmentPointer;
        int acessLink = Main.get_machine().acessLinkGlobal;
        int returnAdress = Main.get_machine().calledValue;

        Main.get_machine().executionMemory.add(controlLink);
        Main.get_machine().executionMemory.add(acessLink);
        Main.get_machine().executionMemory.add(returnAdress);
        Main.get_machine().executionMemory.add(arguments);
        Main.get_machine().executionMemory.add(variables);
        Main.get_machine().environmentPointer = Main.get_machine().environmentPointer + 5 +arguments+variables;

        Main.get_machine().pointerPosition.add(controlLink);

        for (int i = 0; i < arguments; i++) {
            Main.get_machine().executionMemory.add(0);
        }
        for (int i = 0; i < variables; i++) {
            Main.get_machine().executionMemory.add(0);
        }

        if(!Main.get_machine().helpArgVars.isEmpty())
        {
            ArgVarStorage store = new ArgVarStorage();
            for(int i = 0; i < Main.get_machine().helpArgVars.size(); i++)
            {
                store = Main.get_machine().helpArgVars.get(i);
                if(store.type == 0){
                    if(store.depth == 0){
                        Main.get_machine().executionMemory.set(controlLink + 4 + store.position, store.value);
                    }
                }
            }
        }

        Main.get_machine().helpArgVars.removeAllElements();
    }

    @Override
    public String toString() {
        return "Locals{" +
                "arguments=" + arguments +
                ", variables=" + variables +
                '}';
    }
}