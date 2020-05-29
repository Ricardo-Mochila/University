/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package trabalho1;
import java.util.Vector;
import java.rmi.RemoteException;
import java.util.Scanner;


/**
 *
 * @author ricardomochila, inesverissimo
 */
public class Client{
    public static int dadosLocalData = 0;
    public static void main(String args[]) {
        String regHost = "localhost";
        String regPort = "9000";  // porto do binder

        if (args.length !=2) { // requer 3 argumentos
            System.out.println
            ("Usage: java so2.rmi.PalavrasClient registryHost registryPort");
            System.exit(1);
        }
        regHost= args[0];
        regPort= args[1];

        while(true)
        {
            try {

                InvocationsInterface obj = (InvocationsInterface) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/Produtos");
                FileSaving file = new FileSaving();
                int id = file.load();
                if(id != 0){
                    System.out.println("\n-- Actualizacoes sobre as suas necessidades:");
                    String news = obj.checkUpdates(id);
                    System.out.println(news);
                }
//Alterado aqui:
                LocalDatabase data = new LocalDatabase();
                if(!data.isEmpty()){
                    data.convertData(obj); 
                }

                menu(false, obj);

            } catch (Exception ex) {
                menu(true, null);
            }
        }       
    }

    public static void menu(Boolean excepcao, InvocationsInterface obj)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 - Consultar Produto \n2 - Registar Produto e Loja \n3 - Necessidade de Produto \n4 - Sair\nSelecione uma opcao: ");

        int opcao = 0;
        try{
            opcao = scanner.nextInt();

        }catch(Exception e){
            System.out.println("Escolha um inteiro");
        }

        try{
            switch(opcao){
                case 1:
                    if(!excepcao)
                    {
                        System.out.print("Produto: ");
                        String produtoConsulta = scanner.next();;
                        String lista = obj.consultarProduto(produtoConsulta) ;
                        
                        if(lista != null){
                            System.out.println("Lista:\n"+lista);
                        }
                        else{
                            System.out.println("Nao foi encontrado o produto");
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("Neste momento não é possivel consultar os produtos.\n");
                        break;
                    }

                case 2:

                    System.out.print("Produto: ");
                    String produto = scanner.next();;
                    System.out.print("Loja: ");
                    String loja = scanner.next();
                    if(!excepcao)
                    {
                        obj.registarProduto(produto, loja) ;
                        System.out.println("Registou: "+produto +" "+loja);
                        break;
                    }
                    else
                    {
                        LocalDatabase data = new LocalDatabase();
                        data.save("registo", produto, loja);
                        System.out.println("Neste momento não é possivel realizar o registo, será registado assim que se restabelecer a ligação.\n");
                        //dadosLocalData++;
                        break;
                    }

                case 3:
                    if(!excepcao)
                    {
                        System.out.print("Produto: ");
                        String produtoFalta = scanner.next();
                        String procura = obj.consultarProduto(produtoFalta) ;
                        if(procura != null)
                        {
                            System.out.print(procura + "\n\n\n");
                            System.out.print("Loja: ");
                            String lojaFalta = scanner.next();
                            String registaNecessidadeProduto = obj.necessidadeProduto(produtoFalta, lojaFalta);
                            System.out.println("Lista:\n"+registaNecessidadeProduto);
                            break;
                        }
                        else
                        {
                            FileSaving file = new FileSaving();
                            int id = file.load();
                            if(id != 0){
                                obj.necessidadeGeral(produtoFalta, id);
                            }
                            else{
                                int newId = obj.necessidadeGeral(produtoFalta, 0);
                                file.save(newId);
                                break;
                            }
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("Neste momento não é possivel registar a sua necessidade,será feito o registo assim que se restabelecer a ligação.\n");
                        break;
                    }

                case 4:
                    System.exit(1);

                default:
                    System.out.print("Nao é uma opcao");    

            }

        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("deu erro");
        }
    }
}