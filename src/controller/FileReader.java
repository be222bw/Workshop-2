package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Boat;
import model.Member;

public class FileReader {
	private Scanner fileScan;
	private ArrayList<Member> memberList;
	
	/**
	 * Construct a FIleRead object.
	 * @param fileName The file name of the registry.
	 */
	public FileReader(String fileName) {
		memberList = new ArrayList<Member>();
		try {
			fileScan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the members from file.
	 * @return ArrayList of the members.
	 */
	public ArrayList<Member> readMembers() {
		while (fileScan.hasNext()) {
			String id = fileScan.next();
			
			fileScan.useDelimiter("\"");
			fileScan.next();
			String name = fileScan.next();
			
			fileScan.reset();
			fileScan.next();
			
			String personalNumber = fileScan.next();
			int numOfBoats = fileScan.nextInt();
			
			String uuid = id;
			
			Member member = new Member(name, personalNumber, uuid, numOfBoats);
			
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
 