package com.sailpoint;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;


public class jsonPathIMA {
public static void main(String[] args) throws InterruptedException {
	String linkIdResbody = "{\"SV_MX_ENCRYPTED_PASSWORD\":\"mot de passe non encrypté\"}";
	if (linkIdResbody.contains("SV_MX_ENCRYPTED_PASSWORD")){ 
		String linkIDJsonPath= "$['SV_MX_ENCRYPTED_PASSWORD']";
		String linkId = JsonPath.read(linkIdResbody,linkIDJsonPath);
		System.out.println("linkId (MX_PRIV:IDS:MANAGE) : " + linkId);
    }
	else System.out.println("no password found"); 
}
}

