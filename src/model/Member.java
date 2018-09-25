package model;

public class Member {
	private String name;
	private String id;
	private int numOfBoats;
	private String personalNumber;
	
	public Member(String n, String i, int b, String p) {
		name = n;
		id = i;
		numOfBoats = b;
		personalNumber = p;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPersonalNum() {
		return personalNumber;
	}
	
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
