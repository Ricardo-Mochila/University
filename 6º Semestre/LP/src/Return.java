
public class Return extends Instrucao {

    @Override
    public void execute() {

        int index = Main.get_machine().otherPointer + 2;
        int value = Main.get_machine().executionMemory.get(index);
        int index1 = Main.get_machine().pointerPosition.indexOf(Main.get_machine().otherPointer);
        if(Main.get_machine().otherPointer != 0){
            Main.get_machine().otherPointer =  Main.get_machine().pointerPosition.get(index1-1);
            Main.get_machine().pointerPosition.remove(index1);
        }
        //system.out.println("other pointer"+ Main.get_machine().otherPointer);
        if( value == 0){
            System.exit(0);
        }
        Main.get_machine().programCounter = value-1;

    }

    @Override
    public String toString() {
        return "return";
    }
}