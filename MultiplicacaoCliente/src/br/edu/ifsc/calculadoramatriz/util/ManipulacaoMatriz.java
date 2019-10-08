package br.edu.ifsc.calculadoramatriz.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ManipulacaoMatriz {
	
	private int lin = 4; 
	private int col = 4;
	private long[][] matriz;
	private long[][] matrizDiv;
	
	public ManipulacaoMatriz() {
	}

	public long[][] carregarMatriz(String caminho) {
		int l, c;
		matriz = new long[lin][col];

		try {
			FileReader file = new FileReader(caminho);
			BufferedReader bufFile = new BufferedReader(file);

			// Lê a primeira linha
			String line = bufFile.readLine();
			l = c = 0;
			while (line != null) {
				matriz[l][c] = Integer.parseInt(line);
				c++;
				if (c >= this.col) {
					l++;
					c = 0;
				}
				line = bufFile.readLine();
			}
			bufFile.close();
		} catch (Exception e) {
			System.err.print("\n\tErro no método carregarMatriz: " + e.getMessage());
			System.exit(1);
		}

		return matriz;
	}
	
	
	public long[][] separarMatriz(long[][] matrizA, int linInicio, int linFinal ) {
		matrizDiv = new long[1][4];
		for (int i = linInicio; i <= linFinal; i++) {
			for (int j = 0; j < matrizA.length; j++) {
				this.matrizDiv[i][j] = matrizA[i][j];
				System.out.println("matriz separarada "+matrizDiv[i][j]);
			}
		}
		
		return this.matrizDiv;
	}
	
	public long[][] unirPartes(long[][]parte1, long[][]parte2, long[][]parte3, long[][]parte4){
		int l2=0, c2=0;
		int l3=0, c3=0;
		int l4=0, c4=0;
		long[][] matriz = new long[4][4];
		for (int l = 0; l < 4; l++) {
			for (int c = 0; c < 4 ; c++) {
				if(l == 0) {
					matriz[l][c] = parte1[l][c];
					System.out.println("Parte1 "+parte1[l][c]);
				}/*else if(l<=1) {
					this.matriz[l][c] = parte2[l2][c2];
					l2++;
					c2++;
				}else if(l <=2) {
					this.matriz[l][c] = parte3[l3][c3];
					l3++;
					c3++;
				}else if(l <=3) {
					this.matriz[l][c] = parte4[l4][c4];
					l4++;
					c4++;
				}*/
			}
		}
		return matriz;
	}
	
	public void gerarMatriz(String caminho, long[][] matC) {
		try {
			File fOut = new File(caminho);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
			for (int i = 0; i < lin; i++) {
				for (int j = 0; j < col; j++) {
					writer.write(String.valueOf(matC[i][j]));
					if ((i == lin-1) && (j == col-1)) {
						continue;
					} else {
						writer.newLine();
					}
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\n\tErro: "+e.getMessage());
			System.exit(1);
		}
	}

}
