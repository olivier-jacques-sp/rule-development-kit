package com.sailpoint;

import java.text.Normalizer;

public class strings {
   
public static String normalizeInput(String inputString) {
    //Decompose and remove diacritical marks, spaces, special characters and symbols
    if(inputString == null || inputString.isEmpty()) return "";
    inputString = Normalizer.normalize(inputString, Normalizer.Form.NFD);
     inputString = inputString.replaceAll(" ", ".");
     inputString = inputString.replaceAll("[^a-zA-Z0-9.]", "");
    return inputString;
}

public static void main(String[] args) throws InterruptedException {
	String name = "aÂ.eérge 12214287 *i ^y :o";
	System.out.println("normalized name: "+ normalizeInput(name)); 
}
}
