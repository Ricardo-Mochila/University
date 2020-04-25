package trabalho1; 

import java.util.Vector;

public interface InvocationsInterface extends java.rmi.Remote {
 
    public void registarProduto(String produto, String loja) throws java.rmi.RemoteException, Exception;
    public String consultarProduto(String produto) throws java.rmi.RemoteException, Exception;
    public String necessidadeProduto(String produto, String loja) throws java.rmi.RemoteException, Exception;
    public int necessidadeGeral(String produto, int id) throws java.rmi.RemoteException, Exception;
    public String checkUpdates(int id) throws java.rmi.RemoteException, Exception;
}
