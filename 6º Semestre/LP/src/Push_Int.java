import java.awt.image.TileObserver;

public class Push_Int extends Instrucao {

    private int number;

    public Push_Int(int number)
    {
        this.number = number;
    }

    public int getNumber(){return number;}

    @Override
    public void execute()
    {
        Main.get_machine().stackOfEvaluation.push(number);
    }

    @Override
    public String toString() {
        return "Push_Int{" +
                "number=" + number +
                '}';
    }
}