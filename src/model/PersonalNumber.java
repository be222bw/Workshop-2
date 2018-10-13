package model;

import java.time.Year;
import java.util.regex.Pattern;

/** 
 * Personal number with validation. Most of this class is self-contained. Modified version of one of my old projects.
 * @author Björn Elmqvist
 *
 */
public class PersonalNumber {
	
	private String personalNumber;
	
	/**
	 * Construct a Personal Number object.
	 * @param personalNumber The String representation of a personal number.
	 */
	public PersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
		try {
			if (!isCorrect()) {
				throw new Exception("Personal number is not living up to our expectations!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-3);
		}
	}
	
	/**
	 * Checks whether there are the correct amount of digits and if there is a correct delimiter.
	 * @return The validity of the number.
	 */
	private boolean hasReasonableDigits() {
		return Pattern.matches("^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(-|\\+)[0-9]{4}$", personalNumber);
	}
	
	/**
	 * Checks the control sum.
	 * @return The validity of the control sum.
	 */
	private boolean checkControlSum() {
		int sum = 0;
		boolean correct = false;
		String numericalPnr = personalNumber.substring(0, 6) + personalNumber.substring(7); // New string without hyphen or plus.
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
	 * Checks whether the month supplied in the personal number has enough days.
	 * @return Whether the month has enough days or not.
	 */
	private boolean hasReasonableDay() {
		final int[] daysAMonth = {31, (isLeapYear() ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int day = Integer.parseInt(personalNumber.substring(4, 6));
		int month = Integer.parseInt(personalNumber.substring(2, 4));
		
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
	private boolean isLeapYear() {
		int year = toLongYear();
		
		if (year % 100 == 0 && year % 400 == 0 || year % 4 == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the year in four digits.
	 * @return The year in four digits.
	 */
	private int toLongYear() {
		int shortYear = Integer.parseInt(personalNumber.substring(0, 2));
		boolean overOneHundred = personalNumber.substring(6, 7).equals("+");
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
	
	/**
	 * Checks the validity of the entire personal number.
	 * @return The validity of the personal number.
	 */
	private boolean isCorrect() {
		if (!hasReasonableDigits()) {
			return false;
		}
		if (!hasReasonableDay()) {
			return false;
		}
		if (!checkControlSum()) {
			return false;
		}
		
		return true;
	}
	
	/** Returns the String representation of a personal number.
	 * @return The String representation of the personal number.
	 */
	public String toString() {
		return personalNumber;
	}
}