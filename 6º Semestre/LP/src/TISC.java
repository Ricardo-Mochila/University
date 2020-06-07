import java.util.*;

public class TISC {
    public int numberOfInstructions;
    public int environmentPointer;
    public int acessLinkGlobal;
    public int programCounter;
    public int otherPointer;
    public int calledValue;
    public int endPointer;
    public ArrayList<Instrucao> instructionMemory;
    public Stack<Integer> stackOfEvaluation;
    public Vector<Integer> executionMemory;
    public Vector<ArgVarStorage> helpArgVars;
    public HashMap<String, Integer> labels;

    public TISC(){
        numberOfInstructions = 0;
        environmentPointer = -1;
        acessLinkGlobal = -1;
        programCounter = 0;
        otherPointer = 0;
        calledValue = 0;
        endPointer = 0;
        instructionMemory = new ArrayList<>();
        stackOfEvaluation = new Stack<>();
        executionMemory = new Vector<>();
        helpArgVars = new Vector<>();
        labels = new HashMap<>();
    }

    public void run() {
        runInstruction();
    }

    public void addInsctruction(Instrucao newInstruction) {
        instructionMemory.add(newInstruction);
        numberOfInstructions++;
    }

    public void addLabel(Etiqueta newLabel){
        labels.put(newLabel.etiqueta, numberOfInstructions);
    }

    public void runInstruction(){

        for(programCounter = 0; programCounter<instructionMemory.size(); programCounter++)
        {
            instructionMemory.get(programCounter).execute();
            //printExectionMemory();
            //System.out.println(otherPointer);
        }
        
    }

    public void printInstructionMemory(){
        for(int i=0; i<instructionMemory.size(); i++)
        {
            System.out.println(i + ": " + instructionMemory.get(i));
        }
    }

    public void printStackOfEvaluation(){
        for(int i=0; i<stackOfEvaluation.size(); i++)
        {
            System.out.println("stack: " + stackOfEvaluation.get(i));
        }
    }

    public void printExectionMemory(){
        for(int i=0; i<executionMemory.size(); i++)
        {
            System.out.println("Memory "+ i + " :" + executionMemory.get(i));
        }
    }

    public void printLabels(){
        
        System.out.println(labels.entrySet() + "\n");
    }

}

class ArgVarStorage{
    int type; //arg = 0; var = 1
    int depth;
    int position;
    int value;
}