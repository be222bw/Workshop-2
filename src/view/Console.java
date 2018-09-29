package view;

import java.util.Scanner;

public class Console {

	private FileWrite fw;
	
	public Console(String fileName) {
		fw = new FileWrite(fileName);
	}
	/**
	 * Identifies the first argument, and calls the relative method.
	 * @param args Array of arguments, whose first element is the argument to be identified.
	 */
	public void identifyArgument(String[] args) {
		switch (args[0]) {
		case "--help":
		case "/?":
		case "-h":
			showHelp(args);
			break;
		case "/cm":
		case "--create-member":
		case "-cm":
			createMember(args);
			break;
		case "/lm":
		case "--list-members":
		case "-lm":
			
		default:
			System.out.println("Could not identify argument \"" + args[0] + "\"");
			break;
		}
	}
	
	public void showHelp(String args[]) {
		System.out.println("To create member, use command-line argument /cm \"<name>\" <number of boats> " + 
				"<personal number>.");
		System.out.println("To list members, use /lm /v.");
	}
	
	public void createMember(String args[]) {
		try {
			if (args.length < 4) {
				throw new Exception("Not enough arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		model.Member member = new model.Member(args[1], args[2], Integer.parseInt(args[3]));
		try {
			if (!model.Verification.isCorrect(member.getPersonalNum())) {
				throw new Exception("The personal number is not correct!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage() + " Exiting.");
			System.exit(-1);
		}

		String type;
		double length;
		Scanner keyBoardInput = new Scanner(System.in);
		for (int i = 0; i < Integer.parseInt(args[3]); i++) {
			System.out.print("Enter type boat number " + (i + 1) + ": ");
			type = keyBoardInput.nextLine();
			System.out.print("Enter length of boat (in metres): ");
			length = keyBoardInput.nextDouble();
			member.assignBoat(type, length);
		}
		
		fw.writeMemberData(member);
		keyBoardInput.close();
		
		System.out.println("Name: " + member.getName() +
				"\nPersonal number: " + member.getPersonalNum() + "\nNumber of boats: " +
				member.getNumOfBoats() + "\nId: " + member.getIdString());
		
		int numOfBoats = Integer.parseInt(args[3]);
		for (int i = 0; i < numOfBoats; i++) {
			System.out.println("Boat number " + (i + 1) + ":\n" + member.getBoat(i).getType() + " " +
		member.getBoat(i).getLength() + " metres.");
		}
	}
	

}
