package model;

import java.util.ArrayList;

/**
 * Representation of a member.
 * @author Björn Elmqvist, UDM17.
 *
 */
public class Member {
	private String name;
	private int numOfBoats;
	private ArrayList<Boat> boatList;
	private PersonalNumber personalNumber;
	private int id;
	
	/**
	 * Constructs a member object.
	 * @param name
	 * @param personalNumber
	 * @param id
	 * @param numOfBoats
	 * @throws Exception 
	 */
	public Member(String name, String personalNumber, int id, int numOfBoats) throws Exception {
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
	 * @throws Exception 
	 */
	public void setPersonalNum(String personalNumber) throws Exception {
		this.personalNumber = new PersonalNumber(personalNumber);
	}
	
	/**
	 * Get the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get the number of boats.
	 * @return The number of boats.
	 */
	public int getNumOfBoats() {
		return numOfBoats;
	}
}
