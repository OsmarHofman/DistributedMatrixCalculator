package br.edu.ifsc.calculadoramatriz;

public class MultiplicacaoMatriz {

	public static void main(String[] args) {
		System.out.println("\nIniciando servidor CalculadoraRemota...");

//		try {
		// Instancia o gerenciador de segurança
		System.out.println("\n\tRegistrando o serviço de segurança...");
		System.setSecurityManager(new SecurityManager());

		// Instancia o objeto local
		// CalculadoraRemota calc = new CalculadoraRemota();

		// Registra o objeto no RMI Registry
		System.out.println("\n\tRegistrando o objeto no RMI Registry...");
		// Naming.rebind("rmi://localhost:1099/CalculadoraRemota", calc);

		System.out.println("\n\tAguardando requisições...");
//		} catch (RemoteException | MalformedURLException e) {
//			System.err.print("\n\tErro: " + e.getMessage());
//			System.exit(1);
//		}

	}

}
