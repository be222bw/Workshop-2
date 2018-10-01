package view;

import java.util.ArrayList;

import model.Boat;
import model.Member;
import model.Verification;

public class Console {

	private FileWrite fw;
	private FileRead fr;
	private ArrayList<Member> memberList;
	
	/**
	 * @param fileName The file name of the member registry.
	 */
	public Console(String fileName) {
		fw = new FileWrite(fileName);
		fr = new  FileRead(fileName);
		memberList = fr.readMembers(); // Memory for memberList is allocated in the constructor for FileRead.
	}
	/**
	 * Identifies the first argument, and calls the relative method.
	 * @param args Array of arguments, whose first element is the argument to be identified.
	 */
	public void identifyArgument(String[] args) {
		switch (args[0]) {
		case "/?":
			showHelp(args);
			break;
		case "/cm":
			createMember(args);
			break;
		case "/lm":
			listMembers(args);
			break;
		case "/vm":
			viewMember(args);
			break;
		case "/dm":
			deleteMember(args);
			break;
		case "/cmi":
			changeMemberInfo(args);
			break;
		case "/cbi":
			changeBoatInfo(args);
			break;
		default:
			System.out.println("Could not identify argument \"" + args[0] + "\"");
			break;
		}
	}
	
	
	
	public Member getMemberById(String idString) {
		int size = memberList.size();
		for (int i = 0; i < size; i++) {
			Member member = memberList.get(i);
			if (idString.equals(member.getIdString())) {
				return member;
			}
		}
		return null;
	}
	
	public void changeBoatInfo(String[] args) {
		tooFewArguments(args.length < 5);
		
		String idString = args[1];
		Member member = getMemberById(idString);
		int boatNumber = Integer.parseInt(args[2]);
		Boat boat = member.getBoat(boatNumber);
		
		switch (args[3]) {
		case "/ct":
			boat.setType(args[4]);
			break;
		case "/cl":
			boat.setLength(Double.parseDouble(args[4]));
			break;
		default:
			System.err.println("Could not identify parameter \"" + args[3] + ".\"");
		}
	}
	
	public void changeMemberInfo(String[] args) {
		tooFewArguments(args.length < 4);
		
		String idString = args[1];
		Member member = getMemberById(idString);
		
		switch (args[2]) {
		case "/cn":
			member.setName(args[3]);
			break;
		case "/cpn":
			member.setPersonalNum(args[3]);
			break;
		default:
			System.err.println("Could not identify parameter \"" + args[3] + ".\"");;
		}
		fw.overwriteMemberFile(memberList);
	}
	
	public void showHelp(String args[]) {
		System.out.println("To create member, use command-line argument /cm \"<name>\" <number of boats> " + 
				"<personal number>.");
		System.out.println("To list members, use /lm /v.");
	}
	
	public void listMembers(String[] args) {
			for (int i = 0; i < memberList.size(); i++) {
				Member member = memberList.get(i);
				printMember(member, args[1].equals("/v"));
			}
	}
	
	public void registerNewBoat(String[] args) {
	
	}
	
	/**
	 * Throws an exception if there are too few arguments.
	 * @param shallThrow Whether it shall be thrown or not.
	 */
	public void tooFewArguments(boolean shallThrow) { // I throw this exception so much, I might as well automise it.
		try {
			if (shallThrow) {
				throw new Exception("Too few arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
	}
	
	public void createMember(String args[]) {
		tooFewArguments(args.length < 4);
		int numOfBoats = Integer.parseInt(args[3]);
		tooFewArguments(args.length < 4 + numOfBoats * 2);
		Member member = new Member(args[1], args[2], Integer.parseInt(args[3]));
		try {
			if (!Verification.isCorrect(member.getPersonalNum())) {
				throw new Exception("The personal number is not correct!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-2);
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
	
public void viewMember(String[] args) {
	tooFewArguments(args.length < 2);
	
	String idString = args[1];
	Member member = getMemberById(idString);
	printMember(member, args.length > 2 && args[2].equals("/v"));
}
	
	private void printMember(Member member, boolean isVerbose) {
		System.out.println("Name: " + member.getName() + " " +
				(isVerbose ? "Personal number: " + member.getPersonalNum() + " " : "") + 
				"Id: " + member.getIdString() + " Number of boats: " + member.getNumOfBoats());
			if (isVerbose) {
					for (int n= 0; n < member.getNumOfBoats(); n++) {
						Boat boat = member.getBoat(n);
						System.out.println("Boat type: " + boat.getType() + " Length: " + boat.getLength() + " metres.");
					}
			}
	}
	public void deleteMember(String[] args) {
		tooFewArguments(args.length < 2);
		
		String idString = args[1];
		
		int size = memberList.size();
		for (int i = 0; i < size; i++) {
			Member member = memberList.get(i);
			if (member.getIdString().equals(idString)) {
				memberList.remove(i);
				break;
			}
		}
		
		fw.overwriteMemberFile(memberList);
	}
}
