package controller;

import java.util.ArrayList;
import view.Auxiliary;
import model.Boat;
import model.FileWriter;
import model.Member;

/**
 * Class that handles the boat-oriented commands.
 * @author Björn Elmqvist, UDM17.
 *
 */
public class BoatHandler {
	private ArrayList<Member> memberList;
	private FileWriter fw;
	private Auxiliary aux;
	
	/**
	 * Constructs a BoatHandler object.
	 * @param memberList The member list used.
	 * @param fr The file reader used.
	 * @param fw The file writer used.
	 */
	public BoatHandler(ArrayList<Member> memberList, Auxiliary aux, String fileName) {
		this.memberList = memberList;
		this.fw = new FileWriter(fileName);
		this.aux = aux;
	}
	
	/**
	 * Registers a new boat.
	 * @param memberId The id of the member who is to register a new boat.
	 * @param type The type of boat to register.
	 * @param The length of the boat to register.
	 */
	public void registerNewBoat(int memberId, String type, double length) {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = new Boat(type, length);
		member.assignBoat(boat);
		member.incrementNumOfBoats();
		fw.overwriteMemberFile(memberList);
	}
	
	public void changeBoatType(int memberId, String boatType, int boatIndex) {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = member.getBoat(boatIndex);
		boat.setType(boatType);
	}
	
	public void changeBoatLength(int memberId, double boatLength, int boatIndex) {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = member.getBoat(boatIndex);
		boat.setLength(boatLength);
	}
	
	/**
	 * Deletes a specific boat.
	 * @param id The id of the member whose boat to delete.
	 * @param i Index of the boat to delete.
	 */
	public void deleteBoat(int id, int i) {
		Member member = aux.getMemberById(id, memberList);
		
		try {
			if (i > member.getNumOfBoats() -1) {
				throw new IndexOutOfBoundsException("No such boat index!");
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		Boat boat = member.getBoat(i);
		System.out.println("Deleting " + boat.getType() + " from " + member.getName() + ".");
		member.deleteBoat(i);
		member.decrementNumberOfBoats();
		
		fw.overwriteMemberFile(memberList);	
	}
	
}
