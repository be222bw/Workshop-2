package view;

import java.util.ArrayList;

import model.Boat;
import model.Member;

/**
 * This class contains functions used for convenience. Simply put, it contains methods that are needed in several
 * places.
 * @author Björn Elmqvist
 *
 */
public class Auxiliary {
	/**
 	* Throws an exception if there are too few arguments.
 	* @param shallThrow Whether it shall be thrown or not.
 	*/
	public void tooFewArguments(boolean shallThrow) { // I throw this exception so much, I might as well automise it.
		try {
			if (shallThrow) {
				throw new Exception("Too few arguments!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
	}
		
		/**
		 * Print a specific member.
		 * @param member The member to be printed.
		 * @param isVerbose Whether to print it verbosely.
		 */
		public void printMember(Member member, boolean isVerbose) {
			System.out.println("Name: " + member.getName() + "\r\n" +
					(isVerbose ? "Personal number: " + member.getPersonalNum().toString() + "\r\n" : "") + 
					"Id: " + member.getIdString() + "\r\nNumber of boats: " + member.getNumOfBoats());
			if (isVerbose) {
				for (int n= 0; n < member.getNumOfBoats(); n++) {
					Boat boat = member.getBoat(n);
					System.out.println("\tBoat type: " + boat.getType() + " Length: " + boat.getLength() + " metres.");
				}
			}
		}
		
		/**
	 	* Iterates the member list, to find the right member. If he is not found, returns null.
	 	* @param idString The id string of the member to be returned.
	 	* @return The member with the given id, or, if he is not found, null.
	 	*/
		public Member getMemberById(String idString, ArrayList<Member> memberList) {
			int size = memberList.size();
			for (int i = 0; i < size; i++) {
				Member member = memberList.get(i);
				if (idString.equals(member.getIdString())) {
					return member;
				}
			}
			return null;
		}
	}