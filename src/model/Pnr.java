package be222bw_lab3;

import java.time.Year;
import java.util.regex.Pattern;

public class Pnr {

	public static void main(String[] args) {
		String pNr = "940301-3671";
		String secondPnr = "940301-3676";

		if (isCorrect(pNr)) {
			System.out.println("Första delen av personnumret är: " + getFirstPart(pNr));
			System.out.println("Den andra delen av personnumret är: " + getSecondPart(pNr));
		
			System.out.println("Det är" + (isMaleNumber(pNr) ? " " : " inte ") + "ett manligt personnummer.");
			System.out.println("Det är" + (isFemaleNumber(pNr) ? " " : " inte ") + "ett kvinnligt personnummer.");
		
			System.out.println("Personnumren är" + (areEqual(pNr, secondPnr) ? " " : " inte ") + "ekvivalenta.");
		} else {
			System.out.println("Personnumret är ogiltigt.");
		}
	}
	
	public static String getFirstPart (String pNr){
		return pNr.substring(0, pNr.length() - 5);
	}
	
	public static String getSecondPart (String pNr) {
		return pNr.substring(pNr.length() - 4, pNr.length());
	}
	
	public static boolean isMaleNumber (String pNr) {
		return (pNr.charAt(pNr.length() - 2) - '0') % 2 == 1; // Omvandla siffertecknet till motsvarande numeriska värde och se om detta är ojämnt.
	}
	
	public static boolean isFemaleNumber (String pNr) {
		return (pNr.charAt(pNr.length() - 2) - '0') % 2 == 0;
	}
	
	public static boolean areEqual(String first, String second) {
		return first.equals(second);
	}
	
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
	public static boolean hasReasonableDay (String pNr) {
		final int[] daysAMonth = {31, (isLeapYear(pNr) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int day = Integer.parseInt(pNr.substring(4, 6));
		int month = Integer.parseInt(pNr.substring(2, 4));
		
		if (day > daysAMonth[month - 1]) {
			return false;
		}
		
		return true;
	}
	
	public static boolean hasReasonableDigits (String pNr) {
		return Pattern.matches("^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(-|\\+)[0-9]{4}$", pNr);
		// Kontrollerar både längden och de ingående tecknen.
	}
	
	public static boolean isLeapYear (String pNr) {
		int year = toLongYear(pNr);
		
		if (year % 100 == 0 && year % 400 == 0 || year % 4 == 0) {
			return true;
		}
		
		return false;
	}
	
	public static int toLongYear (String pNr) { // Fulla året krävs för att kontrollera årets status som skottår.
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
	
	public static boolean checkControlSum (String pNr) {
		int sum = 0;
		boolean correct = false;
		String numericalPnr = pNr.substring(0, 6) + pNr.substring(7); // Ny sträng utan bindestreck eller plustecken.
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
}


