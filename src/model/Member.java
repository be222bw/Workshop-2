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
		
		try {
			if (!Verification.isCorrect(personalNumber)) {
				throw new Exception("Personal number is not living up to our expectations!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-3);
		}
		
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
	public void assignBoat(Boat boat) {
		boatList.add(boat);
		numOfBoats++;
	}
	
	public void deleteBoat(int i) {
		boatList.remove(i);
		numOfBoats--;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Change the member's name.
	 * @param name The name to which to change.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Boat getBoat(int n) {
		return boatList.get(n);
	}
	
	public String getPersonalNum() {
		return personalNumber;
	}
	
	public void setPersonalNum(String personalNumber) {
		try {
			if (!Verification.isCorrect(personalNumber)) {
				throw new Exception("Personal number is not living up to our expectations!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-3);
		}
		
		this.personalNumber = personalNumber;
	}
	
	public String getIdString() {
		return id.toString();
	}
	
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
