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

		} catch (RemoteException | MalformedURLException e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}

	}

}
