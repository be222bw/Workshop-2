package model;

import java.util.UUID;

public class Member {
	private String name;
	private int numOfBoats;
	private String personalNumber;
	private UUID id;
	
	public Member(String n, String p, int b) {
		name = n;
		numOfBoats = b;
		id = UUID.randomUUID();
		
		try {
			if (!model.Verification.isCorrect(p)) {
				throw new Exception("The personal number is not correct!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage() + " Exiting.");
			System.exit(-1);
		}
		
		personalNumber = p;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPersonalNum() {
		return personalNumber;
	}
	
	public String getIdString() {
		return id.toString();
	}
	
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
