package model;

public class Boat {
	private String type;
	private double length;
	
	/**
	 * Construct a Boat object.
	 * @param type The boat type.
	 * @param length The length of the boat.
	 */
	public Boat(String type, double length) {
		this.type = type;
		this.length = length;
	}
	
	/**
	 * Get the type of boat.
	 * @return The type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Get the length of the boat.
	 * @return The length.
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Set (or change) the boat type.
	 * @param type The type.
	 */
	public void setType(String type) { // This was in the requirements. I guess they rebuild the boat. 
		this.type = type;
	}
	
	/**
	 * Set (or change) the length of the boat.
	 * @param length The length.
	 */
	public void setLength(double length) { // Same comment as above.
		this.length = length;
	}
}
