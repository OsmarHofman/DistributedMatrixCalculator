package br.edu.ifsc.calculadoramatriz.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMultiplicacaoMatriz extends Remote {

	public long[] multiplicacaoMatriz(long[] linhaMatrizA,long[][] matrizB) throws RemoteException;

}
