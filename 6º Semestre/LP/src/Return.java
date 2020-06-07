
public class Return extends Instrucao {

    @Override
    public void execute() {

        int index = Main.get_machine().otherPointer + 2;
        int value = Main.get_machine().executionMemory.get(index);
        Main.get_machine().programCounter = value-1;
        int numArgs = Main.get_machine().executionMemory.get(Main.get_machine().otherPointer + 3);
        int numVars = Main.get_machine().executionMemory.get(Main.get_machine().otherPointer + 4);
        int newPointer = Main.get_machine().executionMemory.get(Main.get_machine().otherPointer);

        int size = Main.get_machine().otherPointer + 4 + numArgs + numVars;

        for(int i = Main.get_machine().otherPointer-1; i < size; i++ ){
            Main.get_machine().executionMemory.remove(Main.get_machine().otherPointer);
        }
       
        Main.get_machine().environmentPointer = newPointer;        
        Main.get_machine().endPointer = Main.get_machine().otherPointer;
        Main.get_machine().otherPointer = newPointer;

        if( Main.get_machine().otherPointer == -1){
            System.exit(0);
        }

    }

    @Override
    public String toString() {
        return "return";
    }
}