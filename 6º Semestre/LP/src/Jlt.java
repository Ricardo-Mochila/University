
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
        int value2 = stackOfEvaluation.pop();
        int value1 = stackOfEvaluation.pop();

        if(value1 < value2){
            //programCounter = labels.get(etiqueta);
        }
    }

    @Override
    public String toString() {
        return "Jlt{" +
                "etiqueta=" + etiqueta +
                '}';
    }
}
