// Programa principal para a implementacaoo da maquina abstracta TISC.


public class Main {
  public static void main(String args[]) throws Exception
  {
    parser aParser = new parser();
    TISC maquina;

    maquina = (TISC) aParser.parse().value;
    if (maquina != null){
      maquina.run();
    }
  }
}
