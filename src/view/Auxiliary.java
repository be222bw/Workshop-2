package view;

import java.util.ArrayList;

import model.Boat;
import model.Member;

/**
 * This class contains functions used for convenience. Simply put, it contains methods that are needed in several
 * places.
 * @author Björn Elmqvist, UDM17.
 *
 */
public class Auxiliary {
		/**
		 * Print a specific member.
		 * @param member The member to be printed.
		 * @param isVerbose Whether to print it verbosely.
		 */
		public void printMember(Member member, boolean isVerbose) {
			System.out.println("Name: " + member.getName() + "\r\n" +
					(isVerbose ? "Personal number: " + member.getPersonalNum().toString() + "\r\n" : "") + 
					"Id: " + member.getId() + "\r\nNumber of boats: " + member.getNumOfBoats());
			if (isVerbose) {
				for (int n= 0; n < member.getNumOfBoats(); n++) {
					Boat boat = member.getBoat(n);
					System.out.println("\tBoat type: " + boat.getType() + " Length: " + boat.getLength() + " metres.");
				}
			}
			System.out.println();
		}
		
		/**
	 	* Iterates the member list, to find the right member. If he is not found, returns null.
	 	* @param idString The id string of the member to be returned.
	 	* @return The member with the given id, or, if he is not found, null.
	 	*/
		public Member getMemberById(int id, ArrayList<Member> memberList) {
			int size = memberList.size();
			for (int i = 0; i < size; i++) {
				Member member = memberList.get(i);
				if (id == member.getId()) {
					return member;
				}
			}
			return null;
		}
	}