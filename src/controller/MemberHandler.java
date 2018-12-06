package controller;

import java.util.ArrayList;
import model.FileWriter;
import model.Member;
import view.Auxiliary;

/**
 * Class that handles the member-oriented commands.
 * @author Björn Elmqvist, UDM17.
 *
 */
public class MemberHandler {
	private ArrayList<Member> memberList;
	private FileWriter fw;
	private Auxiliary aux;
	
	/**
	 * Constructs a MemberHandler object.
	 * @param memberList The list to use.
	 * @param fw The FileWriter to use.
	 */
	public MemberHandler(ArrayList<Member> memberList, String fileName) {
		this.memberList = memberList;
		this.fw = new FileWriter(fileName);
		this.aux = new Auxiliary();
	}
	
	public void changeMemberPersonalNum(int id, String personalNumber) {
		Member member = aux.getMemberById(id, memberList);
		member.setPersonalNum(personalNumber);
		fw.overwriteMemberFile(memberList);
	}
	
	public void changeMemberName(int id, String name) {
		Member member = aux.getMemberById(id, memberList);
		member.setName(name);
		fw.overwriteMemberFile(memberList);
	}
	/**
	 * Lists members.
	 * @param isVerbose Whether to list the members verbosely or not.
	 */
	public void listMembers(boolean isVerbose) {
		for (Member member : memberList) {
			aux.printMember(member, isVerbose);
			System.out.println();
		}
	}
	
	/**
	 * Creates a member.
	 * @param name The name of the member to create.
	 * @param personalNumber The personal number of the member to create.
	 */
	public void createMember(String name, String personalNumber) {
		int id = memberList.size() > 0 ? memberList.get(memberList.size() - 1).getId() + 1 : 0; // Id is id of last member + 1.
		Member member = new Member(name, personalNumber, id, 0); // When creating member, start with no boats.
		
		fw.writeMemberData(member);
		
		aux.printMember(member, false);
	}
	
	/** Deletes a specific member.
	 * @param id The id of the member to delete.
	 */
		public void deleteMember(int id) {
			int size = memberList.size();
			for (int i = 0; i < size; i++) {
				Member member = memberList.get(i);
				if (member.getId() == id) {
					System.out.println("Removing member " + member.getName() + ".");
					memberList.remove(i);
					break;
				}
			}
			
			fw.overwriteMemberFile(memberList);
		}
		
		/** Views a specific member.
		 * @param memberId The id of the member to view.
		 * @param isVerbose Whether to view the member verbosely or not.
		 */
	public void viewMember(int memberId, boolean isVerbose) {
		Member member = aux.getMemberById(memberId, memberList);
		aux.printMember(member, isVerbose);
	}

}
