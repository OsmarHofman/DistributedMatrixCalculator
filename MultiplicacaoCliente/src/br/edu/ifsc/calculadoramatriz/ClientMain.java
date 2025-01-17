package br.edu.ifsc.calculadoramatriz;

import br.edu.ifsc.calculadoramatriz.util.ConexaoServidor;
import br.edu.ifsc.calculadoramatriz.util.MD5;
import br.edu.ifsc.calculadoramatriz.util.ManipulacaoMatriz;

public class ClientMain {

	private final static int l = 4096;
	private final static int c = 4096;

	private static long[][] matA = new long[l][c];
	private static long[][] matB = new long[l][c];
	private static long[][] matC = new long[l][c];

	public static void main(String[] args) {


		ManipulacaoMatriz manipula = new ManipulacaoMatriz(l, c);
		ConexaoServidor con = new ConexaoServidor();
		MD5 md = new MD5();

		System.out.println("\nIniciando cliente da CalculadoraRemota...");

		// Carregando matriz A
		System.out.println("\nLendo a matriz A...");
		String caminhoA = "src/matA.txt";
		matA = manipula.carregarMatriz(caminhoA);

		// Carregando matriz B
		System.out.println("\nLendo a matriz B...");
		String caminhoB = "src/matB.txt";
		matB = manipula.carregarMatriz(caminhoB);

		System.out.println("\nSeparando a matriz A em 4 partes...");
		long[][] parte1 = manipula.separarMatriz(matA, 0, 1024);
		long[][] parte2 = manipula.separarMatriz(matA, 1024, 2048);
		long[][] parte3 = manipula.separarMatriz(matA, 2048, 3072);
		long[][] parte4 = manipula.separarMatriz(matA, 3072, 4096);

		// Tempo de c�lculo
		long startTime = System.currentTimeMillis();

		System.out.println("\nEnviando parte 1...");
		con.conectar("10.151.33.80", parte1, matB, 1);

		System.out.println("\nEnviando parte 2...");
		con.conectar("10.151.33.112", parte2, matB, 2);

		System.out.println("\nEnviando parte 3...");
		con.conectar("10.151.33.134", parte3, matB, 3);

		System.out.println("\nEnviando parte 4...");
		con.conectar("10.151.33.162", parte4, matB, 4);

		System.out.println("Aguandando resultados...");

		while (con.getProcessos().size() != 0) {
			System.out.println("Esperando...");
		}

		long stopTime = System.currentTimeMillis();

		// Imprime o tempo de execu��o
		System.out.print("\n\tTempo de execu��o: " + (stopTime - startTime) + " ms");
		System.out.print("\n\tTempo de execu��o: " + (stopTime - startTime) / 1000 + " segundos");
		System.out.print("\n\tTempo de execu��o: " + ((stopTime - startTime) / 1000) / 60 + " minutos");

		parte1 = con.getParte1();
		parte2 = con.getParte2();
		parte3 = con.getParte3();
		parte4 = con.getParte4();

		System.out.println("Unindo as partes multiplicadas...");

		matC = manipula.unirPartes(parte1, parte2, parte3, parte4);

		System.out.println("Gravando matriz C em um arquivo txt");
		manipula.gerarMatriz("src/matC.txt", matC);

		System.out.println("Gerando MD5...");
		md.geraMD5();

	}

}
