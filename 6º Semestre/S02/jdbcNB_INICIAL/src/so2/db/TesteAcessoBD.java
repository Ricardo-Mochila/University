package so2.db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author jsaias
 */
public class TesteAcessoBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        // coloque os argumentos
        
        PostgresConnector pc = new PostgresConnector( "localhost", "so2", "ricardo", "123");
        // NOTA: se isto nao fosse uma domonstacao, nao PODIA ter configuracoes no codigo fonte!!!
        
        
        // estabelecer a ligacao ao SGBD
        pc.connect();
        Statement stmt = pc.getStatement();

	// *******************
        // update/insert
        try { 
            stmt.executeUpdate("insert into personl99999 values(1,'O.MeuNome','"+new java.util.Date()+"')");
 
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
       }

	// ******************
        // query	
        try {

            ResultSet rs = stmt.executeQuery("SELECT id,name,birth,extract(hour from birth) as hh from personl99999 order by birth");

            while (rs.next()) {
                int id= rs.getInt("id");
                String name = rs.getString("name");
                java.sql.Timestamp birth= rs.getTimestamp("birth");  
                java.sql.Time time= rs.getTime("birth");
 
                System.out.println("Id: "+id+"  name: "+name+
                                   "  birth: "+birth+ "   time: "+time);
            }
            try {
                stmt.close();
                rs.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }

        
        pc.disconnect();
    }


}
