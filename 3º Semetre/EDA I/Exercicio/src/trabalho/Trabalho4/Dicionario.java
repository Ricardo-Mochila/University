package trabalho.Trabalho4;

import java.io.*;
import java.util.StringTokenizer;

public class Dicionario {

    //Trabalho4.linHashTable<String> dicionario = new linHashTable<>();
    trabalho.Trabalho4.QuadHashTable<String> dicionario = new QuadHashTable<>();

    public void readDicionary() throws IOException {

        FileInputStream recieve = new FileInputStream("/Users/mochila/Desktop/UÉ/3º Semetre/EDA I/Exercicio/wordlist-ao-20101027_2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(recieve));

        String strLine;
        int c = 1;

        while ((strLine = br.readLine()) != null){

            dicionario.insere(strLine);
            c++;

        }
        br.close();
    }

    public void readText() throws IOException {

        FileInputStream user = new FileInputStream("/Users/mochila/Desktop/UÉ/3º Semetre/EDA I/Exercicio/userInput.txt");
        BufferedReader b = new BufferedReader(new InputStreamReader(user));

        String strLine;
        int c = 1;
        while ((strLine = b.readLine()) != null)   {
            StringTokenizer st= new StringTokenizer(strLine, " ?,!;.\'1234567890)(");

            while(st.hasMoreTokens()){
                String token = st.nextToken();

                if(dicionario.procurar(token) == null){
                    if(dicionario.procurar(token.toLowerCase()) == null){
                        System.out.println("\nPalavra Errada " +token+", linha " + c);

                        if(sugerirRemove(token) != ""){
                            System.out.println("Palavra sugerida " + sugerirRemove(token));
                        }

                        else if(sugerirTrocar(token) != ""){
                            System.out.println("Palavra sugerida " + sugerirTrocar(token));
                        }

                        else if(sugerirAdd(token) != ""){
                            System.out.println("Palavra sugerida " + sugerirAdd(token));
                        }

                        else{
                            System.out.println("Sem sujestões possiveis");
                        }
                    }

                }
            }
            c++;

        }

        b.close();
    }

    public String sugerirTrocar(String palavra){

        String output = "";

        for(int i = 0; i < palavra.length()-1; i++){
            StringBuilder st = new StringBuilder(palavra);

            st.setCharAt(i, st.charAt(i + 1));
            st.setCharAt(i + 1, palavra.charAt(i));

            for(int c = 0; c < dicionario.size; c++){

                if(dicionario.procurar(st.toString())!= null){
                    output += st + ", ";
                }

            }
        }
        return output;
    }


    public String sugerirAdd (String palavra){

        int i = 0;
        int c = 0;
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXZúÚÉéíÍáÁàÀã".toCharArray();
        String a = "";
        String output = "";

        while(c <= palavra.length()) {

            for(char j : alfabeto){
                String result = palavra.substring(0, c) + j + palavra.substring(c);

                if(dicionario.procurar(result)!= null){

                    if (result.length() == palavra.length() + 1) {
                        output = output  + result + ", ";

                    }
                }
            }
            c++;
        }
        return output;
    }

    public String sugerirRemove (String palavra){

        String a = "";
        String output = "";

        int c = 1;

        while(true){
            while(c < palavra.length()) {

                String result = palavra.substring(0,c)+palavra.substring(c+1);

                if(dicionario.procurar(result)!= null){
                    if (result.length() <= palavra.length() - 1) {
                        output = output  + result + ", ";
                    }
                }

                c++;
            }
            break;
        }

        return output;
    }




    public static void main(String[] args) throws IOException {
        Dicionario a = new Dicionario();
        a.readDicionary();
        a.readText();
        //System.out.println(a.dicionario.procurar("co"));


    }

}
