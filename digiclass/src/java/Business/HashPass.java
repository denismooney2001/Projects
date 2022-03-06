/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author junta
 */
public class HashPass {

    public HashPass() {
    }

    /**
     *
     * @param password when the user try to register or login they enter the
     * password
     * @param salt generate from the function getSalt, it is an byte array it
     * store 16 random digit
     * @return it return a string with 128 characters This MessageDigest class
     * provides applications the functionality of a message digest algorithm,
     * such as SHA-512 or SHA-256 and etc. Message digests are secure one-way
     * hash functions that take arbitrary-sized data and output a fixed-length
     * hash value.
     *
     * @throws NoSuchAlgorithmException This exception is thrown when SHA-512
     * cryptographic algorithm is requested but is not available in the
     * environment.
     */
    public String SecurePass512(String password, byte[] salt) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return generatedPassword;
    }

    /**
     *
     * @return a random 16 byte digit in byte array
     * @throws NoSuchAlgorithmException This exception is thrown when a
     * particular cryptographic algorithm is requested but is not available in
     * the environment.
     *
     */
    public byte[] getSalt() throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;

    }

    /**
     *
     * @param result the result is the salt String on the database
     * @return it return the String to the exact value to the byte array
     * @throws NullPointerException Thrown when an application attempts to use
     * null in a case where an object is required
     */
    public byte[] StringtoByte(String result) throws NullPointerException {
        result = result.substring(1, result.length() - 1);
        String[] splitedStr = result.split("\\s*,\\s*");
        byte[] b = new byte[splitedStr.length];
        int i = 0;
        for (String byt : splitedStr) {
            try {
                b[i++] = Byte.parseByte(byt);
            } catch (NumberFormatException ex) {
            }
        }
        return b;
    }

    /**
     *
     * @param cardDetail when the user are going to pay, we will store their
     * specific card details in hashcode for safety reason
     * @param salt generate from the function getSalt, it is an byte array it
     * store 16 random digit
     * @return it return a string with 64 characters This MessageDigest class
     * provides applications the functionality of a message digest algorithm,
     * such as SHA-512 or SHA-256 and etc. Message digests are secure one-way
     * hash functions that take arbitrary-sized data and output a fixed-length
     * hash value.
     *
     * @throws NoSuchAlgorithmException This exception is thrown when SHA-256
     * cryptographic algorithm is requested but is not available in the
     * environment.
     */
    public String SecureCard256(String cardDetail, byte[] salt) throws NoSuchAlgorithmException {
        String generateCardNumber = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(cardDetail.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generateCardNumber = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return generateCardNumber;
    }
}
