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

    public static void main(String[] args) {
        //customerCode+customerAuth+orderCode+orderTid+timestamp
        String orderCode = "DD-241215-2988810";
        String orderTid = "";
        String input = "104931b3eF0cebb" + orderCode + orderTid + "1667807404146";
        System.out.println(SHA1Encryption.encrypt(input));
    }
}

