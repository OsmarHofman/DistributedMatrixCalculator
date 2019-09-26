package br.edu.ifsc.calculadoramatriz;

import java.io.BufferedReader;
import java.io.FileReader;

public class CarregaMatriz {

	
	public long[][] carregarMatriz(int lin, int col, String caminho) {
		long[][] matriz = new long[lin][col];
		int l,c;
		
		try {
			FileReader file = new FileReader(caminho);
			BufferedReader bufFile = new BufferedReader(file);
			
			// Lê a primeira linha
			String line = bufFile.readLine();
			l = c = 0;
			while (line != null) {
				matriz[l][c] = Integer.parseInt(line);
				c++;
				if (c >= col) {
					l++;
					c = 0;
				}
				line = bufFile.readLine();
			}
			bufFile.close();
		} catch (Exception e) {
			System.err.print("\n\tErro no método carregarMatriz: "+e.getMessage());
			System.exit(1);
		}
		
		return matriz;
		
	}
	
}
