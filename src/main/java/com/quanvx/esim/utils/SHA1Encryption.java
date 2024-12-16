package com.quanvx.esim.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Encryption {
    public static String encrypt(String input) {
        try {
            // Create a MessageDigest instance for SHA-1
            MessageDigest digest = MessageDigest.getInstance("SHA-1");

            // Update the digest with the byte array of the input string
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting string with SHA-1", e);
        }
    }

    public static String encryptWithMD5(String input) {
        try {
            // Get an instance of the MD5 message digest
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute the hash, which returns a byte array
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // Convert byte to hex and append to the string
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Add leading zero if needed
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        //customerCode+customerAuth+orderCode+orderTid+timestamp
        String orderCode = "DD-241215-2988810";
        String orderTid = "";
        String input = "OYchK08ITbIO7a0d7eef-2150-4f7c-ab31-829ea59333f5173437241460928476F0481E5424F8EA22D37C80C0CE3s";
        System.out.println(SHA1Encryption.encryptWithMD5(input));
    }
}

