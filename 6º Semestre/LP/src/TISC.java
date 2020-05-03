
// Tiny Instruction Set Computer

import java.util.ArrayList;
import java.util.Hashtable;

public class TISC {
  private ArrayList<Instrucao> instructionMemory;
  private Hashtable<Etiqueta, Integer> labels;
  private int numberOfInstructions;

  public TISC(){
    instructionMemory = new ArrayList<>();
    labels = new Hashtable<>();
    numberOfInstructions = 0;
  }

  public void run() {
    printInstructionMemory();
  }

  public void addInsctruction(Instrucao newInstruction) {
    instructionMemory.add(newInstruction);
    numberOfInstructions++;
  }

  public void addLabel(Etiqueta newLabel){
    labels.put(newLabel, numberOfInstructions);
  }

  public void printInstructionMemory(){
    for(int i=0; i<instructionMemory.size(); i++)
    {
      System.out.println(i + ": " + instructionMemory.get(i));
    }
  }

  public void printLabels(){
    System.out.println(labels.toString());
  }

}
