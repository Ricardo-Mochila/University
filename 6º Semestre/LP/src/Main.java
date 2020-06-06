
public class Main{

  private static TISC machine;

  public static void main(String args[]) throws Exception
  {
    parser aParser = new parser();
    machine = (TISC) aParser.parse().value;

    if (machine != null){
      machine.run();
    }
   
  }

  public static TISC get_machine(){
    return machine;
  }

}
