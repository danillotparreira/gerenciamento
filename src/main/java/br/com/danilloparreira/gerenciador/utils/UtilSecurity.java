package br.com.danilloparreira.gerenciador.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilSecurity {

	public static String convertStringToMd5(String valor) {
		MessageDigest messageDigest;
		try {
			// Instancia o MD5, alternativa SHA
			messageDigest = MessageDigest.getInstance("MD5");
			// Converte a string valor para um array de bytes em MD5
			byte[] valorMd5 = messageDigest.digest(valor.getBytes("UTF-8"));
			// Converte o valorMd5 para Hexadecimal para ser salvo no banco de dados
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMd5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
