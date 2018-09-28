package view;;

public class Console {

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
		default:
			System.out.println("Could not identify argument \"" + args[0] + "\"");
			break;
		}
	}
	
	public void showHelp(String args[]) {
		System.out.println("To create member, use command-line argument /cm <name> <number of boats> " + 
				"<personal number>.");
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
		
		
		System.out.println("Name: " + member.getName() +
				"\nPersonal number: " + member.getPersonalNum() + "\nNumber of boats: " +
				member.getNumOfBoats() + "\nId: " + member.getIdString());
	}
	

}
