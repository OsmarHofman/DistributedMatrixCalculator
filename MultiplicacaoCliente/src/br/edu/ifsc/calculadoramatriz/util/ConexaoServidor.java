package br.edu.ifsc.calculadoramatriz.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import br.edu.ifsc.calculadoramatriz.interfaces.IMultiplicacaoMatriz;

public class ConexaoServidor {

	private long[][] matriz;
	private long[][] parte1;
	private long[][] parte2;
	private long[][] parte3;
	private long[][] parte4;
	private ArrayList<String> processos;

	public ConexaoServidor() {
		this.processos = new ArrayList<String>();
	}

	public void conectar(String ip, long[][] matrizDiv, long[][] matrizB, int parte) {
		matriz = new long[1][4];
		processos.add(ip);
		new Thread() {
			@Override
			public void run() {
				try {
					// Registra o gerenciador de segurança
					System.out.println("\n\tRegistrando o gerenciador de segurança..");

					IMultiplicacaoMatriz calcPc1;
					calcPc1 = (IMultiplicacaoMatriz) Naming.lookup("rmi://" + ip + ":1099/MultiplicacaoMatriz");
					if (parte == 1) {
						parte1 = calcPc1.multiplicacaoMatriz(matrizDiv, matrizB);
					} else if (parte == 2) {
						parte2 = calcPc1.multiplicacaoMatriz(matrizDiv, matrizB);
					} else if (parte == 3) {
						parte3 = calcPc1.multiplicacaoMatriz(matrizDiv, matrizB);
					} else if (parte == 4) {
						parte4 = calcPc1.multiplicacaoMatriz(matrizDiv, matrizB);
					}

					processos.remove(ip);

				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					System.err.print("\n\tErro ao tentar conectar: " + e.getMessage());
					System.exit(1);
				}
			}
		}.start();
	}

	public ArrayList<String> getProcessos() {
		return processos;
	}

	public long[][] getParte1() {
		return parte1;
	}

	public long[][] getParte2() {
		return parte2;
	}

	public long[][] getParte3() {
		return parte3;
	}

	public long[][] getParte4() {
		return parte4;
	}

}
