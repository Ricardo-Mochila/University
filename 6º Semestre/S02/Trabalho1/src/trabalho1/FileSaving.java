package trabalho1; 

/**
 *
 * @author ricardomochila, inesverissimo
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.util.Scanner;

public class FileSaving implements FileSavingInterface{
    
    public void save(int id){

        try {
            // Creating an object of a file
            File myObj = new File("./numberOfSubscribe.txt"); 
            if (!myObj.exists()) {
                myObj.createNewFile();
            }

            try {
                FileWriter myWriter = new FileWriter("./numberOfSubscribe.txt"); 
                // Writes this content into the specified file
                myWriter.write(String.valueOf(id));
                myObj.setReadOnly();
                myWriter.close(); 

            } catch (IOException e) {
                System.out.println("Ocorreu um erro.");
                e.printStackTrace();
            }

        } catch (IOException e) {
           System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        } 
    }

    public int load(){
        try {
            File file = new File("./numberOfSubscribe.txt"); 
            Scanner myReader = new Scanner(file);
            String idString = myReader.next();
            int id = Integer.parseInt(idString); 
            myReader.close();
            return id; 

        } catch (IOException e) {
            return 0;
        }
     
    }
}