
public class Call extends Instrucao {

    private int number;
    private Etiqueta etiqueta;

    public Call(int number, Etiqueta etiqueta){
        this.number = number;
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "Call{" +
                "number=" + number +
                ", etiqueta=" + etiqueta +
                '}';
    }
}