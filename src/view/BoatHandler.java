package view;

import java.util.ArrayList;

import model.Boat;
import model.FileWriter;
import model.Member;

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
	public BoatHandler(ArrayList<Member> memberList, FileWriter fw) {
		this.memberList = memberList;
		this.fw = fw;
		this.aux = new Auxiliary();
	}
	
	/**
	 * Registers a new boat.
	 * @param args The arguments.
	 */
	public void registerNewBoat(String[] args) {
		aux.tooFewArguments(args.length < 4);
	
		Member member = aux.getMemberById(args[1], memberList);
		Boat boat = new Boat(args[2], Double.parseDouble(args[3]));
		member.assignBoat(boat);
		member.incrementNumOfBoats();
		fw.overwriteMemberFile(memberList);
	}
	
	/**
	 * Changes either the type or length of a boat.
	 * @param args The arguments used.
	 */
	public void changeBoatInfo(String[] args) {		
		aux.tooFewArguments(args.length < 5);
		
		String idString = args[2];
		Member member = aux.getMemberById(idString, memberList);
		int boatIndex = Integer.parseInt(args[3]);
		Boat boat = member.getBoat(boatIndex);
		
		switch (args[1]) {
		case "/ct":
			boat.setType(args[4]);
			break;
		case "/cl":
			boat.setLength(Double.parseDouble(args[4]));
			break;
		default:
			System.err.println("Could not identify parameter \"" + args[1] + "\".");
			break;
		}
		fw.overwriteMemberFile(memberList);
	}
	
	/**
	 * Deletes a specific boat.
	 * @param args Argument array, which contains the id and the boat index.
	 */
	public void deleteBoat(String[] args) {
		aux.tooFewArguments(args.length < 3);
		String idString = args[1];
		Member member = aux.getMemberById(idString, memberList);
		int i = Integer.parseInt(args[2]);
		
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
