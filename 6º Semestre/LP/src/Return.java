
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
        /* System.out.println("OtherPointer: " +  Main.get_machine().otherPointer);
        System.out.println("size: " +  Main.get_machine().environmentPointer);

        System.out.println("Cl: " +  Main.get_machine().executionMemory.get(Main.get_machine().otherPointer));
        System.out.println("AL: " +  Main.get_machine().executionMemory.get(Main.get_machine().otherPointer+1));
        System.out.println("RA: " +  Main.get_machine().executionMemory.get(Main.get_machine().otherPointer+2));
        System.out.println("NA: " +  Main.get_machine().executionMemory.get(Main.get_machine().otherPointer+3));
        System.out.println("NV: " +  Main.get_machine().executionMemory.get(Main.get_machine().otherPointer+4)); */

        for(int i = Main.get_machine().otherPointer-1; i < size; i++ ){
            Main.get_machine().executionMemory.remove(Main.get_machine().otherPointer);
        }
       
        Main.get_machine().environmentPointer = newPointer;        
        
        Main.get_machine().otherPointer = newPointer;

       /*  System.out.println("OtherPointer: " +  Main.get_machine().otherPointer);
        System.out.println("Ev: " +  Main.get_machine().environmentPointer); */
       
        //System.out.println(Main.get_machine().otherPointer);
        if( Main.get_machine().otherPointer == -1){
            System.exit(0);
        }

    }

    @Override
    public String toString() {
        return "return";
    }
}