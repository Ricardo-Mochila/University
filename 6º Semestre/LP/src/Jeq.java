
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
        int value2 = stackOfEvaluation.pop();
        int value1 = stackOfEvaluation.pop();

        if(value1 == value2){
            //programCounter = labels.get(etiqueta);
        }
    }

    @Override
    public String toString() {
        return "Jeq{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
