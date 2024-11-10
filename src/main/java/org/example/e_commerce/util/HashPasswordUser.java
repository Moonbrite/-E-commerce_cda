package org.example.e_commerce.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
@Configuration
public class HashPasswordUser {

    public static boolean verifyPassword(String password, byte[] salt, String storedHash) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password, salt);
        return hashedPassword.equals(storedHash);
    }

    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashedPassword = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Méthode pour générer un salt
    public static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}