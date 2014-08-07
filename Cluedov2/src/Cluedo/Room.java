package Cluedo;
/**
 * This class represents a Room type of card
 * in the Deck. 
 * 
 * @author Quang Tran, Crystal Johnston
 *
 */
public class Room implements Card{
	private String name;

	public Room(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the Room 
	 */
	public String toString(){
		return name;
	}
	
}
