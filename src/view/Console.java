package view;

public class Console {
	/**
	 * Shows a welcome message as well as instructions for the console.
	 */
	public void showInstructions () {
		System.out.println("Welcome to the member registry!\n\n");
		System.out.println("To create a new member, input cm.");
	}
	
	public boolean identifyArgument(String arg) {
		switch (arg) {
		case "cm":
			// Create member
			return true;
		default:
			return false;
		}
	}
	

}
