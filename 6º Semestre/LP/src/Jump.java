
public class Jump extends Instrucao {

    Etiqueta etiqueta;

    public Jump(Etiqueta etiqueta)
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
        return "Jump{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
