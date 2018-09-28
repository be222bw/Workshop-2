package model;

import java.time.Year;
import java.util.regex.Pattern;

/** 
 * This class is about verifying the validity of the personal number, a task more complex than it may seem.
 * This is my own code from an earlier Java project.
 * @author Björn Elmqvist
 *
 */
public class Verification {
	/**
	 * Checks whether there are the correct amount of digits and if there is a correct delimiter.
	 * @param pNr The personal number to check.
	 * @return The validity of the number.
	 */
	public static boolean hasReasonableDigits (String pNr) {
		return Pattern.matches("^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(-|\\+)[0-9]{4}$", pNr);
	}
	
	/**
	 * Checks the control sum.
	 * @param pNr The number to check.
	 * @return The validity of the control sum.
	 */
	public static boolean checkControlSum (String pNr) {
		int sum = 0;
		boolean correct = false;
		String numericalPnr = pNr.substring(0, 6) + pNr.substring(7); // New string without hyphen or plus.
		int length = numericalPnr.length();
		for (int i = 0; i < length; i++) {
		int digit = (i == 0 || i % 2 == 0 ? (numericalPnr.charAt(i) - '0') * 2 : (numericalPnr.charAt(i) - '0'));
		if (digit > 9) {
			digit = digit % 10 + digit / 10;
		}
		sum += digit;
		}
		if (sum % 10 == 0) {
			correct = true;
		}
		return correct;
	}
	
	/**
	 * Gets the first part of the number, before the delimiter.
	 * @param pNr The personal number whose first part  to get.
	 * @return The first part of the number.
	 */
	public static String getFirstPart (String pNr){
		return pNr.substring(0, pNr.length() - 5);
	}
	
	/**
	 * Gets the second part, after the delimiter.
	 * @param pNr The personal number whose second part to get.
	 * @return The second part of the number.
	 */
	public static String getSecondPart (String pNr) {
		return pNr.substring(pNr.length() - 4, pNr.length());
	}
	
	/**
	 * Checks the validity of the entire personal number.
	 * @param pNr The personal number to validate.
	 * @return The validity of the personal number.
	 */
	public static boolean isCorrect(String pNr) {
		if (!hasReasonableDigits(pNr)) {
			return false;
		}
		if (!hasReasonableDay(pNr)) {
			return false;
		}
		if (!checkControlSum(pNr)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether the month supplied in the personal number has enough days.
	 * @param pNr The personal number.
	 * @return Whether the month has enough days or not.
	 */
	public static boolean hasReasonableDay (String pNr) {
		final int[] daysAMonth = {31, (isLeapYear(pNr) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int day = Integer.parseInt(pNr.substring(4, 6));
		int month = Integer.parseInt(pNr.substring(2, 4));
		
		if (day > daysAMonth[month - 1]) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether the year supplied in the personal number is a leap year.
	 * @param pNr The personal number.
	 * @return The being or not-being a leap year.
	 */
	public static boolean isLeapYear (String pNr) {
		int year = toLongYear(pNr);
		
		if (year % 100 == 0 && year % 400 == 0 || year % 4 == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the year in four digits.
	 * @param pNr The personal number.
	 * @return The year in four digits.
	 */
	public static int toLongYear (String pNr) {
		int shortYear = Integer.parseInt(pNr.substring(0, 2));
		boolean overOneHundred = pNr.substring(6, 7).equals("+");
		int currentYear = Year.now().getValue();
		
		if (!overOneHundred && 2000 + shortYear <= currentYear) {
			return 2000 + shortYear;
		} else if (!overOneHundred && 2000 + shortYear > currentYear) {
			return 1900 + shortYear;
		} else if (overOneHundred && currentYear - (1900 + shortYear) >= 100) {
			return 1900 + shortYear;
		} else {
			return 1800 + shortYear;
		}
	}
}
