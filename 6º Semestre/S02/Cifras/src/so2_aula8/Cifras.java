/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2_aula8;

import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
/**
 *
 * @author ricardomochila
 */
public class Cifras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        //Pergunta 1
        
        Provider[] providers = java.security.Security.getProviders();
        /*for(Provider p: providers){
            System.out.println(p);
        }*/
        
        //Pergunta 2
        /*for(Provider.Service service : providers[0].getServices())
        {
            System.out.println("S tipo: "+ service.getType() + "algortimo: "+service.getAlgorithm());
        }*/
        
        //Pergunta3
        /*for(String alg : Security.getAlgorithms("Cipher")){
            System.out.println("Cipher: " + alg);
        }*/
        
        //Pergunta4
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        SecretKey secretKey = kg.generateKey();
        
        System.out.println("chave para: "+ secretKey.getAlgorithm());
        
        
        //Pergunta5
      

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        /* para vários blocos usar-se-ia o método update() (multiple-part encryption)
           até ao penúltimo bloco, seguido de doFinal()
        */
        String MSG = "Férias da Pascoa";
        byte[] plaintext = MSG.getBytes();
        byte[] ciphertext = cipher.doFinal( plaintext );  // cifrar num "bloco" só

        // teste rápido para ver o q fica, que não será visível:
        System.out.println( new String(ciphertext) );
        
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plaintext2 = cipher.doFinal(ciphertext);
        System.out.println( new String(plaintext2) );
    }
    
}
