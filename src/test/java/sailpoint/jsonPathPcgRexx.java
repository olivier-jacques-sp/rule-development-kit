package com.sailpoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import java.text.*;
import java.util.*;

import org.slf4j.helpers.Util;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import sailpoint.tools.Util;

public class jsonPathPcgRexx {

public static  String objectToString (String responseBody , String jsonPath){
	JSONArray jsonarray = JsonPath.read(responseBody, jsonPath);
	String outputString = null;
	if (jsonarray.size() > 0) {
		outputString = (String) jsonarray.get(0);
		for (int i = 1; i < jsonarray.size(); i++) {
			outputString = outputString + "," + (String) jsonarray.get(i);
		}
	}
	return outputString;
}

private String formattedDate(String strDate) {
	Date date = new Date(Long.valueOf(strDate.substring(6,16)));
	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	format.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
	return format.format(date);
}

private static int DayOfWeek (String date) {
	
		int y0 = y - (14 - m) / 12;
		int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
		int m0 = m + 12 * ((14 - m) / 12) - 2;
		int d0 = (d + x + (31 * m0) / 12) % 7;
	return d0;
}

public static String convertDate(String stringDate){
	if ( stringDate != null ) {
		DateTimeFormatter dateFormatZoned = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return outputFormat.format(ZonedDateTime.parse(stringDate, dateFormatZoned));
	}
    return null;
}

public static void main(String[] args) throws InterruptedException {
		String responseBody = "{\"data\":[{\"ID Personal\":\"1032\",\"Abwesenheit von\":\"17.04.2024\",\"Abwesenheit Buchung\":\"Krank ohne Lohnfortzahlung\",\"Abwesenheit bis\":\"19.04.2024\"},{\"ID Personal\":\"1032\",\"Abwesenheit von\":\"17.04.2023\",\"Abwesenheit Buchung\":\"Krank ohne Lohnfortzahlung2\",\"Abwesenheit bis\":\"19.04.2023\"}{\"ID Personal\":\"1006\",\"Abwesenheit von\":\"15.04.2024\",\"Abwesenheit Buchung\":\"Krank ohne Lohnfortzahlung\",\"Abwesenheit bis\":\"17.04.2024\"},{\"ID Personal\":\"955\",\"Abwesenheit von\":\"13.05.2024\",\"Abwesenheit Buchung\":\"Elternzeit\",\"Abwesenheit bis\":\"16.07.2024\"},{\"ID Personal\":\"617\",\"Abwesenheit von\":\"19.05.2024\",\"Abwesenheit Buchung\":\"Mutterschutz\",\"Abwesenheit bis\":\"25.08.2024\"},{\"ID Personal\":\"481\",\"Abwesenheit von\":\"16.04.2024\",\"Abwesenheit Buchung\":\"Elternzeit\",\"Abwesenheit bis\":\"04.02.2025\"},{\"ID Personal\":\"402\",\"Abwesenheit von\":\"01.02.2024\",\"Abwesenheit Buchung\":\"Freistellung\",\"Abwesenheit bis\":\"30.06.2024\"}],\"status\":null}";
		// System.out.println("responseBody : " + responseBody);
		String IDPersonal = "1032";

		String leaveDetailsJsonPath = "$.data[?(@['ID Personal']==\"" + IDPersonal + "\")]";
		String leaveDetails = JsonPath.read(responseBody, leaveDetailsJsonPath).toString();
		if (leaveDetails.contains("{")) System.out.println("all leave details for " + IDPersonal + " are " + leaveDetails);

		String leaveTypeJsonPath = "$.data[?(@['ID Personal']==\"" + IDPersonal + "\")].['Abwesenheit Buchung']";
		//String leaveType = JsonPath.read(responseBody, leaveTypeJsonPath).toString();
		String leaveType = objectToString(responseBody, leaveTypeJsonPath);
		if (leaveType != null) System.out.println("leave type for " + IDPersonal + " is " + leaveType);

		String leaveStartDateJsonPath = "$.data[?(@['ID Personal']==\"" + IDPersonal + "\")].['Abwesenheit von']";
		String leaveStartDate = objectToString(responseBody, leaveStartDateJsonPath);
		if (leaveStartDate != null) System.out.println("leave start date for " + IDPersonal + " is " + leaveStartDate);
		
		String leaveEndDateJsonPath = "$.data[?(@['ID Personal']==\"" + IDPersonal + "\")].['Abwesenheit bis']";
		String leaveEndDate = objectToString(responseBody, leaveEndDateJsonPath);
		if (leaveEndDate != null) System.out.println("leave end date for " + IDPersonal + " is " + leaveEndDate);

		String inputDate = "01.08.2012";
		SimpleDateFormat in = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedlDate = null;
		convertedlDate = out.parse(inputDate);
		System.out.println("date " + inputDate + " converted to " + convertedlDate);
  		//SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
  		//DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
  		//Date dt1 = format1.parse(inputDate);
  		//String finalDay = format2.format(dt1);

		//if (leaveStartDate != null) System.out.println("leave start date is on " + DayOfWeek(30,05,2024));

 		Instant instant = Instant.now();

        ZonedDateTime zdt = instant.atZone(ZoneId.of("America/New_York"));
System.out.println(zdt.getDayOfWeek());
	}
}
