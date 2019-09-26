package br.edu.ifsc.calculadoramatriz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.edu.ifsc.calculadoramatriz.interfaces.IMultiplicacaoMatriz;

public class CalculadoraClient {

	public static void main(String[] args) {

		System.out.println("\nIniciando cliente da CalculadoraRemota...");

		int lin = 2, col = 2;

		CarregaMatriz ca = new CarregaMatriz();
		// Carregando matriz A
		String caminhoA = "src/matA.txt";
		long[][] matA = ca.carregarMatriz(lin,col, caminhoA);
		
		//Carregando matriz B
		String caminhoB = "src/matB.txt";
		long[][] matB = ca.carregarMatriz(lin, col, caminhoB);
		
		// Separando dados da matriz A
		long[] result = new long[col];
		result[0] = matA[0][0];
		result[1] = matA[0][1];
			
	
		try {
			IMultiplicacaoMatriz calcPc1;
			
			// Registra o gerenciador de segurança
			System.out.println("\n\tRegistrando o gerenciador de seguranca..");
			System.setSecurityManager(new SecurityManager());

			calcPc1 = (IMultiplicacaoMatriz) Naming.lookup("rmi://10.151.33.134:1099/MultiplicacaoMatriz");
			
		    long[] resultado = calcPc1.multiplicacaoMatriz(result, matB);
		    
		    for (int i = 0; i < resultado.length; i++) {
					System.out.println(resultado[i]);
				
			}

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.err.print("\n\tErro ao tentar conectar: " + e.getMessage());
			System.exit(1);
		}

	}

}
