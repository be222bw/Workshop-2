
public class program {

	public static void main(String[] args) {
		view.Console console = new view.Console("Members.txt");
		if (args.length < 1) {
			System.out.println("To show available arguments, use \"/?\"");
		} else {
			console.identifyArgument(args);
		}
	}
}
