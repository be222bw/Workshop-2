package model;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
	private String name;
	private int numOfBoats;
	private ArrayList<Boat> boatList;
	private String personalNumber;
	private UUID id;
	
	/** Construct a Member object and assign a random id.
	 * @param name The full name of the member
	 * @param personalNumber The personal number.
	 * @param numOfBoats The number of boats.
	 */
	public Member(String name, String personalNumber, int numOfBoats) {
		this.name = name;
		this.numOfBoats = numOfBoats;
		this.id = UUID.randomUUID();
		this.personalNumber = personalNumber;
		this.boatList = new ArrayList<Boat>();
	}
	
	/**
	 * Construct a member object with the id supplied.
	 * @param name
	 * @param personalNumber
	 * @param id
	 * @param numOfBoats
	 */
	public Member(String name, String personalNumber, UUID id, int numOfBoats) {
		this.name = name;
		this.personalNumber = personalNumber;
		this.id = id;
		this.numOfBoats = numOfBoats;
		boatList = new ArrayList<Boat>();
	}
	
	/**
	 * Assign a boat to the member.
	 * @param type Type of boat
	 * @param length Length of boat.
	 */
	public void assignBoat(String type, double length) {
		boatList.add(new Boat(type, length));
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
