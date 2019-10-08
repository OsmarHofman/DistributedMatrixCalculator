package br.edu.ifsc.calculadoramatriz.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import br.edu.ifsc.calculadoramatriz.interfaces.IMultiplicacaoMatriz;

public class ConexaoServidor {

	private long[][] matriz;
	private ArrayList<String> processos;
	
	
	public ConexaoServidor() {
		this.processos = new ArrayList<String>();
	}
	
	
	public long[][] conectar(String ip, long[][] matrizDiv, long[][]matrizB) {
	  matriz = new long[1][4];
      processos.add(ip);
		new Thread() {
			@Override
			public void run() {
				try {
					// Registra o gerenciador de seguran√ßa
					System.out.println("\n\tRegistrando o gerenciador de seguranca..");
					
					IMultiplicacaoMatriz calcPc1;
					calcPc1 = (IMultiplicacaoMatriz) Naming.lookup("rmi://"+ip+":1099/MultiplicacaoMatriz");
				    matriz = calcPc1.multiplicacaoMatriz(matrizDiv, matrizB);
				    processos.remove(ip);
				    
				    for (int i = 0; i < matriz.length; i++) {
				    	for (int j = 0; j < matrizB.length; j++) {
							System.out.println("T· retornando"+matriz[i][j]);
						}
					}

				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					System.err.print("\n\tErro ao tentar conectar: " + e.getMessage());
					System.exit(1);
				}
			}
		}.start();
		
		return matriz;
	}


	public ArrayList<String> getProcessos() {
		return processos;
	}


	public long[][] getMatriz() {
		return matriz;
	}
	
	
}
