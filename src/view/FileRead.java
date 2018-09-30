package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FileRead {
	private Scanner fileScan;
	private ArrayList<model.Member> memberList;
	
	public FileRead(String fileName) {
		memberList = new ArrayList<model.Member>();
		try {
			fileScan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<model.Member> readMembers() {
		while (fileScan.hasNextLine()) {
			String id = fileScan.next();
			
			fileScan.useDelimiter("\"");
			String name = fileScan.next();
			
			fileScan.reset();
			
			int numOfBoats = fileScan.nextInt();
			String personalNumber = fileScan.next();
			
			UUID uuid = UUID.fromString(id);
			
			model.Member member = new model.Member(name, personalNumber, uuid, numOfBoats);
			
			String type;
			double length;
			for (int i = 0; i < numOfBoats; i++) {
				fileScan.useDelimiter("\"");
				type = fileScan.next();
				fileScan.reset();
				length = fileScan.nextDouble();
				member.assignBoat(type, length);
			}
			memberList.add(member);
		}
		return memberList;
	}
}
 