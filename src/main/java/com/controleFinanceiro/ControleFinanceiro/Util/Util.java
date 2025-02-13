package com.controleFinanceiro.ControleFinanceiro.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public abstract class Util {
	
	public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);	
	}
	
	public static byte[] getSalt() throws NoSuchAlgorithmException{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
    public static boolean verifyPassword(String enteredPassword, String storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {
        String newHash = hashPassword(enteredPassword, storedSalt);
        return newHash.equals(storedHash);
    }	

}
