package Cluedo;
/**
 * This class represents the Solution to winning the game.
 * Contains the Murderer Card, Murder Room and the Murder Weapon. 
 * If A player makes a correct accusation that matches these cards
 * the player wins.
 * 
 * 
 * @author Quang Tran, Crystal Johnston
 *
 */
public class Solution{
	private static Room murderRoom = null;
	private static Weapon murderWep = null;
	private static Characters murderer = null;
	
	//Solution 
	public Solution(Room murderRoom, Weapon murderWeapon, Characters murderer){
		this.murderRoom = murderRoom;
		this.murderWep = murderWeapon;
		this.murderer = murderer;
	}

	/**
	 * Returns the Murder Room
	 * @return Returns the Murder Room
	 */
	public static Room getMurderRoom(){
		return murderRoom;
	}
	/**
	 * Returns the Murder Weapon
	 * @return Returns the Murder Weapon
	 */
	public static Weapon getMurderWeapon(){
		return murderWep;
	}
	/**
	 * Returns the murderer
	 * @return Returnst the Murderer
	 */
	public static Characters getMurderer(){
		return murderer;
	}
	
	/**
	 * Prints out the Solution. Used for testing. 
	 */
	public String toString(){
		return (String.format("murderer = %s, murder Weapon = %s, murder Room = %s.", murderer,murderWep,murderRoom));
	}
}
