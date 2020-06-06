
public class Call extends Instrucao {

    private int number;
    private Etiqueta etiqueta;

    public Call(int number, Etiqueta etiqueta){
        this.number = number;
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute() {
        Main.get_machine().calledValue = Main.get_machine().programCounter + 1;
        Main.get_machine().programCounter = Main.get_machine().labels.get(etiqueta.etiqueta)-1;
    }

    @Override
    public String toString() {
        return "Call{" +
                "number=" + number +
                ", etiqueta=" + etiqueta +
                '}';
    }
}