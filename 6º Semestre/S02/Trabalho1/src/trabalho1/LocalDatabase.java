package trabalho1;

import java.sql.*;
import java.sql.ResultSet;

/**
 *
 * @author ricardomochila, inesverissimo
 */
public class LocalDatabase {

    String DB_PATH;
    String DB_USER;
    String DB_PASSWORD;

    public LocalDatabase()
    {
        DB_PATH= "./LocalData/data";
        DB_USER= "client";
        DB_PASSWORD= "";
    }
    
    public void save(String registo, String produto, String loja) {
        java.sql.Connection conn = null;
        
        try {
            // Load the HSQL Database Engine JDBC driver
            // hsqldb.jar should be in the class path or made part of the current jar
            Class.forName("org.hsqldb.jdbcDriver");

            // connect to the database
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+DB_PATH, DB_USER, DB_PASSWORD);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.err.println("PROBLEMA com o ACESSO 'a BD: "+se);
        }

        Statement st = null;
        try {
            st = conn.createStatement();    // statements
            try{
                st.executeUpdate("Create table registo(produto varchar(30), loja varchar(30));");
                st.executeUpdate("insert into registo values(LOWER('"+produto+"'),LOWER('"+loja+"'));");
            }catch(SQLException e){
                st.executeUpdate("insert into registo values(LOWER('"+produto+"'),LOWER('"+loja+"'));");    // run the query
            }
        }
        catch (SQLException se1) {
            se1.printStackTrace();
            System.err.println("PROBLEMA no insert: "+se1);
        }

       /*  try {
            ResultSet rs = null;   // para receber as "linhas" de resultado da query
            st = conn.createStatement();         // statement objects can be reused with
            // repeated calls to execute but we
            // choose to make a new one each time
            rs = st.executeQuery("select * from aluno");    // run the query
            while (rs.next()) {
                System.out.println("RESULTADO (do select): " + rs.getInt(1)+" " + rs.getString(2));
            }
            // do something with the result set.
            st.close();    // NOTE!! if you close a statement the associated ResultSet is closed too
            // -------------------------------------------------------------
            // NOTA: se precisar daqueles valores depois de fechar o Statement ou o ResultSet, 
            //  devera guarda-los numa colecao, para usar a seguir! 
            // -------------------------------------------------------------
        }
        catch (SQLException se2) {
            se2.printStackTrace();
            System.err.println("PROBLEMA no select: "+se2);
        }

        
        // finalizar a ligacao 'a BD
        // em cenarios reais, podera evitar a repeticao de abrir/fechar,
        // usando ligacoes persistentes */
        try {
            conn.close();  
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.err.println("PROBLEMA ao fechar connection");
        }
    
    }

    public void delete(String registo, String produto, String loja) {
                
        java.sql.Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+DB_PATH, DB_USER, DB_PASSWORD);
        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA com o ACESSO 'a BD: "+se);
        }
        Statement st = null;
        try {
            st = conn.createStatement();    // statements
            st.executeUpdate("delete from registo where(LOWER('"+produto+"'),LOWER('"+loja+"'));");
        }
        catch (SQLException se1) {
            System.err.println("PROBLEMAS NA REMOCAO"+se1);
        }
        try {
            conn.close();  
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA ao fechar connection");
        }
    }

    public boolean isEmpty() {

        java.sql.Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+DB_PATH, DB_USER, DB_PASSWORD);
        }catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.err.println("PROBLEMA com o ACESSO 'a BD: "+se);
        }
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("Select * from registo;");
            if(result.next()) {
                return false;
            }
            return true;
        }
        catch (SQLException se1) {
            
        }
        
        return true;
    }


    public void convertData(InvocationsInterface obj) throws java.rmi.RemoteException, Exception{
                
        java.sql.Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+DB_PATH, DB_USER, DB_PASSWORD);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.err.println("PROBLEMA com o ACESSO 'a BD: "+se);
        }
        Statement st = null;
        try {
            ResultSet rs = null;  
            st = conn.createStatement();  
            rs = st.executeQuery("Select * from registo"); 
            while (rs.next()) {
                System.out.println("RESULTADO (do select): " + rs.getString(1)+" " + rs.getString(2));
                obj.registarProduto(rs.getString(1), rs.getString(2)) ;
            }
            st.executeUpdate("Drop table registo"); 
        }
        catch (SQLException se1) {
            se1.printStackTrace();
            System.err.println("PROBLEMAS AO COPIAR DADOS"+se1);
        }
        try {
            conn.close();  
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.err.println("PROBLEMA ao fechar connection");
        }
    }
}
