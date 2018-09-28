
public class program {

	public static void main(String[] args) {
		view.Console console = new view.Console();
		if (args.length < 1) {
			System.out.println("To show available arguments, use command-line argument --help, -h or /?.");
		} else {
			console.identifyArgument(args);
		}
	}
}
