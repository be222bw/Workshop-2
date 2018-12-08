package controller;

import java.io.FileNotFoundException;
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
	public BoatHandler(ArrayList<Member> memberList, String fileName) {
		this.memberList = memberList;
		this.fw = new FileWriter(fileName);
		this.aux = new Auxiliary();
	}
	
	/**
	 * Registers a new boat.
	 * @param memberId The id of the member who is to register a new boat.
	 * @param type The type of boat to register.
	 * @param The length of the boat to register.
	 * @return The boat registered.
	 * @throws FileNotFoundException If file is not found.
	 */
	public Boat registerNewBoat(int memberId, String type, double length) throws FileNotFoundException {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = new Boat(type, length);
		member.assignBoat(boat);
		member.incrementNumOfBoats();
		fw.overwriteMemberFile(memberList);
		return boat;
	}
	
	/** Changes the type of a boat.
	 * @param memberId Id of the member.
	 * @param boatType The new boat type.
	 * @param boatIndex The index of the boat.
	 * @return The old boat type.
	 * @throws FileNotFoundException If file is not found.
	 */
	public String changeBoatType(int memberId, String boatType, int boatIndex) throws FileNotFoundException {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = member.getBoat(boatIndex);
		String oldType = boat.getType();
		boat.setType(boatType);
		fw.overwriteMemberFile(memberList);
		return oldType;
	}
	
	/**
	 * Changes the length of a boat.
	 * @param memberId Id of the member.
	 * @param boatLength The new length.
	 * @param boatIndex The index of the boat.
	 * @return The old length.
	 * @throws FileNotFoundException If file is not found.
	 */
	public double changeBoatLength(int memberId, double boatLength, int boatIndex) throws FileNotFoundException {
		Member member = aux.getMemberById(memberId, memberList);
		Boat boat = member.getBoat(boatIndex);
		double oldLength = boat.getLength();
		boat.setLength(boatLength);
		fw.overwriteMemberFile(memberList);
		return oldLength;
	}
	/**
	 * Deletes a specific boat.
	 * @param id The id of the member whose boat to delete.
	 * @param i Index of the boat to delete.
	 * @return The boat deleted.
	 * @throws FileNotFoundException If file is not found.
	 * @throws IndecOutOfBoundsException
	 */
	public void deleteBoat(int id, int i) throws IndexOutOfBoundsException, FileNotFoundException {
		Member member = aux.getMemberById(id, memberList);
		
		if (i > member.getNumOfBoats() -1) {
			throw new IndexOutOfBoundsException("No such boat index!");
		}
		member.deleteBoat(i);
		member.decrementNumberOfBoats();
		
		fw.overwriteMemberFile(memberList);	
	}
}
