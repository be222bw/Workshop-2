package model;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
	private String name;
	private int numOfBoats;
	private ArrayList<Boat> boatList;
	private String personalNumber;
	private UUID id;
	
	public Member(String n, String p, int b) {
		name = n;
		numOfBoats = b;
		id = UUID.randomUUID();
		personalNumber = p;
		boatList = new ArrayList<Boat>();
	}
	
	public void assignBoat(String desc) {
		boatList.add(new Boat(desc));
	}
	
	public String getName() {
		return name;
	}
	
	public Boat getBoat(int n) {
		return boatList.get(n);
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
