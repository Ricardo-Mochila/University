/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author mochila
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket s = new Socket("127.0.0.1", 8080);
        
        
        InetAddress inet = InetAddress.getByName("host.di.uevora.pt");
        System.out.println ("IP : " + inet.getHostAddress());
    }
    
}
