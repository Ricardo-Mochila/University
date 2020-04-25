package trabalho1; 

import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.sql.SQLException;

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
        try{
            stmt.executeUpdate("Create table produto(nome varchar(30), loja varchar(30));");
            System.out.println("Foi creada a tabela de necessidades");

        }catch(Exception e){
            System.out.println("Tabela ja existe");
        }

        try { 
            stmt.executeUpdate("insert into produto values(LOWER('"+produto+"'),LOWER('"+loja+"'));");
            System.out.println("Foi realizada insercao na tabela de produtos");

            try{
                stmt.executeQuery("update necessidades set vista = TRUE where produto = "+"LOWER('"+produto+"');");
                System.out.println("Foi realizada uma actualizaçao na tabela de necessidades");
            }catch(Exception e){
                System.err.println("Tabela necessidades ainda não foi criada ou produto procurado nao existe");
            }
        }
        catch (Exception e) {
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
            result = stmt.executeQuery("Select Distinct nome, loja from produto where nome = LOWER('"+produto+"');");
            System.out.println("Foi realizada uma procura na tabela de produtos");
            while (result.next()) {
                output += "- " + result.getString(1) + " na loja " +result.getString(2) + "\n";
            }
            if(output == ""){
                return null;
            }
            return output;
        }
        catch (Exception e) {
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
            System.out.println("Foi realizada uma remocao na tabela de produtos");

            try{
                result = stmt.executeQuery("Select * from produto where nome = LOWER('"+produto+"');");
                while (result.next()) {
                    output += "O produto existe na loja "+result.getString(2) + ".\n";
                }
                return output;
            }
            catch(Exception e){
                output += "Nao existe em nenhuma loja" + "\n";
            }
            
        }
        catch (Exception e) {
            System.err.println("Problems finding product");
        }
       return null;
    }

    public int necessidadeGeral(String produto, int id) throws java.rmi.RemoteException, Exception{
        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();
        int numberId = 0;

        try{
            stmt.executeUpdate("Create table necessidades(id int, produto varchar(30), vista BOOLEAN);");
            System.out.println("Foi creada a tabela de necessidades");

        }catch(Exception e){
            System.out.println("Tabela ja existe");
        }
        if(id == 0){
            Random random = new Random();
            numberId = random.nextInt(999999);
            
            while(!check(numberId)){
                numberId = random.nextInt(999999);
            }
            id = numberId;
        }
        
        stmt.executeUpdate("insert into necessidades values("+id+",LOWER('"+produto+"'), False);");
        System.out.println("Foi realizada insercao na tabela de necessidades");


        return id;
    }

    public String checkUpdates(int id) throws java.rmi.RemoteException, Exception{

        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();
        String output = "";
        try{
            ResultSet result = stmt.executeQuery("Select * from necessidades where id = "+ id+" and vista = True;");
            
            while (result.next()) {
                output += consultarProduto(result.getString(2)) + "\n";
            }
            if(output == ""){
                return "Nao existem alteraçoes em relaçao as suas subscriçoes";
            }
            if(output != ""){
                stmt.executeUpdate("DELETE FROM necessidades WHERE id = "+ id+" and vista = True;");
            }
        }catch(Exception e)
        {
            return "Ainda nao foi criada a tabela de necessidades";
        }
        
        return output;
    }

    public boolean check(int id) throws java.rmi.RemoteException, Exception{

        PostgresConnector pc = new PostgresConnector( "localhost", "Produtos", "Admin", "123");
        
        pc.connect();
        Statement stmt = pc.getStatement();

        ResultSet result = stmt.executeQuery("Select * from necessidades where id = "+ id+";");
        if(!result.next()){
            return true;
        }
        return false;
    }

}
