
public class Jump extends Instrucao {

    Etiqueta etiqueta;

    public Jump(Etiqueta etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute()
    {
        System.out.println(super.getEtiqueta1());
        //int cenas = labels.get(etiqueta);
        //System.out.println(cenas);
        //programCounter = jumpTo;
        //System.out.println(jumpTo);

    }

    @Override
    public String toString() {
        return "Jump{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
