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
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+DB_PATH, DB_USER, DB_PASSWORD);
        }
        catch (ClassNotFoundException cnfe) {
            System.err.println("PROBLEMA por não encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na secção Libraries");
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA com o ACESSO à BD: "+se);
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
            System.err.println("PROBLEMA no insert: "+se1);
        }

    
        try {
            conn.close();  
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA ao fechar connection");
        }
    
    }

    public void delete(String produto, String loja) {
                
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
            st.executeUpdate("delete from registo where produto = LOWER('"+produto+"')and loja = LOWER('"+loja+"');");
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
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
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
            System.err.println("PROBLEMA por nao encontrar uma classe! Veja se tem o driver na classpath!");
            System.err.println("Adicionar hsqldb.jar na seccao Libraries");
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA com o ACESSO 'a BD: "+se);
        }
        Statement st = null;
        try {
            ResultSet rs = null;  
            st = conn.createStatement();  
            rs = st.executeQuery("Select * from registo"); 
            while (rs.next()) {
                obj.registarProduto(rs.getString(1), rs.getString(2)) ;
                delete(rs.getString(1), rs.getString(2));
            }
        }
        catch (SQLException se1) {
            System.err.println("PROBLEMAS AO COPIAR DADOS"+se1);
        }
        try {
            conn.close();  
        }
        catch (SQLException se) {
            System.err.println("PROBLEMA ao fechar connection");
        }
    }
}
