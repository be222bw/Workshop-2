package view;

import java.util.ArrayList;

import model.FileWriter;
import model.Member;

public class MemberHandler {
	private ArrayList<Member> memberList;
	private FileWriter fw;
	private Auxiliary aux;
	
	/**
	 * Constructs a MemberHandler object.
	 * @param memberList The list to use.
	 * @param fw The FileWriter to use.
	 */
	public MemberHandler(ArrayList<Member> memberList, FileWriter fw) {
		this.memberList = memberList;
		this.fw = fw;
		aux = new Auxiliary();
	}
	
	/**
	 * Changes either the name or the personal number of a member. Id cannot be changed, but the number of boats
	 * is changed if one adds a boat or removes a boat, i.e. not here.
	 * @param args The arguments.
	 */
	public void changeMemberInfo(String[] args) {
		aux.tooFewArguments(args.length < 4);
		
		String idString = args[2];
		Member member = aux.getMemberById(idString, memberList);
		
		switch (args[1]) {
		case "/cn":
			member.setName(args[3]);
			break;
		case "/cpn":
			member.setPersonalNum(args[3]);
			break;
		default:
			System.err.println("Could not identify parameter \"" + args[3] + ".\"");;
			return;
		}
		fw.overwriteMemberFile(memberList);
	}
	
	/**
	 * Lists members.
	 * @param args The arguments.
	 */
	public void listMembers(String[] args) {
		for (int i = 0; i < memberList.size(); i++) {
			Member member = memberList.get(i);
			boolean isVerbose = args.length > 1 && args[1].equals("/v");
			aux.printMember(member, isVerbose);
		}
	}
	
	/**
	 * Creates a member.
	 * @param args The arguments.
	 */
	public void createMember(String args[]) {
		
		aux.tooFewArguments(args.length < 3);
		Member member = new Member(args[1], args[2], 0); // When creating member, start with no boats.
		
		fw.writeMemberData(member);
		
		aux.printMember(member, false);
	}
	
	/** Deletes a specific member.
	 * @param args The arguments.
	 */
		public void deleteMember(String[] args) {
			aux.tooFewArguments(args.length < 2);
			
			String idString = args[1];
			
			int size = memberList.size();
			for (int i = 0; i < size; i++) {
				Member member = memberList.get(i);
				if (member.getIdString().equals(idString)) {
					System.out.println("Removing member " + member.getName() + ".");
					memberList.remove(i);
					break;
				}
			}
			
			fw.overwriteMemberFile(memberList);
		}
		
		/** Views a specific member.
		 * @param args The arguments.
		 */
	public void viewMember(String[] args) {
		aux.tooFewArguments(args.length < 2);
		
		boolean isVerbose = args.length > 2 &&  args[1].equals("/v");
		String idString = (isVerbose ? args[2] : args[1]); //If /v is supplied, id is the third parameter.
		Member member = aux.getMemberById(idString, memberList);
		aux.printMember(member, isVerbose);
	}

}
