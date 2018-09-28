package model;

public class Member {
	private String name;
	private int numOfBoats;
	private String personalNumber;
	
	public Member(String n, String p, int b) {
		name = n;
		numOfBoats = b;
		
		try {
			if (!model.Verification.isCorrect(p)) {
				throw new Exception("The personal number is not living up to our expectations!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		personalNumber = p;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPersonalNum() {
		return personalNumber;
	}
	
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
