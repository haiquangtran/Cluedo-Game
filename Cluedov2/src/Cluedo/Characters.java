package Cluedo;

/**
 * This class represents a Character type of card
 * in the Deck. 
 * 
 * @author Quang Tran, Crystal Johnston
 *
 */
public class Characters implements Card{
	private String name;
	
	public Characters(String name){
		this.name = name;
	}

	/**
	 * Returns the Character name of the Card.
	 */
	public String toString(){
		return name;
	}
}
