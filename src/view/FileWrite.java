package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Boat;
import model.Member;

public class FileWrite {
	private File registry;
	private PrintWriter pw;
	private PrintWriter fileOverwriter;

	/**
	 * Construct a FileWrite object.
	 * @param fileName The file name of the registry.
	 */
	FileWrite(String fileName) {
		registry = new File(fileName);
	}
	
	/**
	 * Write the member data of one member.
	 * @param member The member whose data is to be written.
	 */
	public void writeMemberData(Member member) {
		try {
			pw = new PrintWriter(new FileOutputStream(registry, true));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
		int numOfBoats = member.getNumOfBoats();
		pw.append(member.getIdString() + " \""+
		member.getName() + "\" " + 
		member.getNumOfBoats() +  " " +
		member.getPersonalNum());
		for (int i = 0; i < numOfBoats; i++) {
			Boat boat = member.getBoat(i);
			pw.append((i == 0 ? " " : "") + "\"" + boat.getType() + "\" " + boat.getLength() + 
					(i == numOfBoats - 1 ? "\r\n"  : " "));
		}
		pw.close();
	}
	
	/**
	 * Overwrites the entire file with a list of members.
	 * @param members The members.
	 */
	public void overwriteMemberFile(ArrayList<Member> members) {
		try {
			fileOverwriter = new PrintWriter(registry);
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(-2);
		}
		int size = members.size();
		for (int i = 0; i < size; i++) {
			Member member = members.get(i);
			int numOfBoats = member.getNumOfBoats();
			fileOverwriter.print(member.getIdString() + " \"" +
			member.getName() + "\" " +
			member.getNumOfBoats() + " " +
			member.getPersonalNum() + " ");
			
			for (int n = 0; n < numOfBoats; n++) {
				Boat boat = member.getBoat(n);
				fileOverwriter.print("\"" +boat.getType() + " \" " + boat.getLength() +
						(n == numOfBoats - 1 ? "\r\n" : " "));
			}
		}
		fileOverwriter.close();
	}
}
