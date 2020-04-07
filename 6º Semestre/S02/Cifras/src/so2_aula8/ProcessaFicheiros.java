/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2_aula8;

import java.io.*;

public class ProcessaFicheiros {


    private String inFile;
    private String outFile;


    public static void main(String[] args) {
	if (args.length<2) {
	    System.err.println("erro: argumentos insuficientes\n"+
			       "java ProcessaFicheiros inFilename outFilename" + "Op");
	    System.exit(1);
	}

	System.out.println("vou passar dados de ("+args[0]+
			   ") para ("+ args[1]+")\n");

	ProcessaFicheiros pf= new ProcessaFicheiros(args[0], args[1]);
	pf.passaDados();
    }


    public ProcessaFicheiros(String inFile, String outFile) {
	this.inFile= inFile;
	this.outFile= outFile;
    }


    // faz uma copia simples
    public void passaDados() {
	byte[] b= new byte[1024];
	int lidos;

	try {
	    FileInputStream fis= new FileInputStream(inFile);
	    FileOutputStream fos= new FileOutputStream(outFile);
	    
	    lidos= fis.read(b);
	    while (lidos>0) {
		// escrever os bytes no outro ficheiro
		fos.write(b,0,lidos); 
		
		// e continuar
		lidos= fis.read(b);
	    }
	    fis.close();
	    fos.close();
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.err.println("PROBLEMA: "+e.getMessage());
	    return;
	}
	System.out.println("----------- ok");
    }


}
