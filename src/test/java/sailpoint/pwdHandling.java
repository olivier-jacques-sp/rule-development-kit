package com.sailpoint;

public class pwdHandling {

  public static void main(String[] args) {
    // Define your secret key and salt (keep these secure and don't hardcode in production)
    String secretKey = "what a bad secret!";
    String salt = "this is a weird but easy to guess salt !@dfdfd3454$%^#$^@$, isn't it ;-)";

    // String to be encrypted
    String password = "my bad easy passw0rd!";

    // Encrypt the string
    String encryptedString =  AESEncryptDecrypt.encrypt(password, secretKey, salt);
    if (encryptedString != null) {
      System.out.println("Encrypted password: " + encryptedString);
    } else {
      System.err.println("Encryption failed.");
      return;
    }

    // Decrypt the string
    String decryptedString =  AESEncryptDecrypt.decrypt(encryptedString, secretKey, salt);
    if (decryptedString != null) {
      System.out.println("Decrypted password: " + decryptedString);
    } else {
      System.err.println("Decryption failed.");
    }
    System.out.println("\nUsing the secretKey \"what a bad secret!\" and salt \"this is a weird but easy to guess salt !@dfdfd3454$%^#$^@$, isn't it ;-)\"");
    encryptedString = "KID93aJALpx75kAaYu2ZApHdeG6lkoLjCUJK3HTBWI+woQyVN80oGNHTKLJtJxoX";
    System.out.println(encryptedString + " is also the encrypted password: " + AESEncryptDecrypt.decrypt(encryptedString, secretKey, salt));
    encryptedString = "9oDsFgM4o38oqHqGUHNcZVRF6bVexQg8yiyhntQ1Pz/8UEa87gfUaK/OOWKdmvNB";
    System.out.println(encryptedString + " is also the encrypted password: " + AESEncryptDecrypt.decrypt(encryptedString, secretKey, salt));
    encryptedString="wNJrAiOVbAe1NCuOfBGaDSpQS5vTEprYXatiqeuzZZ4GI1pXI8Jb9cVW2KK8Er1R";
    System.out.println(encryptedString + " is also the encrypted password: " + AESEncryptDecrypt.decrypt(encryptedString, secretKey, salt));
  }
}