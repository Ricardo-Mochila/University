
public class Call extends Instrucao {

    private int number;
    private Etiqueta etiqueta;

    public Call(int number, Etiqueta etiqueta){
        this.number = number;
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute() {
        //System.out.println("number:" + number);
        if(number == -1){
            Main.get_machine().calledValue = Main.get_machine().programCounter + 1;
            Main.get_machine().programCounter = Main.get_machine().labels.get(etiqueta.etiqueta)-1;
            if( Main.get_machine().otherPointer == -1){
                Main.get_machine().acessLinkGlobal = Main.get_machine().otherPointer + 1;
            }
            else{
                Main.get_machine().acessLinkGlobal = Main.get_machine().otherPointer;
            }
            
        }
        else if(number == 0){
            Main.get_machine().calledValue = Main.get_machine().programCounter + 1;
            Main.get_machine().programCounter = Main.get_machine().labels.get(etiqueta.etiqueta)-1;
            int acessLinkBrother = Main.get_machine().executionMemory.get(Main.get_machine().otherPointer + 1);
            if( acessLinkBrother == -1){
                Main.get_machine().acessLinkGlobal = acessLinkBrother + 1;
            }
            else{
                Main.get_machine().acessLinkGlobal = acessLinkBrother;
            }
        }
        else{
            Main.get_machine().calledValue = Main.get_machine().programCounter + 1;
            Main.get_machine().programCounter = Main.get_machine().labels.get(etiqueta.etiqueta)-1;
            int pointer = Main.get_machine().otherPointer;
            int acessLinkBrother = -1;
            for(int i = 0; i <= number; i++){
                acessLinkBrother = Main.get_machine().executionMemory.get(pointer + 1);
                pointer = acessLinkBrother;
            }
            if( acessLinkBrother == -1){
                Main.get_machine().acessLinkGlobal = acessLinkBrother + 1;
            }
            else{
                Main.get_machine().acessLinkGlobal = acessLinkBrother;
            }
        }
        
    }

    @Override
    public String toString() {
        return "Call{" +
                "number=" + number +
                ", etiqueta=" + etiqueta +
                '}';
    }
}