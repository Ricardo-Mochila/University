package so2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private int serverPort;	
    
    public EchoServer(int p) {
	serverPort= p;		
    }
    
    public static void main(String[] args) throws IOException{
	System.err.println("SERVER...");
	if (args.length<1) {
	    System.err.println("Missing parameter: port number");	
	    System.exit(1);
	}
	int p=0;
	try {
	    p= Integer.parseInt( args[0] );
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.exit(2);
	}
	
	
	EchoServer serv= new EchoServer(p);
	serv.servico();   // activa o servico
    }

    
    // activa o servidor no porto indicado em "serverPort"
    public void servico() throws IOException {
	
	// exercicio 2: inicializar um socket para aceitar ligacoes...
        
        ServerSocket s = new ServerSocket(serverPort);
        
        while(true){
            Socket connection = s.accept();
                       
            /*byte[] output = new byte[256];
            int lidos = socketIn.read(output);
            
            InputStream socketIn= connection.getInputStream();
        
            String resp = new String(output,0,lidos);
            
            System.out.println("Recebido: "+resp);
            OutputStream socketOut= connection.getOutputStream();
            socketOut.write(resp.getBytes());*/
            
            DataOutputStream sout= new DataOutputStream(connection.getOutputStream());
            BufferedReader breader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String out1 = breader.readLine();
            System.out.println("Received: "+out1);
            
            sout.write(out1.getBytes());
        }
        

    }


}
