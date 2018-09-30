package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Member;

public class Console {

	private FileWrite fw;
	private FileRead fr;
	
	public Console(String fileName) {
		fw = new FileWrite(fileName);
		fr = new  FileRead(fileName);
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
			listMembers(args);
			break;
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
	
	public void listMembers(String[] args) {
		ArrayList<Member> memberList = fr.readMembers();
		if (args[1].equals("/v")) {
			for (int i = 0; i < memberList.size(); i++) {
				Member member = memberList.get(i);
				System.out.println(member.getName() + " " + member.getIdString() + " " + member.getNumOfBoats());
			}
		}
	}
	
	public void createMember(String args[]) {
		try {
			if (args.length < 4) {
				throw new Exception("Not enough arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		int numOfBoats = Integer.parseInt(args[3]);
		try {
			if (args.length < 4 + numOfBoats * 2) {
				throw new Exception("Not enough arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
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
		for (int i = 4; i < numOfBoats * 2 + 3; i += 2) {
			type = args[i];
			length = Double.parseDouble(args[i + 1]);
			member.assignBoat(type, length);
		}
		
		fw.writeMemberData(member);
		
		System.out.println("Name: " + member.getName() +
				"\nPersonal number: " + member.getPersonalNum() + "\nNumber of boats: " +
				member.getNumOfBoats() + "\nId: " + member.getIdString());
		
		for (int i = 0; i < numOfBoats; i++) {
			System.out.println("Boat number " + (i + 1) + ":\n" + member.getBoat(i).getType() + " " +
		member.getBoat(i).getLength() + " metres.");
		}
	}
	

}
