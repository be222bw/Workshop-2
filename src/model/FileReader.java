package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Boat;
import model.Member;

/**
 * Class that reads file data in the correct order.
 * @author Björn Elmqvist, UDM17.
 *
 */
public class FileReader {
	private Scanner fileScan;
	private ArrayList<Member> memberList;
	
	/**
	 * Construct a FIleRead object.
	 * @param fileName The file name of the registry.
	 * @throws FileNotFoundException If file is not found.
	 */
	public FileReader(String fileName) throws FileNotFoundException {
		memberList = new ArrayList<Member>();
		fileScan = new Scanner(new File(fileName));
	}
	
	/**
	 * Read the members from file.
	 * @return ArrayList of the members.
	 * @throws Exception If personal number is incorrect.
	 */
	public ArrayList<Member> readMembers() throws Exception {
		while (fileScan.hasNext()) {
			int id = fileScan.nextInt();
			
			fileScan.useDelimiter("\"");
			fileScan.next();
			String name = fileScan.next();
			
			fileScan.reset();
			fileScan.next();
			
			String personalNumber = fileScan.next();
			int numOfBoats = fileScan.nextInt();
			
			Member member = new Member(name, personalNumber, id, numOfBoats);
			
			String type;
			double length;
			for (int i = 0; i < numOfBoats; i++) {
				fileScan.useDelimiter("\"");
				String we = fileScan.next();
				type = fileScan.next();
				fileScan.reset();
				we = fileScan.next();
				we = fileScan.next();
				length = Double.parseDouble(we);
				Boat boat = new Boat(type, length);
				member.assignBoat(boat);
			}
			memberList.add(member);
		}
		return memberList;
	}
}
 