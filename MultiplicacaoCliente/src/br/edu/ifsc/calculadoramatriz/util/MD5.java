package br.edu.ifsc.calculadoramatriz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.MessageDigest;

public class MD5 {

	private static byte[] getFileBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;

		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);

		} catch (Exception e) {
			System.err.println("\n\tErro ao ler os bytes do arquivo da matriz C: " + e.getMessage());
			System.exit(1);
		}
		return sendBuf;
	}

	
	private static String toHexFormat(final byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	
	public void geraMD5() {
		try {
			File matCFile = new File("src/matC.txt");
			int matCSize = (int) matCFile.length();
			byte[] matCBytes = new byte[matCSize];
			matCBytes = getFileBytes(matCFile);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(matCBytes);
			System.out.println("\nHash: " + toHexFormat(hash));
			System.out.println("\nGravando arquivo matC.md5...");
			File mdsFile = new File("src/matC.md5");
			BufferedWriter writer = new BufferedWriter(new FileWriter(mdsFile));
			writer.write(toHexFormat(hash) + "  matC.txt");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			System.err.println("\n\tErro ao gerar o MD5: " + e.getMessage());
			System.exit(1);
		}
	}
}
