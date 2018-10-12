package model;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
	private String name;
	private int numOfBoats;
	private ArrayList<Boat> boatList;
	private PersonalNumber personalNumber;
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
		this.personalNumber = new PersonalNumber(personalNumber);
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
		this.personalNumber = new PersonalNumber(personalNumber);
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
	}
	
	/**
	 * Increments the number of boats.
	 */
	public void incrementNumOfBoats() {
		numOfBoats++;
	}
	
	
	/**
	 * Deletes the boat specified by the index.
	 * @param i The index.
	 */
	public void deleteBoat(int i) {
		boatList.remove(i);
	}
	
	/**
	 * Decrements the number of boats.
	 */
	public void decrementNumberOfBoats() {
		numOfBoats--;
	}
	
	/**
	 * Gets the name of the member.
	 * @return The name of the member.
	 */
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
	
	/**
	 * Get the boat specified by the index.
	 * @param i The index.
	 * @return The boat.
	 */
	public Boat getBoat(int i) {
		return boatList.get(i);
	}
	
	/**
	 * Get the member's personal number.
	 * @return The personal number.
	 */
	public PersonalNumber getPersonalNum() {
		return personalNumber;
	}
	
	/**
	 * Set the personal number.
	 * @param personalNumber The new personal number.
	 */
	public void setPersonalNum(String personalNumber) {
		this.personalNumber = new PersonalNumber(personalNumber);
	}
	
	/**
	 * Get the id in string form.
	 * @return The id in string form.
	 */
	public String getIdString() {
		return id.toString();
	}
	
	/**
	 * Get the number of boats.
	 * @return The number of boats.
	 */
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
