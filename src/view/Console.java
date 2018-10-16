package view;

import java.util.ArrayList;

import model.Member;

/**
 * Class that identifies the command-line argument.
 * @author Björn Elmqvist, UDM17
 *
 */
public class Console {
	private model.FileWriter fw;
	private model.FileReader fr;
	private ArrayList<Member> memberList;
	MemberHandler mh;
	BoatHandler bh;
	
	/** Constructs a console object.
	 * @param fileName The file name of the member registry.
	 */
	public Console(String fileName) {
		fw = new model.FileWriter(fileName);
		fr = new  model.FileReader(fileName);
		memberList = fr.readMembers(); // Memory for memberList is allocated in the constructor for FileRead.
		mh = new MemberHandler(memberList, fw);
		bh = new BoatHandler(memberList, fw);
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
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			mh.createMember(args);
			break;
		case "/lm":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			mh.listMembers(args);
			break;
		case "/vm":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			mh.viewMember(args);
			break;
		case "/dm":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			mh.deleteMember(args);
			break;
		case "/db":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			bh.deleteBoat(args);
			break;
		case "/cmi":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			mh.changeMemberInfo(args);
			break;
		case "/cbi":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			bh.changeBoatInfo(args);
			break;
		case "/rnb":
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			bh.registerNewBoat(args);
			break;
		default:
			System.out.println("Could not identify argument \"" + args[0] + "\"");
			break;
		}
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
}