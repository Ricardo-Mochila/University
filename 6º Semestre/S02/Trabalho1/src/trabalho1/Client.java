/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;
import java.util.Vector;
import java.util.Scanner;

/**
 *
 * @author ricardomochila, inesverissimo
 */
public class Client {

    public static void main(String args[]) {
        String regHost = "localhost";
        String regPort = "9000";  // porto do binder

        if (args.length !=3) { // requer 3 argumentos
            System.out.println
            ("Usage: java so2.rmi.PalavrasClient registryHost registryPort");
            System.exit(1);
        }
        regHost= args[0];
        regPort= args[1];
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 - Consultar Produto \n2 - Registar Produto e Loja \n3 - Necessidade de Produto \nSelecione uma opcao: ");
        int opcao = 0;
        try{
            opcao = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Nao é uma opcao");
        }
        try {
            // objeto que fica associado ao proxy para objeto remoto
            InvocationsInterface obj = (InvocationsInterface) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/Produtos");

            // invocacao de metodos remotos
            switch(opcao){
                case 1:
                    System.out.print("Produto: ");
                    String produtoConsulta = scanner.next();;
                    String lista = obj.consultarProduto(produtoConsulta) ;
                    System.out.println("Lista:\n"+lista);
                    break;

                case 2:
                    System.out.print("Produto: ");
                    String produto = scanner.next();;
                    System.out.print("Loja: ");
                    String loja = scanner.next();

                    obj.registarProduto(produto, loja) ;
                    System.out.println("Registou: "+produto +" "+loja);
                    break;

                
                case 3:
                    System.out.print("Produto: ");
                    String produtoFalta = scanner.next();
                    String procura = obj.consultarProduto(produtoFalta) ;
                    if(procura != null)
                    {
                        System.out.print("Loja: ");
                        String lojaFalta = scanner.next();
                        String registaNecessidadeProduto = obj.necessidadeProduto(produtoFalta, lojaFalta);
                        System.out.println("Lista:\n"+registaNecessidadeProduto);
                        break;
                    }
                    else
                    {
                        String registaNecessidadeGeral = obj.necessidadeGeral(produtoFalta);
                        System.out.println("Lista:\n"+registaNecessidadeGeral);
                        break;
                    }
                default:
                    System.out.print("Nao é uma opcao");    
    
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
