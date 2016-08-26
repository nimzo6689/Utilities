/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nimzo
 */
public class Crypto {

    public static final String ENCRYPT_KEY = "1234567890123456";
    public static final String ENCRYPT_IV = "abcdefghijklmnop";
    public static final String ENCRIPT_TEXT = "test123";
    public static final String DECRIPT_TEXT = "BTRsN57DlDsSB9aPzgt/Vg==";
    public static final String ALGORITHM = "AES";
    public static final String MODE_PADDING = "/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        String encrypted = encrypt(ENCRIPT_TEXT);
        System.out.println(encrypted);
        String decrypted = decrypt(DECRIPT_TEXT);
        System.out.println(decrypted);
    }

    /**
     * Encrypt
     *
     * @param text
     * @return
     */
    public static String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + MODE_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(ENCRYPT_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM),
                new IvParameterSpec(ENCRYPT_IV.getBytes(StandardCharsets.UTF_8)));
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Decrypt
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + MODE_PADDING);
        cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(ENCRYPT_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM),
                new IvParameterSpec(ENCRYPT_IV.getBytes(StandardCharsets.UTF_8)));
        return new String(cipher.doFinal(Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
    }

}
