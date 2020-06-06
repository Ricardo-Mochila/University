
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
        int value2 = Main.get_machine().stackOfEvaluation.pop();
        int value1 = Main.get_machine().stackOfEvaluation.pop();

        if(value1 == value2){
            Main.get_machine().programCounter = Main.get_machine().labels.get(etiqueta.etiqueta)-1;
            
        }
    }

    @Override
    public String toString() {
        return "Jeq{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
