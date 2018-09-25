package view;

import java.util.Scanner;

public class Console {
	/**
	 * Shows a welcome message as well as instructions for the console.
	 */
	public void showInstructions () {
		System.out.println("Welcome to the member registry!\n\n");
		System.out.println("To create a new member, input cm.");
		Scanner scan = new Scanner(System.in);
		String arg = scan.next();
		identifyArgument(arg);
	}
	
	public void identifyArgument(String arg) {
		Scanner scan = new Scanner(System.in);
		switch (arg) {
		case "cm":
			System.out.print("Enter name: ");
			String name = scan.nextLine();
			System.out.print("Enter ID: ");
			String id = scan.nextLine();
			System.out.print("Enter number of boats: ");
			int numOfBoats = scan.nextInt();
			System.out.print("Enter personal number: ");
			String personalNumber = scan.next();
			model.Member member = new model.Member(name, id, numOfBoats, personalNumber);
			
			System.out.println("Name: " + member.getName() + "\nID: " + member.getId() +
					"\nPersonal number: " + member.getPersonalNum() + "\nNumber of boats: " +
					member.getNumOfBoats());
			break;
		default:
			break;
		}
	}
	

}
