package com.sailpoint;

public class AES256Example {

  public static void main(String[] args) {
    // Define your secret key and salt (keep these secure and don't hardcode in production)
    String secretKey = "what a bad secret!";
    String salt = "this is a weird but easy to guess salt !@dfdfd3454$%^#$^@$, isn't it";

    // String to be encrypted
    String password = "Hello, this is a secret message.";

    // Encrypt the string
    String encryptedString = AESEncryptDecrypt.encrypt(password, secretKey, salt);
    if (encryptedString != null) {
      System.out.println("Encrypted password: " + encryptedString);
    } else {
      System.err.println("Encryption failed.");
      return;
    }

    // Decrypt the string
    String decryptedString = AESEncryptDecrypt.decrypt(encryptedString, secretKey, salt);
    if (decryptedString != null) {
      System.out.println("Decrypted password: " + decryptedString);
      System.out.println("second pass Decrypted password: " + AESEncryptDecrypt.decrypt("mTJD31w2172SBXgtAimINgcqwr4CxISjXjydUOIepywiYFkCUxWZbjwSgy/5By6tk30kJtGzMxeBJu0UCWqgxA==", secretKey, salt));
    } else {
      System.err.println("Decryption failed.");
    }
  }
}