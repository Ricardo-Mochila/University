/*
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;

public class Exercicio12_6 {
    public static void main(String[] args) throws IOException {

        File outfile = new File("palavras_do_utilizador.txt");
        FileOutputStream outFileStream = new FileOutputStream(outfile);
        PrintWriter outStream = new PrintWriter(outFileStream);
        Scanner scanner = new Scanner(System.in);


        while(scanner.hasNext()){
            String user = scanner.nextLine();
            if (user.equalsIgnoreCase("stop")) {
                break;
            }

            outStream.write(user+"\n");
        }
        outStream.close();




    }
}*/