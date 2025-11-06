package com.sailpoint;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

//      import org.json.JSONArray;
//      import org.json.JSONException;
//      import org.json.JSONObject;

 

public class jsonPath {
    public static void main(String[] args) throws InterruptedException {
    String resbody = "{\"d\" : {\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\", \"type\" : \"SFOData.PerPerson\"}, \"personIdExternal\" : \"90013300\", \"lastModifiedDateTime\" : \"\\\\/Date(1651153849000+0000)\\\\/\", \"createdDateTime\" : \"\\\\/Date(1651138525000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1651138525000)\\\\/\", \"customDouble1\" : \"29\", \"lastModifiedBy\" : \"BOBA0001\", \"customString3\" : \"41243\", \"dateOfBirth\" : \"\\\\/Date(714096000000)\\\\/\", \"customString2\" : \"19920818-9994\", \"perPersonUuid\" : \"E381B1308EF041CE91C6E4EE0B6731BA\", \"lastModifiedOn\" : \"\\\\/Date(1651153849000)\\\\/\", \"customString1\" : null, \"createdBy\" : \"BOBA0001\", \"personId\" : \"1835\", \"customString1Nav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/customString1Nav\"}}, \"personalInfoNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personalInfoNav\"}}, \"emergencyContactNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/emergencyContactNav\"}}, \"customString3Nav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/customString3Nav\"}}, \"secondaryAssignmentsNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/secondaryAssignmentsNav\"}}, \"personEmpTerminationInfoNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personEmpTerminationInfoNav\"}}, \"employmentNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/employmentNav\"}}, \"homeAddressNavDEFLT\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/homeAddressNavDEFLT\"}}, \"phoneNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/phoneNav\"}}, \"nationalIdNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/nationalIdNav\"}}, \"userAccountNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/userAccountNav\"}}, \"personTypeUsageNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personTypeUsageNav\"}}, \"emailNav\" : {\"results\" : [{\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\", \"type\" : \"SFOData.PerEmail\"}, \"emailType\" : \"31767\", \"personIdExternal\" : \"90013300\", \"emailAddress\" : \"morampudi.nagababu1@postnordstg.onmicrosoft.com\", \"lastModifiedDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdBy\" : \"SFADMIN\", \"isPrimary\" : true, \"lastModifiedBy\" : \"SFADMIN\", \"createdDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"lastModifiedOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"emailTypeNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\\/emailTypeNav\"}}, \"personNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\\/personNav\"}}}, {\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\", \"type\" : \"SFOData.PerEmail\"}, \"emailType\" : \"31768\", \"personIdExternal\" : \"90013300\", \"emailAddress\" : \"Dummy@abc.com\", \"lastModifiedDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdBy\" : \"BOBA0001\", \"isPrimary\" : false, \"lastModifiedBy\" : \"SFADMIN\", \"createdDateTime\" : \"\\\\/Date(1651152959000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1651152959000)\\\\/\", \"lastModifiedOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"emailTypeNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\\/emailTypeNav\"}}, \"personNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\\/personNav\"}}}]}}}";
	//System.out.println("responseBody : " + responseBody);
	String emailAddressJsonPath = "$.d.emailNav.results[?(@.emailType=='31767')].emailAddress";
	String linkIDJsonPath= "$.d.results[?(@.REFERENCED_MSKEYVALUE=='MX_PRIV:IDS:MANAGE')].LINK_ID";
	String businessEmail = JsonPath.read(resbody,emailAddressJsonPath).toString();
 	System.out.println("personalEmail (31767) : " + businessEmail);
	
	emailAddressJsonPath = "$.d.emailNav.results[?(@.emailType=='31768')].emailAddress";
	//businessEmail = JsonPath.read(resbody, emailAddressJsonPath).toString();
	//businessEmail = businessEmail.replace("[\"", "");
	//businessEmail = businessEmail.replace("\"]", "");
 	//System.out.println("businessEmail (31768) : " + businessEmail);
	JSONArray jsonarray = JsonPath.read(resbody, emailAddressJsonPath);
	System.out.println("jsonarray size = "+jsonarray.size());
	String emails=null;
	if (jsonarray.size()>0){
		emails=(String) jsonarray.get(0);
		for (int i = 1; i < jsonarray.size(); i++) {
			System.out.println("jsonarray get("+i+") = "+jsonarray.get(i));
			emails=emails+","+(String) jsonarray.get(i);
		}
	}
	System.out.println("businessEmail (31768) from Array : " + emails);
	emailAddressJsonPath = "$.d.emailNav.results[?(@.emailType=='31767' || @.emailType=='31768')].emailAddress";
	//businessEmail = JsonPath.read(resbody,emailAddressJsonPath).toString();
	//businessEmail = businessEmail.replace("[\"", "");
	//businessEmail = businessEmail.replace("\"]", "");
	//businessEmail = businessEmail.replace("\",\"", ",");
 	//System.out.println("personalEmail (31767) and businessEmail (31768) : " + businessEmail);
    
	jsonarray = JsonPath.parse(resbody).read(emailAddressJsonPath);
	System.out.println("jsonarray size = "+jsonarray.size());
	emails=(String) jsonarray.get(0);
	for (int i = 1; i < jsonarray.size(); i++) {
		System.out.println("jsonarray get("+i+") = "+jsonarray.get(i));
		emails=emails+","+(String) jsonarray.get(i);
	}
	System.out.println("personalEmail (31767) and businessEmail (31768) from Array : " + emails);
}
}

