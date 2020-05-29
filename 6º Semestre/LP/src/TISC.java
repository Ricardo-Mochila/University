import java.util.*;

public class TISC {
    public ArrayList<Instrucao> instructionMemory;
    public static int numberOfInstructions;
    public int programCounter;
    int environmentPointer;
    public static Stack<Integer> stackOfEvaluation;
    public  Stack<Integer> labels;
    public static Vector<Integer> executionMemory;

    public TISC(){
        instructionMemory = new ArrayList<>();
        numberOfInstructions = 0;
        stackOfEvaluation = new Stack<>();
        labels = new Stack<>();
        executionMemory = new Vector<>();
        programCounter = 0;
        environmentPointer = 0;
    }

    public void run() {
        //printInstructionMemory();
        runInstruction();
    }

    public void addInsctruction(Instrucao newInstruction) {
        instructionMemory.add(newInstruction);
        numberOfInstructions++;
    }

    public void addLabel(Etiqueta newLabel){
        LabelInstruction label = new LabelInstruction();
        label.etiqueta = newLabel;
        label.value = numberOfInstructions;
        Random random = new Random();
        labels.push(random.nextInt());
        System.out.println(labels.size());
    }

    public void runInstruction(){
        for(programCounter = 0; programCounter<instructionMemory.size(); programCounter++)
        {
            instructionMemory.get(programCounter).execute();
        }
    }

    public void printInstructionMemory(){
        for(int i=0; i<instructionMemory.size(); i++)
        {
            System.out.println(i + ": " + instructionMemory.get(i));
        }
    }

    public int getEtiqueta1()
    {
        return this.labels.size();
    }

    public void printLabels(){
        System.out.println(labels.toString());
    }

}


class LabelInstruction{
    Etiqueta etiqueta;
    int value;
}