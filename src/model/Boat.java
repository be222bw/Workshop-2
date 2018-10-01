package model;

public class Boat {
	private String type;
	private double length;
	
	/**
	 * Constructs a Boat object.
	 * @param type The boat type.
	 * @param length The length of the boat.
	 */
	public Boat(String type, double length) {
		this.type = type;
		this.length = length;
	}
	
	/**
	 * Gets the type of boat.
	 * @return The type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the length of the boat.
	 * @return The length.
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Sets (or changes) the boat type.
	 * @param type The type.
	 */
	public void setType(String type) { // This was in the requirements. I guess they rebuild the boat. 
		this.type = type;
	}
	
	/**
	 * Sets (or changes) the length of the boat.
	 * @param length The length.
	 */
	public void setLength(double length) { // Same comment as above.
		this.length = length;
	}
}
