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
			showHelp(null);
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
		case "/rnb":
			registerNewBoat(args);
			break;
		default:
			System.out.println("Could not identify argument \"" + args[0] + "\"");
			break;
		}
	}
	
	
	/**
	 * Iterates the member list, to find the right member. If he is not found, returns null.
	 * @param idString The id string of the member to be returned.
	 * @return The member with the given id, or, if he is not found, null.
	 */
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
	
	/**
	 * Changes either the type or length of a boat.
	 * @param args The arguments used.
	 */
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
	
	/**
	 * Changes either the name or the personal number of a member. Id cannot be changed, but the number of boats
	 * is changed if one adds a boat or removes a boat, i.e. not here.
	 * @param args The arguments used.
	 */
	public void changeMemberInfo(String[] args) {
		if (args.length > 1 && args[1].equals("/?")) {
			showHelp(args[0]);
			return;
		}
		
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
	
	/**
	 * Lists of commands available in the programme, or shows how to use a comand.
	 * @param args
	 */
	public void showHelp(String command) {
		switch (command) {
		case "/cm":
			System.out.println("Create member. The syntax is /cm \"<name>\" <personal number> <number of boats> " +
					"(<type of boat number one> <length of boat number one> ... <type of boat number n> <length of boat "
					+ "number n>.");
			break;
		case "/lm":
			System.out.println("List members. The syntax is /lm (/v). /v means a verbose list.");
			break;
		case "/vm":
			System.out.println("View a specific member. The syntax is /vm <id>.");
			break;
		case "/dm":
			System.out.println("Delete member. The syntax is /dm <id>.");
			break;
		case "/cmi":
			System.out.println("Change member info. Syntax is /cm /cn <new name> or /cm /cpn <new personal number>.");
			break;
		case "/cbi":
			System.out.println("Change boat info. Syntax is /cbi /ct <new type> or /cm /cl <new length>.");
			break;
		case "/rnb":
			System.out.println("Register new boat. Syntax is /rnb <id> \"<boat type>\" <boat length>.");
			break;
		default:
			System.out.println("/cm Create member.");
			System.out.println("/lm List members.");
			System.out.println("/vm View specific member.");
			System.out.println("/dm Delete member.");
			System.out.println("/cmi Change member info.");
			System.out.println("/cbi Change boat info.");
			System.out.println("/rnb Register new boat.");
		}
	}
	
	public void listMembers(String[] args) {
		if (args.length > 1 && args[1].equals("/?")) {
			showHelp(args[0]);
			return;
		}
		for (int i = 0; i < memberList.size(); i++) {
			Member member = memberList.get(i);
			boolean isVerbose = args.length > 1 && args[1].equals("/v");
			printMember(member, isVerbose);
		}
	}
	
	public void registerNewBoat(String[] args) {
		if (args.length > 1 && args[1].equals("/?")) {
			showHelp(args[0]);
			return;
		}
		tooFewArguments(args.length < 4);
	
		Member member = getMemberById(args[1]);
		Boat boat = new Boat(args[2], Double.parseDouble(args[3]));
		member.assignBoat(boat);
	}
	
	
	public void createMember(String args[]) {
		if (args.length > 1 && args[1].equals("/?")) {
			showHelp(args[0]);
			return;
		}
		
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
			Boat boat = new Boat(type, length);
			member.assignBoat(boat);
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
	if (args.length > 1 && args[1].equals("/?")) {
		showHelp(args[0]);
		return;
	}
	
	tooFewArguments(args.length < 2);
	
	String idString = args[1];
	Member member = getMemberById(idString);
	printMember(member, args.length > 2 && args[2].equals("/v"));
}
	
	public void deleteMember(String[] args) {
		if (args.length > 1 && args[1].equals("/?")) {
			showHelp(args[0]);
			return;
		}
		
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
	/**
 	* Throws an exception if there are too few arguments.
 	* @param shallThrow Whether it shall be thrown or not.
 	*/
	private void tooFewArguments(boolean shallThrow) { // I throw this exception so much, I might as well automise it.
		try {
			if (shallThrow) {
				throw new Exception("Too few arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
	}
}