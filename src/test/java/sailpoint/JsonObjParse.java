package com.sailpoint;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class JsonObjParse {

	public static void main(String[] args) {
		 String resbody = "{\"d\" : {\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\", \"type\" : \"SFOData.PerPerson\"}, \"personIdExternal\" : \"90013300\", \"lastModifiedDateTime\" : \"\\\\/Date(1651153849000+0000)\\\\/\", \"createdDateTime\" : \"\\\\/Date(1651138525000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1651138525000)\\\\/\", \"customDouble1\" : \"29\", \"lastModifiedBy\" : \"BOBA0001\", \"customString3\" : \"41243\", \"dateOfBirth\" : \"\\\\/Date(714096000000)\\\\/\", \"customString2\" : \"19920818-9994\", \"perPersonUuid\" : \"E381B1308EF041CE91C6E4EE0B6731BA\", \"lastModifiedOn\" : \"\\\\/Date(1651153849000)\\\\/\", \"customString1\" : null, \"createdBy\" : \"BOBA0001\", \"personId\" : \"1835\", \"customString1Nav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/customString1Nav\"}}, \"personalInfoNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personalInfoNav\"}}, \"emergencyContactNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/emergencyContactNav\"}}, \"customString3Nav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/customString3Nav\"}}, \"secondaryAssignmentsNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/secondaryAssignmentsNav\"}}, \"personEmpTerminationInfoNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personEmpTerminationInfoNav\"}}, \"employmentNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/employmentNav\"}}, \"homeAddressNavDEFLT\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/homeAddressNavDEFLT\"}}, \"phoneNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/phoneNav\"}}, \"nationalIdNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/nationalIdNav\"}}, \"userAccountNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/userAccountNav\"}}, \"personTypeUsageNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerPerson('90013300')\\/personTypeUsageNav\"}}, \"emailNav\" : {\"results\" : [{\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\", \"type\" : \"SFOData.PerEmail\"}, \"emailType\" : \"31767\", \"personIdExternal\" : \"90013300\", \"emailAddress\" : \"morampudi.nagababu1@postnordstg.onmicrosoft.com\", \"lastModifiedDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdBy\" : \"SFADMIN\", \"isPrimary\" : true, \"lastModifiedBy\" : \"SFADMIN\", \"createdDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"lastModifiedOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"emailTypeNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\\/emailTypeNav\"}}, \"personNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31767',personIdExternal='90013300')\\/personNav\"}}}, {\"__metadata\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\", \"type\" : \"SFOData.PerEmail\"}, \"emailType\" : \"31768\", \"personIdExternal\" : \"90013300\", \"emailAddress\" : \"Dummy@abc.com\", \"lastModifiedDateTime\" : \"\\\\/Date(1695029196000+0000)\\\\/\", \"createdBy\" : \"BOBA0001\", \"isPrimary\" : false, \"lastModifiedBy\" : \"SFADMIN\", \"createdDateTime\" : \"\\\\/Date(1651152959000+0000)\\\\/\", \"createdOn\" : \"\\\\/Date(1651152959000)\\\\/\", \"lastModifiedOn\" : \"\\\\/Date(1695029196000)\\\\/\", \"emailTypeNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\\/emailTypeNav\"}}, \"personNav\" : {\"__deferred\" : {\"uri\" : \"https://api55preview.sapsf.eu\\/odata\\/v2\\/PerEmail(emailType='31768',personIdExternal='90013300')\\/personNav\"}}}]}}}";
		 
		String email;//
		//email = "[\"morampudi.nagababu1@postnordstg.onmicrosoft.com\",\"Dummy@abc.com\"]";
		email = "[\"morampudi.nagababu1@postnordstg.onmicrosoft.com\",\"Dummy@abc.com\",\"Dummy1@abc.com\",\"Dummy2@abc.com\",\"Dumm3@abc.com\"]";
		//email = "[\"morampudi.nagababu1@postnordstg.onmicrosoft.com\"]";
		 String emailArr [] = email.split(",");
		 
		 System.out.println("Before Parsed Email "+ 0 +": " +emailArr[0]);
		 //System.out.println("Before Parsed Email "+1 +": " +emailArr[1]);
		 //System.out.println("Before Parsed Email "+1 +": " +emailArr[2]);
		 
		 System.out.println("**********Start Using Stmt****************");
		 System.out.println("After Parsed Email "+ 0 +": " +emailArr[0].substring(2, emailArr[0].toString().length()-1));
		 //System.out.println("AFter Parsed Email "+ 1 +": " +emailArr[1].substring(1, emailArr[1].toString().length()-1));
		 //System.out.println("AFter Parsed Email "+ 2 +": " +emailArr[2].substring(1, emailArr[2].toString().length()-2));
		 System.out.println("**********End Using Stmt****************");
		 
		 System.out.println("**********Start Loop Stmt****************");
		 
		 for(int i=0;i<emailArr.length;i++) {
			System.out.println("emailArr[" + i + "]=" + emailArr[i]);
			 if(i==0) {
				 System.out.println("After Parsed Email "+ i +": " +emailArr[i].substring(2, emailArr[i].toString().length()-1));
			 }else if (i==emailArr.length-1) {
				 System.out.println("AFter Parsed Email "+ i +": " +emailArr[i].substring(1, emailArr[i].toString().length()-2));
			 }else {
				 System.out.println("AFter Parsed Email "+ i +": " +emailArr[i].substring(1, emailArr[i].toString().length()-1));
				 
			 }
			 //System.out.println("Email "+i +":" +emailArr[i]).substring(int beginIndex, int endIndex);
		 }
		 
		 
		 System.out.println("**********End Loop Stmt****************");
		 

		 

	}

}
