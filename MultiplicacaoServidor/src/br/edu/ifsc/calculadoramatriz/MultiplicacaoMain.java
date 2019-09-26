package br.edu.ifsc.calculadoramatriz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import br.edu.ifsc.calculadoramatriz.util.MultiplicacaoMatriz;

public class MultiplicacaoMain {

	public static void main(String[] args) {
		System.out.println("\nIniciando servidor MultiplicacaoMatriz...");

		try {
		// Instancia o gerenciador de segurança
		System.out.println("\n\tRegistrando o serviço de segurança...");
		System.setSecurityManager(new SecurityManager());

		// Instancia o objeto local
		MultiplicacaoMatriz calc = new MultiplicacaoMatriz();

		// Registra o objeto no RMI Registry
		System.out.println("\n\tRegistrando o objeto no RMI Registry...");
		Naming.rebind("rmi://localhost:1099/MultiplicacaoMatriz", calc);

		System.out.println("\n\tAguardando requisições...");
//		
//		MultiplicacaoMatriz matriz = new MultiplicacaoMatriz();
//		long[] matA = new long[2];
//		matA[0] = 1;
//		matA[1] = 2;
//		
//		long[][] matB = new long[2][2];
//		matB[0][0] = 1;
//		matB[0][1] = 2;
//		matB[1][0] = 3;
//		matB[1][1] = 4;
//		
//		matriz.multiplicacaoMatriz(matA, matB);
		
		} catch (RemoteException | MalformedURLException e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}

	}

}
