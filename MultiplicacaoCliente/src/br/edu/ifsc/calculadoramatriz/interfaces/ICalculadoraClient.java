package br.edu.ifsc.calculadoramatriz.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadoraClient extends Remote {

	public float mult(long[] linhaMatriz,long[][] matriz) throws RemoteException;

}
