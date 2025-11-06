package com.sailpoint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.lang.System.arraycopy;



public class saltPassword {

    public static class ByteArrayUtil {
        public static byte[] concat(byte[] a, byte[] b) {
            byte[] c = new byte[a.length + b.length];
            arraycopy(a, 0, c, 0, a.length);
            arraycopy(b, 0, c, a.length, b.length);
            return c;
        }

        public static String encode(byte[] source) {
            return Base64.getEncoder().encodeToString(source);
        }

        public static byte[] decode(String source) {
            return Base64.getDecoder().decode(source);
        }

        public static byte[] hash(byte[] source) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(source);
        }
    }
    
public static void main(String args []) throws Exception {
    String newPassword = "New and bad Password!";
    System.out.println("String To Encrypt: "+ newPassword);
    
    String strSalt = "this is weird but easy to guess salt !@dfdfd3454$%^#$^@$";
    byte[] salt = org.apache.commons.codec.binary.Base64.encodeBase64(strSalt.getBytes());
    int saltLength = new String(salt).length();
    System.out.println("encoded salt String: " + new String(salt));
    byte[] hashedPassword = ByteArrayUtil.concat(salt, newPassword.getBytes());
    //encode
    String base64HashedPassword = ByteArrayUtil.encode(hashedPassword);
   
    System.out.println("Encrypted String:" + base64HashedPassword);
    //decode
    byte[] decryptedByte = ByteArrayUtil.decode(base64HashedPassword);
    String decryptedPassword = new String(decryptedByte);
    System.out.println("Decrypted String: " + decryptedPassword.substring(saltLength));
}
}

