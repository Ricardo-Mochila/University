public class Jump extends Instrucao {

    Etiqueta etiqueta;

    public Jump(Etiqueta etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    @Override
    public void execute()
    {
        if(Main.get_machine().labels.get(etiqueta.etiqueta) != null ){
            
            int jumpTo = Main.get_machine().labels.get(etiqueta.etiqueta);
            Main.get_machine().programCounter = jumpTo -1;
        }
        //System.out.println(Main.get_machine().stackOfEvaluation.size());

    }

    @Override
    public String toString() {
        return "Jump{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
