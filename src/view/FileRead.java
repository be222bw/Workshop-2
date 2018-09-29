package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
		
	}
}
 