package br.edu.ifsc.calculadoramatriz.util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ifsc.calculadoramatriz.interfaces.IMultiplicacaoMatriz;

public class MultiplicacaoMatriz extends UnicastRemoteObject implements IMultiplicacaoMatriz {

	private static final long serialVersionUID = 1L;

	public MultiplicacaoMatriz() throws RemoteException {
	}

	@Override
	public long[][] multiplicacaoMatriz(long[][] linhaMatrizA, long[][] matrizB) throws RemoteException {
		System.out.println("Calculando matriz");
		long[][] matC = new long[1024][4096];
		// realiza multiplicação das matrizes
		System.out.println("Multiplicando as matrizes...");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < linhaMatrizA.length; i++) {
			for (int j = 0; j < matrizB.length; j++) {
				for (int k = 0; k < linhaMatrizA[1].length; k++) {
					matC[i][j] += linhaMatrizA[i][k] * matrizB[k][j];
				}
			}
		}

		System.out.println("Finalizando C�lculo");

		return matC;
	}

}
