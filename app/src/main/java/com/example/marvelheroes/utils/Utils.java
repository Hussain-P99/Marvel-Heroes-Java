package com.example.marvelheroes.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

//    fun calculateMd5Sum(ts : String,pubKey : String, privKey : String) : String {
//        val messageDigest = MessageDigest.getInstance("MD5")
//        messageDigest.update((ts+privKey+pubKey).toByteArray())
//        val digest = messageDigest.digest().toHexString()
//        return digest
//    }

    public static String calculateMd5Sum(String ts,String pubKey, String privKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((ts+privKey+pubKey).getBytes());
            return byteArrayToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
