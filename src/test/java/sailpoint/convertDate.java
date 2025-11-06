package com.sailpoint;

import com.jayway.jsonpath.JsonPath;
//import connector.common.JsonUtil;
import net.minidev.json.JSONArray;

import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;


import sailpoint.tools.GeneralException;
import sailpoint.tools.Util;

public class convertDate {

static String dateFormat = "dd.MM.yyyy";
static String timeZone = "Europe/Berlin";
static String fuzzyOffset = "2";

public static String objectToString(String responseBody, String jsonPath) {
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

// If Monday offset is set to 2
public static int offsetStartDate(String dt,String timeZone,String dateFormat) throws ParseException {
	int offset = 0;
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, new Locale("en"));
	sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
	Date dtTocheck = sdf.parse(dt);
	Calendar cal = Calendar.getInstance();
	cal.setTime(dtTocheck);
	int day = cal.get(Calendar.DAY_OF_WEEK);
	if (day == Calendar.MONDAY) {
		offset = 2;
	} 
	return offset;
}
	
// If Friday offset is set to 2
public static int offsetEndDate(String dt, String timeZone, String dateFormat) throws ParseException {
	int offset = 0;
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, new Locale("en"));
	sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
	Date dtTocheck = sdf.parse(dt);
	Calendar cal = Calendar.getInstance();
	cal.setTime(dtTocheck);
	int day = cal.get(Calendar.DAY_OF_WEEK);;
	if (day == Calendar.FRIDAY) {
		offset = 2;
	} else if (day == Calendar.SUNDAY) {
		offset = 0;
	}
	return offset;
}

// Function to apply offset to StartDate
public static String offsetToStartDate(String date, int days, String timeZone, String dateFormat) throws ParseException {
	Calendar c = Calendar.getInstance();
	DateFormat df = new SimpleDateFormat(dateFormat);
	df.setTimeZone(TimeZone.getTimeZone(timeZone));
	Date myDate = df.parse(date.trim());
	c.setTime(myDate);
	c.add(Calendar.DATE, ((days) * -1));
	String toDate = df.format(c.getTime());
	return toDate;
}

// Function to apply offset to EndDate
public static String offsetToEndDate(String date, int days, String timeZone, String dateFormat)	throws ParseException {
	Calendar c = Calendar.getInstance();
	DateFormat df = new SimpleDateFormat(dateFormat);
	df.setTimeZone(TimeZone.getTimeZone(timeZone));
	Date myDate = df.parse(date.trim());
	c.setTime(myDate);
	c.add(Calendar.DATE, (days));
	String toDate = df.format(c.getTime());
	return toDate;
}

// Function to calculate days between two dates
public static long daysBetween (String date1, String date2, String dateFormat) throws DateTimeParseException{
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
	LocalDate firstDate = LocalDate.parse(date1, formatter);
	LocalDate secondDate = LocalDate.parse(date2, formatter);
	return ChronoUnit.DAYS.between(firstDate, secondDate);
}

public static long max(long[] arr) {
		// Initialize max with first element of array.
		Long max = arr[0];
		// Loop through the array
		for (int i = 0; i < arr.length; i++) {
			// Compare elements of array with max
			if (arr[i] > max)
				max = arr[i];
		}
		System.out.println("Largest element present in given array: " + max);
		return max;
}

public static String consecutiveLoasDuration(String startDate, String endDate) {
	String SDArray [] = startDate.split(",");
	String EDArray[] = endDate.split(",");
	long outputDuration = 0;
	String outputArray [] = {Long.toString(outputDuration)};
	for (int i = 0; i < EDArray.length; i++) {
		int offsetSD = 0;
		int offsetED = 0;
		String newStartDate, newEndDate;
		if (EDArray.length == 1) {
			// calculate the real start and end dates that include weekends
			try {
				offsetSD = offsetStartDate(SDArray[0], timeZone, dateFormat);
				offsetED = offsetEndDate(EDArray[0], timeZone, dateFormat);
				newStartDate = offsetToStartDate(SDArray[0], offsetSD, timeZone, dateFormat);
				newEndDate = offsetToEndDate(EDArray[0], offsetED, timeZone, dateFormat);
				// The LoA duration is based on the single start and end  LoA dates, nothing to change
				outputDuration = daysBetween(newStartDate, newEndDate, dateFormat);
				outputArray[0] = Long.toString(outputDuration);
				System.out.println("LoA duration is " + outputArray[0]);
				return outputArray[0];
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("ParseException :" + e);
			}
		} else if (i < EDArray.length - 1) {
			System.out.println("Handling multiple LoAs");
			try {
				offsetED = offsetEndDate(EDArray[i], timeZone, dateFormat);
				newEndDate = offsetToEndDate(EDArray[i], offsetED, timeZone, dateFormat);
				System.out.println("Handling LoA #" + (i+1));
				for (int j = i+1; j < SDArray.length; j++) {
					offsetSD = offsetStartDate(SDArray[j], timeZone, dateFormat);
					newStartDate = offsetToStartDate(SDArray[j], offsetSD, timeZone, dateFormat);
					System.out.println("Handling LOA startdate " + newStartDate + " for enddate : " + newEndDate);
					long durationBetweenLoa = daysBetween(newEndDate, newStartDate, dateFormat);
					System.out.println("days between LOAs is " +  Long.toString(durationBetweenLoa));
					// 1 day is added to the duration calulated by daysBetween that counts the number of days between two dates					 
					if (durationBetweenLoa <= (Integer.parseInt(fuzzyOffset)+1)) System.out.println("consecutive LoAs duration is " + Long.toString(daysBetween(
						offsetToStartDate(SDArray[i], offsetStartDate(SDArray[i], timeZone, dateFormat), timeZone, dateFormat), 
						offsetToEndDate(EDArray[j], 1 + offsetEndDate(EDArray[j], timeZone, dateFormat), timeZone, dateFormat), dateFormat)));
					else System.out.println("single (but adjusted) duration is " + Long.toString(daysBetween(
						offsetToStartDate(SDArray[i], offsetStartDate(SDArray[i], timeZone, dateFormat), timeZone, dateFormat),
						offsetToEndDate(EDArray[i], offsetEndDate(EDArray[i], timeZone, dateFormat), timeZone, dateFormat), dateFormat)));
					//System.out.println("MAx LOA duration found is " Long.toString(max(outputDuration)));
				}
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("ParseException :" + e);
			}
		}		
	}
	return outputArray.toString();
}

public static String selectLoa(String startDate, String endDate) {
	String SDArray[] = startDate.split(",");
	String EDArray[] = endDate.split(",");
	String output = null;
	long StartDateEval = 0;
	long EndDateEval = 0;
	// generate reference date as of today
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	System.out.println("\ntoday : " + formatter.format(now));
	System.out.println("\nbetween today and 10.06.2024: " + daysBetween(formatter.format(now), "10.06.2024", dateFormat));
	
	String newStartDate, newEndDate;
	for (int i = 0; i < EDArray.length; i++) {
		if (EDArray.length == 1) {
			try {
				StartDateEval = daysBetween(formatter.format(now), SDArray[0], dateFormat);
				EndDateEval = daysBetween(formatter.format(now), EDArray[0], dateFormat);
				if ((StartDateEval <= 0) && (EndDateEval >= 1)) output = SDArray[0];
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("ParseException :" + e);
			}
			return output;
		} else if (i < EDArray.length ) {
			System.out.println("Handling multiple LoAs");
			try {
				System.out.println("Handling LoA #" + i);
				long StartDateEvalLoop = daysBetween(formatter.format(now), SDArray[0], dateFormat);
				long EndDateEvalLoop = daysBetween(formatter.format(now), EDArray[0], dateFormat);
				if ((StartDateEval <= 0) && (EndDateEval >= 1))
					output = SDArray[0];
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("ParseException :" + e);
			}
		}
	}
	return outputArray.toString();
}

public static void main(String[] args) throws InterruptedException {
		
		//String responseBody = "{\"data\":[{\"ID Personal\":\"1032\",\"Abwesenheit von\":\"30.03.2024\",\"Abwesenheit Buchung\":\"Mutterschutz \",\"Abwesenheit bis\":\"30.06.2024\"},{\"ID Personal\":\"1006\",\"Abwesenheit von\":\"15.04.2024\",\"Abwesenheit Buchung\":\"Krank ohne Lohnfortzahlung\",\"Abwesenheit bis\":\"17.04.2024\"},{\"ID Personal\":\"955\",\"Abwesenheit von\":\"13.05.2024\",\"Abwesenheit Buchung\":\"Elternzeit\",\"Abwesenheit bis\":\"16.07.2024\"},{\"ID Personal\":\"617\",\"Abwesenheit von\":\"19.05.2024\",\"Abwesenheit Buchung\":\"Mutterschutz\",\"Abwesenheit bis\":\"25.08.2024\"},{\"ID Personal\":\"481\",\"Abwesenheit von\":\"16.04.2024\",\"Abwesenheit Buchung\":\"Elternzeit\",\"Abwesenheit bis\":\"04.02.2025\"},{\"ID Personal\":\"402\",\"Abwesenheit von\":\"01.02.2024\",\"Abwesenheit Buchung\":\"Freistellung\",\"Abwesenheit bis\":\"30.06.2024\"}],\"status\":null}";
		String responseBody = "{\"data\":[{\"ID Personal\": \"1032\",\"Abwesenheit von\": \"17.06.2024\",\"Abwesenheit Buchung\": \"Mutterschutz \",\"Abwesenheit bis\": \"28.06.2024\"},{\"ID Personal\": \"1032\",\"Abwesenheit von\": \"03.07.2024\",\"Abwesenheit Buchung\": \"Mutterschutz \",\"Abwesenheit bis\": \"26.07.2024\"},{\"ID Personal\": \"1032\",\"Abwesenheit von\": \"23.12.2024\",\"Abwesenheit Buchung\": \"Mutterschutz \",\"Abwesenheit bis\": \"27.03.2024\"},{\"ID Personal\": \"1006\",\"Abwesenheit von\": \"15.04.2024\",\"Abwesenheit Buchung\": \"Krank ohne Lohnfortzahlung\",\"Abwesenheit bis\": \"17.04.2024\"},{\"ID Personal\": \"955\",\"Abwesenheit von\": \"13.05.2024\",\"Abwesenheit Buchung\": \"Elternzeit\",\"Abwesenheit bis\": \"16.07.2024\"},{\"ID Personal\": \"617\",\"Abwesenheit von\": \"19.05.2024\",\"Abwesenheit Buchung\": \"Mutterschutz\",\"Abwesenheit bis\": \"25.08.2024\"},{\"ID Personal\": \"481\",\"Abwesenheit von\": \"16.04.2024\",\"Abwesenheit Buchung\": \"Elternzeit\",\"Abwesenheit bis\": \"04.02.2025\"},{\"ID Personal\": \"402\",\"Abwesenheit von\": \"01.02.2024\",\"Abwesenheit Buchung\": \"Freistellung\",\"Abwesenheit bis\": \"30.06.2024\"}],\"status\": null}";
		// System.out.println("responseBody : " + responseBody);
		String personalID = "1032";

		String leaveDetailsJsonPath = "$.data[?(@['ID Personal']==\"" + personalID + "\")]";
		String leaveDetails = JsonPath.read(responseBody, leaveDetailsJsonPath).toString();
		if (leaveDetails.contains("{")) System.out.println("all leave details for " + personalID + " are " + leaveDetails);

		String leaveTypeJsonPath = "$.data[?(@['ID Personal']==\"" + personalID + "\")].['Abwesenheit Buchung']";
		//String leaveType = JsonPath.read(responseBody, leaveTypeJsonPath).toString();
		String leaveType = objectToString(responseBody, leaveTypeJsonPath);
		if (leaveType != null) System.out.println("leave type for " + personalID + " is " + leaveType);

		String leaveStartDateJsonPath = "$.data[?(@['ID Personal']==\"" + personalID + "\")].['Abwesenheit von']";
		String leaveStartDate = objectToString(responseBody, leaveStartDateJsonPath);
		if (leaveStartDate != null) System.out.println("leave start date for " + personalID + " is " + leaveStartDate);
		
		String leaveEndDateJsonPath = "$.data[?(@['ID Personal']==\"" + personalID + "\")].['Abwesenheit bis']";
		String leaveEndDate = objectToString(responseBody, leaveEndDateJsonPath);
		if (leaveEndDate != null) System.out.println("leave end date for " + personalID + " is " + leaveEndDate);


		String inputDate = "03.06.2024";
		//String twoDaysB4StartDt = offsetToStartDate(inputDate, "2", timeZone, dateFormat);
		//System.out.println("twoDaysB4StartDt " + twoDaysB4StartDt);
		int offsetSD=0;
		int offsetED=0;
		String newStartDate = inputDate;
		String newEndDate = inputDate;

		try {
			offsetSD = offsetStartDate(leaveStartDate, timeZone, dateFormat);
			offsetED = offsetEndDate(leaveEndDate, timeZone, dateFormat);
			newStartDate = offsetToStartDate(leaveStartDate, offsetSD, timeZone, dateFormat);
			newEndDate = offsetToEndDate(leaveEndDate, offsetED, timeZone, dateFormat);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ParseException :" + e);
		}
		System.out.println("With offset (" + offsetSD + ") the startDate taken into account is " + newStartDate );
		System.out.println("With offset (" + offsetED + ") the endDate taken into account is " + newEndDate);
		//long projectedLeaveDuration = 0;
		//long duration = 0;
		//try {
		//	projectedLeaveDuration = daysBetween(newStartDate, newEndDate, dateFormat);
		//	duration = daysBetween(leaveStartDate, leaveEndDate, dateFormat);
		//} catch (DateTimeParseException e) {
		//	e.printStackTrace();
		//	System.out.println("DateTimeParseException :" + e);
		//} 

		//System.out.println("The leave duration is effectively " + projectedLeaveDuration + " day(s).");
		//System.out.println("Without applying any offset it was " + duration + " day(s).");
		System.out.println("\n\tconsecutiveLoasDuration: " + consecutiveLoasDuration(leaveStartDate,leaveEndDate));
		System.out.println("\nbetween 24.12.2024 and 27.12.2024: " + daysBetween("24.12.2024", "27.12.2024", dateFormat));


				
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		System.out.println("\ntoday : " +formatter.format(now));
		System.out.println("\nbetween today and 10.06.2024: " + daysBetween(formatter.format(now), "10.06.2024", dateFormat));
		System.out.println("between today and 11.06.2024: " + daysBetween(formatter.format(now), "11.06.2024", dateFormat));
		System.out.println("between today and 12.06.2024: " + daysBetween(formatter.format(now), "12.06.2024", dateFormat));

		String text = "firstword second";
		text = text.contains(" ") ? text.split(" ")[0]:text;
		//System.out.println( text + "\nfirst : " + text.substring(0, text.indexOf(' ')));
		System.out.println("first : " + text);
	}
}
