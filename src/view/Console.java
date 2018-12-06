package view;

import java.util.ArrayList;
import model.Member;

/**
 * Class that identifies the command-line argument.
 * @author Björn Elmqvist, UDM17
 *
 */
public class Console {
	private model.FileReader fr;
	private ArrayList<Member> memberList;
	private controller.MemberHandler mh;
	private controller.BoatHandler bh;
	
	/** Constructs a console object.
	 * @param fileName The file name of the member registry.
	 */
	public Console(String fileName) {
		fr = new  model.FileReader(fileName);
		memberList = fr.readMembers(); // Memory for memberList is allocated in the constructor for FileRead.
		mh = new controller.MemberHandler(memberList, fileName);
		bh = new controller.BoatHandler(memberList, fileName);
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
		case "/cm": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			tooFewArguments(args.length < 3);
			String name = args[1];
			String personalNumber = args[2];
			mh.createMember(name, personalNumber);
			break;
		}
		case "/lm": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			boolean isVerbose = args.length > 1 && args[1].equals("/v");
			mh.listMembers(isVerbose);
			break;
		}
		case "/vm": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			boolean isVerbose = args.length > 2 &&  args[1].equals("/v");
			int memberId = (isVerbose ? Integer.parseInt(args[2]) : Integer.parseInt(args[1]));
			mh.viewMember(memberId, isVerbose);
			break;
		}
		case "/dm": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			int id = Integer.parseInt(args[1]);
			mh.deleteMember(id);
			break;
		}
		case "/db": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			tooFewArguments(args.length < 3);
			int id = Integer.parseInt(args[1]);
			int i = Integer.parseInt(args[2]);
			bh.deleteBoat(id, i);
			break;
		}
		case "/cmi": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			tooFewArguments(args.length < 4);
			String subCommand = args[1];
			int id = Integer.parseInt(args[2]);
			switch (subCommand) {
			case "/cn":
				String name = args[3];
				mh.changeMemberName(id, name);
				break;
			case "/cpn":
				String personalNumber = args[3];
				mh.changeMemberPersonalNum(id, personalNumber);
				break;
			}
			break;
		}
		case "/cbi": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			tooFewArguments(args.length < 5);
			String subCommand = args[1];
			int id = Integer.parseInt(args[2]);
			int boatIndex = Integer.parseInt(args[3]);
			switch (subCommand) {
			case "/ct":
				String boatType = args[4];
				bh.changeBoatType(id, boatType, boatIndex);
				break;
			case "/cl":
				double boatLength = Double.parseDouble(args[4]);
				bh.changeBoatLength(id, boatLength, boatIndex);
				break;
			default:
				System.err.println("Could not identify parameter \"" + subCommand + "\".");
				break;
			}	
			break;
		}
		case "/rnb": {
			if (args[args.length - 1].equals("/h")) {
				showHelp(args[0]);
				return;
			}
			int id = Integer.parseInt(args[1]);
			String boatType = args[2];
			double boatLength = Double.parseDouble(args[3]);
			bh.registerNewBoat(id, boatType, boatLength);
			break;
		}
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
					"or /cbi /cl <member id> <boat index> <new length>.");
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
			System.exit(-1);
		}
	}
}