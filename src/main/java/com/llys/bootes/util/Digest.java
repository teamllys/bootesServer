package com.llys.bootes.util;

import java.security.MessageDigest;

public class Digest {

    public Digest() {
        // TODO Auto-generated constructor stub
    }

    public static String makeSha(String inputText) throws Exception{
        
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(inputText.getBytes());
        byte[] digest = md.digest();
        
//        System.out.println(md.getAlgorithm());
//        System.out.println(digest.length);
//        
        StringBuffer sb = new StringBuffer();
        for(byte b : digest){
         //System.out.print(Integer.toHexString(b & 0xff) + "");
            sb.append(Integer.toHexString(b & 0xff));
        }
//        System.out.println("\n\nReturn String : " + sb.toString());
        return sb.toString();
       }
}
