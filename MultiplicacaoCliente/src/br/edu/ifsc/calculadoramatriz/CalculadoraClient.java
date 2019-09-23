package br.edu.ifsc.calculadoramatriz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.edu.ifsc.calculadoramatriz.carregamatrizes.CarregaMatriz;
import br.edu.ifsc.calculadoramatriz.interfaces.ICalculadoraClient;

public class CalculadoraClient {

	public static void main(String[] args) {

		System.out.println("\nIniciando cliente da CalculadoraRemota...");

		int lin = 2, col = 2;

		CarregaMatriz ca = new CarregaMatriz();
		// Carregando matriz A
		String caminhoA = "src/matA.txt";
		long[][] matA = ca.carregarMatriz(lin, col, caminhoA);
		
		//Carregando matriz B
		String caminhoB = "src/matB.txt";
		long[][] matB = ca.carregarMatriz(lin, col, caminhoB);
		
		// Separando dados da matriz A
		long[] result = new long[col];
		result[0] = matA[0][0];
		result[1] = matA[0][1];
			
	
		try {
			ICalculadoraClient calcPc1;
			
			// Registra o gerenciador de seguranÃ§a
			System.out.println("\n\tRegistrando o gerenciador de segurança..");
			System.setSecurityManager(new SecurityManager());

			calcPc1 = (ICalculadoraClient) Naming.lookup("rmi://localhost:1099/MultiplicacaoMatriz");
			
		    long[][] resultado = calcPc1.multiplicacaoMatriz(result, matB);
		    
		    for (int i = 0; i < resultado.length; i++) {
				for (int j = 0; j < resultado.length; j++) {
					System.out.println(resultado[i][j]);
				}
			}

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}

	}

}
