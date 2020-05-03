
public class Jeq extends Instrucao {

    Etiqueta etiqueta;

    public Jeq(Etiqueta etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public Etiqueta getEtiqueta(){return etiqueta;}

    @Override
    public void execute()
    {

    }

    @Override
    public String toString() {
        return "Jeq{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
