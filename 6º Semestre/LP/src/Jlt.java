
public class Jlt extends Instrucao {

    Etiqueta etiqueta;

    public Jlt(Etiqueta etiqueta)
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
        return "Jlt{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
