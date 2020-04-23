package trabalho1; 

import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.Statement;

public class Invocations extends UnicastRemoteObject implements InvocationsInterface, java.io.Serializable {

    // deve existir um construtor
    public Invocations() throws java.rmi.RemoteException {
        super();
    }
    
    /**
     * devolve a primeira palavra da frase recebida
     *
     * @param s
     * @return
     * @throws java.rmi.RemoteException
     */
    
    public void registarProduto(String produto, String loja) throws java.rmi.RemoteException, Exception{
        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();

        try { 
            stmt.executeUpdate("insert into produto values(LOWER('"+produto+"'),LOWER('"+loja+"'));");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
       }
    }

    public String consultarProduto(String produto) throws java.rmi.RemoteException, Exception{
        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();
        ResultSet result;
        String output = "";
        try {
            result = stmt.executeQuery("Select * from produto where nome = LOWER('"+produto+"');");
            while (result.next()) {
                output += "- " + result.getString(1) + " na loja " +result.getString(2) + "\n";
            }
            if(output == ""){
                return null;
            }
            return output;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems finding product");
       }
       return null;
    }

    public String necessidadeProduto(String produto, String loja) throws java.rmi.RemoteException, Exception{
        
        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();
        ResultSet result;
        String output = "";

        try {
            stmt.executeUpdate("DELETE FROM produto WHERE nome = LOWER('"+produto+"') AND loja = LOWER('"+loja+"');");

            try{
                result = stmt.executeQuery("Select * from produto where nome = LOWER('"+produto+"');");
                while (result.next()) {
                    output += "O pedido está registado para a loja " + loja + " mas existe na loja "+result.getString(2) + ".\n";
                }
                return output;
            }
            catch(Exception e){
                output += "Nao existe em nenhuma loja" + "\n";
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems finding product");
        }
       return null;
    }

    public String necessidadeGeral(String produto) throws java.rmi.RemoteException, Exception{
        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();

        return "Cagalhones" ;
        /* String output = consultarProduto(String produto);
                se nao existe o produto em nenhuma loja
                System.out.println("Produto em Falta");

                Regista a necessidade em todas as lojas
            */

    }

}
