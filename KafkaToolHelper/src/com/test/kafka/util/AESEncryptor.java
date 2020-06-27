/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.kafka.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author pg939j
 */
public class AESEncryptor {
    
    private String publicKey = "PHVtDXvFWc9AWVzAYdQB9w==";

    public String encrypt(String key){
            String encodedString = StringUtils.EMPTY;
            try {

                    BASE64Encoder encoder = new BASE64Encoder();
                    BASE64Decoder decoder = new BASE64Decoder();
                    SecretKeySpec keySpec = new SecretKeySpec(decoder.decodeBuffer(getPublicKey()), "AES");

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec);

                    byte[] encryptedText = cipher.doFinal(key.getBytes());
                    encodedString = encoder.encode(encryptedText);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return encodedString;
    }
    /**
     * @return the publicKey
     */
    public String getPublicKey() {
            return publicKey;
    }

}
