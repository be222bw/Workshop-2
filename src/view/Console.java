package view;

import java.util.ArrayList;

import model.Boat;
import model.Member;

public class Console {
	private controller.FileWriter fw;
	private controller.FileReader fr;
	private ArrayList<Member> memberList;
	
	/** Constructs a console object.
	 * @param fileName The file name of the member registry.
	 */
	public Console(String fileName) {
		fw = new controller.FileWriter(fileName);
		fr = new  controller.FileReader(fileName);
		memberList = fr.readMembers(); // Memory for memberList is allocated in the constructor for FileRead.
	}
	/**
	 * Identifies the first argument, and calls the relative method.
	 * @param args Array of arguments, whose first element is the argument to be identified.
	 */
	public void identifyArgument(String[] args) {
		switch (args[0]) {
		case "/h":
			showHelp(args[0]);
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
		case "/db":
			deleteBoat(args);
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
	
	
	
	private void deleteBoat(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		tooFewArguments(args.length < 3);
		String idString = args[1];
		Member member = getMemberById(idString);
		int i = Integer.parseInt(args[2]);
		
		try {
			if (i > member.getNumOfBoats() -1) {
				throw new IndexOutOfBoundsException("No such boat index!");
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		Boat boat = member.getBoat(i);
		System.out.println("Deleting " + boat.getType() + " from " + member.getName() + ".");
		member.deleteBoat(i);
		member.decrementNumberOfBoats();
		
		fw.overwriteMemberFile(memberList);
		
	}
	/**
	 * Changes either the type or length of a boat.
	 * @param args The arguments used.
	 */
	public void changeBoatInfo(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		
		tooFewArguments(args.length < 5);
		
		String idString = args[2];
		Member member = getMemberById(idString);
		int boatIndex = Integer.parseInt(args[3]);
		Boat boat = member.getBoat(boatIndex);
		
		switch (args[1]) {
		case "/ct":
			boat.setType(args[4]);
			break;
		case "/cl":
			boat.setLength(Double.parseDouble(args[4]));
			break;
		default:
			System.err.println("Could not identify parameter \"" + args[1] + "\".");
			break;
		}
		
		fw.overwriteMemberFile(memberList);
	}
	
	/**
	 * Changes either the name or the personal number of a member. Id cannot be changed, but the number of boats
	 * is changed if one adds a boat or removes a boat, i.e. not here.
	 * @param args The arguments.
	 */
	public void changeMemberInfo(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		
		tooFewArguments(args.length < 4);
		
		String idString = args[2];
		Member member = getMemberById(idString);
		
		switch (args[1]) {
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
	 * @param args The arguments.
	 */
	public void showHelp(String command) {
		switch (command) {
		case "/cm":
			System.out.println("Create member. The syntax is /cm <name> <personal number>.");
			break;
		case "/db":
			System.out.println("Delete boat. The syntax is /db <member id> <boat index>.");
			break;
		case "/lm":
			System.out.println("List members. The syntax is /lm (/v). /v means a verbose list.");
			break;
		case "/vm":
			System.out.println("View a specific member. The syntax is /vm (/v) <member id>. /v means a verbose view.");
			break;
		case "/dm":
			System.out.println("Delete member. The syntax is /dm <member id>.");
			break;
		case "/cmi":
			System.out.println("Change member info. Syntax is /cmi /cn <member id> <new name> or" +
					" /cmi /cpn <member id> <new personal number>.");
			break;
		case "/cbi":
			System.out.println("Change boat info. Syntax is /cbi /ct <member id> <boat index> <new type> " +
					"or /cm /cl <member id> <boat index> <new length>.");
			break;
		case "/rnb":
			System.out.println("Register new boat. Syntax is /rnb <member id> <boat type> <boat length>.");
			break;
		case "/h":
			System.out.println("/cm Create member.");
			System.out.println("/db Delete boat.");
			System.out.println("/lm List members.");
			System.out.println("/vm View specific member.");
			System.out.println("/dm Delete member.");
			System.out.println("/cmi Change member info.");
			System.out.println("/cbi Change boat info.");
			System.out.println("/rnb Register new boat.");
			System.out.println("Type <parameter> /h for more specific help.");
			break;
		default:
			System.out.println("Parameter not recognised!");
		}
	}
	
	/**
	 * Lists members.
	 * @param args The arguments.
	 */
	public void listMembers(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		for (int i = 0; i < memberList.size(); i++) {
			Member member = memberList.get(i);
			boolean isVerbose = args.length > 1 && args[1].equals("/v");
			printMember(member, isVerbose);
		}
	}
	
	/**
	 * Registers a new boat.
	 * @param args The arguments.
	 */
	public void registerNewBoat(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		tooFewArguments(args.length < 4);
	
		Member member = getMemberById(args[1]);
		Boat boat = new Boat(args[2], Double.parseDouble(args[3]));
		member.assignBoat(boat);
		member.incrementNumOfBoats();
		fw.overwriteMemberFile(memberList);
	}
	
	/**
	 * Creates a member.
	 * @param args The arguments.
	 */
	public void createMember(String args[]) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		
		tooFewArguments(args.length < 3);
		Member member = new Member(args[1], args[2], 0); // When creating member, start with no boats.
		
		fw.writeMemberData(member);
		
		printMember(member, false);
	}
	
	/** Views a specific member.
	 * @param args The arguments.
	 */
public void viewMember(String[] args) {
	if (args[args.length - 1].equals("/h")) {
		showHelp(args[0]);
		return;
	}
	
	tooFewArguments(args.length < 2);
	
	boolean isVerbose = args.length > 2 &&  args[1].equals("/v");
	String idString = (isVerbose ? args[2] : args[1]); //If /v is supplied, id is the third parameter.
	Member member = getMemberById(idString);
	printMember(member, isVerbose);
}
	
/** Deletes a specific member.
 * @param args The arguments.
 */
	public void deleteMember(String[] args) {
		if (args[args.length - 1].equals("/h")) {
			showHelp(args[0]);
			return;
		}
		
		tooFewArguments(args.length < 2);
		
		String idString = args[1];
		
		int size = memberList.size();
		for (int i = 0; i < size; i++) {
			Member member = memberList.get(i);
			if (member.getIdString().equals(idString)) {
				System.out.println("Removing member " + member.getName() + ".");
				memberList.remove(i);
				break;
			}
		}
		
		fw.overwriteMemberFile(memberList);
	}

/**
 * Print a specific member.
 * @param member The member to be printed.
 * @param isVerbose Whether to print it verbosely.
 */
private void printMember(Member member, boolean isVerbose) {
	System.out.println("Name: " + member.getName() + "\r\n" +
			(isVerbose ? "Personal number: " + member.getPersonalNum().toString() + "\r\n" : "") + 
			"Id: " + member.getIdString() + "\r\nNumber of boats: " + member.getNumOfBoats());
	if (isVerbose) {
		for (int n= 0; n < member.getNumOfBoats(); n++) {
			Boat boat = member.getBoat(n);
			System.out.println("\tBoat type: " + boat.getType() + " Length: " + boat.getLength() + " metres.");
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
}