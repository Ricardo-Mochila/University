//http://g2pc1.bu.edu/~qzpeng/manual/MySQL%20Commands.htm comandos base dados

package trabalho1; 

/**
 *
 * @author ricardomochila, inesverissimo
 */

public class Server {

    public static void main(String args[]) {
	

	int regPort= 1099; 

	if (args.length !=4) { 
	    System.out.println("Usage: java so2.rmi.PalavrasServer registryPort");
	    System.exit(1);
	}

	try {
	    regPort=Integer.parseInt(args[0]);

	    InvocationsInterface obj= new Invocations(args[1], args[2], args[3]);
        java.rmi.registry.LocateRegistry.createRegistry(regPort);            
            
	    java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

	    registry.rebind("Produtos", obj);  

	    System.out.println("Bound RMI object in registry");

		System.out.println("servidor pronto");
		

	} 
	catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
}
