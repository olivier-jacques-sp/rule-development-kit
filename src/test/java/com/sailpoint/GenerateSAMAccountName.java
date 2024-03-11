package com.sailpoint;

import sailpoint.api.*;
import sailpoint.object.*;
import sailpoint.rule.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.Normalizer;

import org.apache.commons.lang.StringUtils;

public class GenerateSAMAccountName {
int maxIteration = 100;

public String normalizeInput(String inputString) {
    //Decompose and remove diacritical marks, spaces, special characters and symbols
    if(inputString == null || inputString.isEmpty()) return "";
    inputString = Normalizer.normalize(inputString, Normalizer.Form.NFD);
    inputString = inputString.replaceAll("[^a-zA-Z0-9]","");
    return inputString;
}

public static String generateUsername ( String firstName, String lastName ) {
    if ( ( firstName == null ) || ( lastName == null ) )
        return null;
        else
            return generateUsername( firstName, lastName, 0 );
}
     
public String generateUsername ( String firstName, String lastName, int iteration ) {
    /*
     * This will hold the final username;
     */
    String username = null;
    int maxLength = 20;
    /*
     * Iteration logic - this was provided by the customer.
     * - firstname + "." + First letter of lastname
     * - Should that not be unique or too long: firstName + "." + add one letter of lastName
     * - Should that not be unique or too long: firstName + "." + first letter of lastName
     * - Should that not be unique or too long: firstname + "." + lastname + counter(1,2,3)
     */
    if  ( iteration == 0  ) {
        int lastnameIteration = 0;
        while ( lastnameIteration <= lastName.length()){
            username = firstName + "." + StringUtils.substring( lastName, 0, lastnameIteration );
            // if it's too long shorten it to fit within maxLength characters 
		    if (!meetsRequirements ( username ) ) {
                if (firstName.length()<maxLength) username = firstName + "." + StringUtils.substring( lastName , 0 , maxLength-firstName.length()-1 );
                else username = StringUtils.substring( firstName , 0 , maxLength );
                lastnameIteration = lastName.length();
            }
            if ( isUnique ( username ) ) return username;
            else lastnameIteration = lastnameIteration + "1";
        }      
        return generateUsername ( firstName, lastName, ( iteration + 1 ) );
	}
	else {
		username = firstName + "." + lastName + (iteration) ;
		// if it's too long shorten it to fit within maxLength characters 
		if (!meetsRequirements ( username ) ) {
            if (firstName.length()<maxLength) username = firstName + "." + StringUtils.substring( lastName , 0 , maxLength-firstName.length()-1 );
            else username = StringUtils.substring( firstName , 0 , maxLength-iteration.length() ) + (iteration);
        if ( isUnique ( username ) ) return username;
	    else if ( iteration < maxIteration ) return generateUsername ( firstName, lastName, ( iteration + 1 ) );
		else return null;
        }
        if ( isUnique ( username ) ) return username;
		else if ( iteration < maxIteration ) return generateUsername ( firstName, lastName, ( iteration + 1 ) );
        else return null;
    }
}

public boolean isUnique ( String username ) {
    boolean unique = true;
    List sourceIds = new ArrayList(Arrays.asList(new String[]{application.getId()}));
    List searchValues = new ArrayList(Arrays.asList(new String[]{username}));
    if (username.Equals("Olivier.jacques.3")) unique = true;
    //if (idn.attrSearchCountAccounts(sourceIds, "sAMAccountNames", "Equals", searchValues) > 0) unique = false; 
    return unique;
}

public  boolean meetsRequirements ( String username ) {
    // ensuring the name is between 3 and maxLength characters long
	return ( username.length() >= 3 ) && ( username.length() <= maxLength );
}
public static main(String[] args) {
// Main
System.out.println(generateUsername(normalizeInput("Olivier"), normalizeInput("Jacques")));
}}

