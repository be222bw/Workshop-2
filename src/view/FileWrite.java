package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileWrite {
	private File registry;
	private PrintWriter pw;

	FileWrite(String fileName) {
		registry = new File(fileName);
		try {
			pw = new PrintWriter(new FileOutputStream(registry, true));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
	}
	
	public void writeMemberData(model.Member member) {
		int numOfBoats = member.getNumOfBoats();
		pw.append(member.getIdString() + " \""
		+ member.getName() + "\" " + 
		member.getNumOfBoats() +  " " +
		member.getPersonalNum());
		for (int i = 0; i < numOfBoats; i++) {
			model.Boat boat = member.getBoat(i);
			pw.append((i == 0 ? " " : "") + "\"" + boat.getType() + "\" " + boat.getLength() + 
					(i == numOfBoats - 1 ? "\r\n"  : " "));
		}
		pw.close();
	}
}
