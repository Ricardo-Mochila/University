
public class Etiqueta extends Instrucao{

    String etiqueta;

    public Etiqueta( String etiqueta){
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute(){

    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "etiqueta='" + etiqueta + '\'' +
                '}';
    }
}