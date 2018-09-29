package model;

public class Boat {
	private String type;
	private double length;
	
	public Boat(String type, double length) {
		this.type = type;
		this.length = length;
	}
	
	public String getType() {
		return type;
	}
	
	public double getLength() {
		return length;
	}
}
