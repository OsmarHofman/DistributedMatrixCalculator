package br.edu.ifsc.calculadoramatriz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.edu.ifsc.calculadoramatriz.interfaces.ICalculadoraClient;


public class CalculadoraClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\nIniciando cliente da CalculadoraRemota...");

		try {
			ICalculadoraClient calc;

			// Registra o gerenciador de segurança
			System.out.println("\n\tRegistrando o gerenciador de segurança...");
			System.setSecurityManager(new SecurityManager());

			calc = (ICalculadoraClient)Naming.lookup("rmi://localhost:1099/CalculadoraRemota");

			//System.out.println("\n\tMULT 2 + 2 =>" + calc.mult(2, 2));

		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}

	}

}
