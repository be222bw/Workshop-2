package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Boat;
import model.Member;

/**
 * Class that outputs data in the correct order.
 * @author Bj�rn Elmqvist, UDM17.
 *
 */
public class FileWriter {
	private File registry;
	private PrintWriter pw;
	private PrintWriter fileOverwriter;

	/**
	 * Constructs a FileWrite object.
	 * @param fileName The file name of the registry.
	 */
	public FileWriter(String fileName) {
		registry = new File(fileName);
	}
	
	/**
	 * Write the member data of one member.
	 * @param member The member whose data is to be written.
	 * @throws FileNotFoundException 
	 */
	public void writeMemberData(Member member) throws FileNotFoundException {
		pw = new PrintWriter(new FileOutputStream(registry, true));
		int numOfBoats = member.getNumOfBoats();
		pw.append(member.getId() + " \""+
		member.getName() + "\" " + 
		member.getPersonalNum() + " " +
		member.getNumOfBoats());
		if (numOfBoats == 0) {
			pw.print(System.lineSeparator());
			pw.close();
			return;
		}
		
		for (int i = 0; i < numOfBoats; i++) {
			Boat boat = member.getBoat(i);
			pw.append((i == 0 ? " " : "") + "\"" + boat.getType() + "\" " + boat.getLength() + 
					(i == numOfBoats - 1 ? System.lineSeparator()  : " "));
		}
		pw.close();
	}
	
	/**
	 * Overwrites the entire file with a list of members.
	 * @param members The members.
	 * @throws FileNotFoundException 
	 */
	public void overwriteMemberFile(ArrayList<Member> members) throws FileNotFoundException {
		fileOverwriter = new PrintWriter(registry);
		int size = members.size();
		for (int i = 0; i < size; i++) {
			Member member = members.get(i);
			int numOfBoats = member.getNumOfBoats();
			fileOverwriter.print(member.getId() + " \"" +
			member.getName() + "\" " +
			member.getPersonalNum() + " " +
			member.getNumOfBoats());
			if (numOfBoats == 0) {
				fileOverwriter.print(System.lineSeparator());
				continue;
			}
			
			for (int n = 0; n < numOfBoats; n++) {
				Boat boat = member.getBoat(n);
				fileOverwriter.print((n == 0 ? " " : "") +"\"" +boat.getType() + "\" " + boat.getLength() +
						(n == numOfBoats - 1 ? System.lineSeparator() : " "));
			}
		}
		fileOverwriter.close();
	}
}
