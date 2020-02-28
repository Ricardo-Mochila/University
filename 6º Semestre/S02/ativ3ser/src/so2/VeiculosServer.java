package so2;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class VeiculosServer implements java.io.Serializable{
    private int serverPort;	
    
    private Vector<Registo> repositorio;
    
    public VeiculosServer(int p) {
	serverPort= p;		
	repositorio= new Vector<Registo>(); // INICIALIZE o Vector
    }
    

    
    public static void main(String[] args){
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
	
	
	VeiculosServer serv= new VeiculosServer(p);
        serv.servico();   // activa o servico
    }

    public void servico() {

	try {

	     ServerSocket s = new ServerSocket(serverPort);
	  
	    while (true) {
		Socket connection = s.accept();
		
		try {
		    Object objPedido= null;
		    // le os dados do pedido, como um OBJECTO "objPedido"	
                    InputStream socketIn= connection.getInputStream();
                    ObjectInputStream in = new ObjectInputStream(socketIn);
                    objPedido = in.readObject();
                    
                    OutputStream socketOut= connection.getOutputStream();
                    ObjectOutputStream out = new ObjectOutputStream(socketOut);
                            
		    // apreciar o objecto com o pedido recebido:
		    if (objPedido==null)
			System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
		    
		    if (objPedido instanceof PedidoDeConsulta) {
			PedidoDeConsulta pc= (PedidoDeConsulta) objPedido;
                        
			Boolean contains =false;
                        
                        for(Registo p : repositorio){
                            if(p.getMatricula().equals(pc.getMatricula())){
                                contains = true;
                            }
                        }
                        
                        if(contains){

                            out.writeObject(pc);
                        }
                        else{
                            String output = "Not found";
                            out.write(output.getBytes());
                        }
			// procurar o registo associado a matricula pretendida

			// pesquisar no servidor (Vector, mais tarde num ficheiro)

			
			// enviar objecto Cliente via socket		    
			// se encontra devolve o registo, se não, devolve um novo objecto ou string a representar que nao encontrou
	
		    }
		    else if (objPedido instanceof PedidoDeRegisto) {
			PedidoDeRegisto pr= (PedidoDeRegisto) objPedido; // ...

			Boolean contains =false;
                        
                        for(Registo p : repositorio){
                            if(p.getMatricula().equals(pr.getRegisto())){
                                contains = true;
                            }
                        }
                        
                        if(contains){

                            String output = "ALready exists";
                            out.write(output.getBytes());
                        }
                        else{                        
                            repositorio.add(pr.getRegisto());
                            out.writeObject(objPedido);
                        }// ver se ja existia registo desta matricula

			
		    }
                    else
			System.out.println("PROBLEMA");
		    
                }
                catch (Exception exNoAtendimento) {
                    exNoAtendimento.printStackTrace();
                }
                finally {  // fechar o socket de dados
                    // fechar o socket de dados dedicado a este cliente:
                    try {
                        s.close();
                    }
                    catch (Exception e002) {
                    }
                }
                
		
	    
		
	    }  // ... ciclo de atendimento
	
	}
	catch (Exception problemaBindAccept) {
	    problemaBindAccept.printStackTrace();
	}

    }


}
