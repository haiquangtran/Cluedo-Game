package Cluedo;
//import Game;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * This class represents a Player in the Game of Cluedo. 
 * A Player has a character which represents their token on the board. 
 * This class is responsible for the movement of a player, 
 * the rolling of the dice and the Suggestions and Accusations
 * that a player can make. This class also contains a list of
 * Cards that are in the players hand. 
 * 
 * @author Quang Tran, Crystal Johnston
 *
 */
public class Player {
	//For movement
	private int playerNum = 0;
	private String currentRoom = null;
	private Characters character;
	private String dir = null;
	private boolean inRoom = false;
	private boolean startTurn = false;
	private boolean endTurn = false;
	//Player Name
	private String name; 
	//For Suggestions
	private Weapon suggestedWeapon = null;
	private Characters suggestedMurderer = null;
	private Room accusationRoom = null;
	//Cards they have in their Hand
	private ArrayList<Card> hand = new ArrayList<Card>();
	private boolean cornerRoom = false;
	private boolean cantEnter;

	public Player(int playerNumber, Characters character, String name){
		this.playerNum = playerNumber;
		this.character = character;
		this.name = name;
	}

	/**
	 * Returns the Player number.
	 * @return Player Number
	 */
	public int getPlayerNumber(){
		return playerNum;
	}

	/**
	 * Returns the Character name of the Character the Player is using.
	 * @return Character Name 
	 */
	public String getCharName(){
		return character.toString();
	}

	/**
	 * Returns the Cards in the Players hand.
	 * @return	Cards in the Players Hand.
	 */
	public ArrayList<Card> getHand(){
		return hand;	
	}

	/**
	 * MOVEMENT: 
	 * Determines what the user typed for the movement of the Character
	 * w,a,s,d. 
	 */
	public void input(){
		System.out.println(Game.getCurrent().character.toString()+" type w,a,s or d then enter to move");
		//Figures out the direction that the user wants to move, North, West, East or South. 
		if(dir==null){		
			Scanner scan = new Scanner(System.in);
			while (scan != null){
				String input = scan.next();
				if (input.equals("w")){dir = "north"; break;}
				else if (input.equals("a")){dir = "west";break;}
				else if (input.equals("s")){dir = "south";break;}
				else if (input.equals("d")){dir = "east";break;}
				else System.out.println("Invalid input: please type w,a,s or d");}
		}
	}

	/**
	 *  Determines the outcome of the dice roll (1-6),
	 *  which is how far the player can move on the board.
	 */
	int roll;
	public void roll(){
		startTurn=true;
		endTurn =false;
		//Rolls the dice (1-6)
		roll= (int)(Math.random()*6)+1;
		if(!Game.textBased)CanvasPanel.getText().append(Game.getCurrent().getCharName()+" rolled a " +roll+"\n");
		else{ 
			System.out.println(Game.getCurrent().character.toString()+" has rolled a "+roll+"!");
			//Allows how far the player can move, depending on their dice roll
			for(int i=roll; i>0;i--){
				if(!endTurn){
					if(!cornerRoom){input();}
					move();
				}	
			}
		}
	}

	//Move method for GUI version of game
	public void moveGUI(int startX, int startY, int endX, int endY){		
		if(Board.board[endX][endY]<3 && Board.board[endX][endY]!=1){
			if(Board.board[endX][endY]==2 && !inRoom){
				middleOfRoom(endX,endY,getCounter());
				if(!cantEnter){
					Board.board[startX][startY] = 0;}
				else{CanvasPanel.getText().append("Can't enter. Someone in room\n");
				BoardPanel.setMoveCount(BoardPanel.getMoveCount() -Math.max(Math.abs((endX - startX)), Math.abs((endY - startY))));}
			}
			else{
				Board.board[endX][endY] = getCounter();
				if(Game.getCurrent().comeFromRoom()){
					currentDoor();
					Board.board[door[0]][door[1]] = 2;
					Game.getCurrent().currentRoom = null;
					Game.getCurrent().setComeFromRoom(false);
				}
				else Board.board[startX][startY] = 0;
			}		}			

	}

	/**
	 * Moves the Character's player on the board in the specified direction.
	 * 
	 * @param input	- The Direction to move (N,S,E,W)
	 * @param i		- row index in the array 
	 * @param j		- col index in the array
	 * @param counter - The number that the Player is represented by on the board.
	 */
	public void move(String input,int i,int j,int counter){
		if(input!=null){
			try{
				if(dir.equals("north")){				//move North
					if(Board.board[i-1][j]<3){
						if(Board.board[i-1][j]==2 && !inRoom){
							Board.board[i][j] = 0;
							middleOfRoom(i-1,j,counter);
							if(!cantEnter){
								Board.board[i][j] = 0;}
							else throw new IndexOutOfBoundsException();

						}
						else{
							Board.board[i-1][j] = counter;
							if(inRoom){ 				//Player In room
								Board.board[i][j] = 2;
								inRoom=false;}
							else Board.board[i][j] = 0;
						}					
					}
					else {System.out.println("You've hit a player/wall, choose another direction");	//Hit Wall 
					dir =null;
					input();
					move(dir,i,j,counter);
					}
					dir=null;
				}

				else if(dir.equals("west")){			//Move West
					if(Board.board[i][j-1]<3){
						if(Board.board[i][j-1]==2&& !inRoom){
							Board.board[i][j] = 0;
							middleOfRoom(i,j-1,counter);
							if(!cantEnter){
								Board.board[i][j] = 0;}
							else throw new IndexOutOfBoundsException();
						}else{
							Board.board[i][j-1] = counter;
							if(inRoom){					//Player In Room
								Board.board[i][j] = 2;
								inRoom=false;
							}
							else Board.board[i][j] = 0;}

					}
					else {System.out.println("You've hit a player/wall, choose another direction"); //Hit Wall 
					dir =null;
					input();
					move(dir,i,j,counter);}

					dir=null;}

				else if(dir.equals("south")){					//Move South
					if(Board.board[i+1][j]<3){					//wall
						if(Board.board[i+1][j]==2&& !inRoom){	//door
							Board.board[i][j] = 0;
							middleOfRoom(i+1,j,counter);
							if(!cantEnter){
								Board.board[i][j] = 0;}
							else throw new IndexOutOfBoundsException();
						}else{
							Board.board[i+1][j] = counter;
							if(inRoom){ 						//In Room
								Board.board[i][j] = 2;
								inRoom=false;}
							else Board.board[i][j] = 0;}

					}
					else{ System.out.println("You've hit a player/wall, choose another direction");	//Hit Wall
					dir =null;
					input();
					move(dir,i,j,counter);
					}

					dir=null;}
				else if(dir.equals("east")){
					if(Board.board[i][j+1]<3){

						if(Board.board[i][j+1]==2&& !inRoom){
							Board.board[i][j] = 0;
							middleOfRoom(i,j+1,counter);
							if(!cantEnter){
								Board.board[i][j] = 0;}
							else throw new IndexOutOfBoundsException();
						}
						else{
							Board.board[i][j+1] = counter;
							if(inRoom){Board.board[i][j] = 2;
							inRoom=false;}
							else Board.board[i][j] = 0;
						}
					}
					else {System.out.println("You've hit a player/wall, choose another direction");
					dir =null;
					input();
					move(dir,i,j,counter);
					}

					dir=null;}
				if(!inRoom) {Board b = new Board(false);}			//REDRAW BOARD WITH UPDATED POSITIONS OF PLAYERS
			}catch(IndexOutOfBoundsException e){
				System.out.println("Invalid move: please choose another direction");
				dir =null;
				input();
				move(dir,i,j,counter);
			}
		}
	}

	/**
	 * If a player is in a room for more than a turn already, then the player
	 * has the choice to leave the room or go through secret passage
	 * into the opposite corner room.
	 * 
	 * @param i			- Row index in Array
	 * @param j			- Col index in Array
	 * @param counter	- The number in the 2d Array that represents the current Player
	 */
	public void roomPrompt(int i, int j, int counter){
		System.out.println("You have the choice to leave the room or go over to the opposite corner room!\n" +
				"Which do you want to do? (corridor/other room)");
		Scanner scan = new Scanner(System.in);
		if(scan.hasNext()){
			String input = scan.next();
			//Exit Room
			if(input.equalsIgnoreCase("corridor")){
				exitRoom(i,j,counter);
			}
			//Secret Passage
			else if(input.equalsIgnoreCase("other")){
				cornerRoom(i, j, counter);
			}
		}

	}

	/**
	 * Determines if the player is in a corner room,
	 * which gives special options (Secret Passage).
	 * @param i			- Row index in Array
	 * @param j			- Col index in Array
	 * @param counter	- The number in the 2d Array that represents the current Player
	 */
	public void cornerRoom(int i, int j,int counter){
		//Corner Rooms
		if(Game.getCurrent().currentRoom.equals("Guest House")){
			if(Board.board[3][4]==1){
				Game.getCurrent().currentRoom = "Spa";
				Board.board[3][4]=counter;}
			else cantEnter=true;
		}else if(Game.getCurrent().currentRoom.equals("Spa")){
			if(Board.board[23][23]==1){
				Game.getCurrent().currentRoom = "Guest House";
				Board.board[23][23]=counter;}
			else cantEnter=true;
		}else if(Game.getCurrent().currentRoom.equals("Kitchen")){
			if(Board.board[5][23]==1){
				Game.getCurrent().currentRoom = "Observatory";
				Board.board[5][23]=counter;}
			else cantEnter=true;
		}else if(Game.getCurrent().currentRoom.equals("Observatory")){
			if(Board.board[23][4]==1){
				Game.getCurrent().currentRoom = "Kitchen";
				Board.board[23][4]=counter;}
			else cantEnter=true;
		}
		if(!cantEnter){
			Board.board[i][j] = 1;
			Board b = new Board(false);		//redraw Board
			if (cornerRoom){
				if(!Game.textBased)GraphicalUserInterface.suggestionOrAccuse(false);
				else suggestion();//Suggestion FOR TEXT BASED
			}

			startTurn=false;
			endTurn = true;}
		else{System.out.println("You have to leave the room, someone is in the other room");
		exitRoom(i,j, counter);
		}
	}

	/**
	 * If a player is within a room and their turn is over, they need 
	 * to move from the door rather than within the room.
	 * In other words, if a player has been in a room more than one turn he 
	 * is teleported to the door where he has to move when it's his turn.
	 * 
	 * @param i			- Row index in Array
	 * @param j			- Col index in Array
	 * @param counter	- The number in the 2d Array that represents the current Player
	 */
	public void exitRoom(int i,int j,int counter){
		BoardPanel.setMoveCount(0);
		if(Game.textBased){if(cornerRoom) input();}
		int[] coords = new int[2];
		cornerRoom =false;
		if(Game.getCurrent().currentRoom.equals("Spa")){
			Board.board[5][5]=counter;		//move player
			coords[0] =5; coords[1]=5;
		}else if(Game.getCurrent().currentRoom.equals("Living Room")){
			Board.board[8][16]=counter;
			coords[0] =8; coords[1]=16;
		}else if(Game.getCurrent().currentRoom.equals("Theatre")){
			Board.board[7][10]=counter;
			coords[0] =7; coords[1]=10;
		}else if(Game.getCurrent().currentRoom.equals("Observatory")){
			Board.board[8][22]=counter;
			coords[0] =8; coords[1]=22;
		}else if(Game.getCurrent().currentRoom.equals("Patio")){
			Board.board[10][4]=counter;
			coords[0] =10; coords[1]=4;
		}else if(Game.getCurrent().currentRoom.equals("Hall")){
			Board.board[11][22]=counter;
			coords[0] =11; coords[1]=22;
		}else if(Game.getCurrent().currentRoom.equals("Dining Room")){
			Board.board[20][12]=counter;
			coords[0] =20; coords[1]=12;
		}else if(Game.getCurrent().currentRoom.equals("Guest House")){
			Board.board[21][20]=counter;
			coords[0] =21; coords[1]=20;
		}else if(Game.getCurrent().currentRoom.equals("Kitchen")){
			Board.board[22][6]=counter;
			coords[0] =22; coords[1]=6;
		}

		Board.board[i][j]=1;
		if(Game.textBased)Game.getCurrent().currentRoom = null;
		else inRoom = false;
		Board b = new Board(false);
		Game.getCurrent().comeFromRoom = true;
		if(Game.textBased)move(dir,coords[0], coords[1],counter);
		inRoom=false;
	}
	private boolean comeFromRoom = false;
	/**
	 * When Player moves to a door, they are moved to the middle of the room
	 * No other players can enter the room. 
	 * Secret doors between corner rooms are now available. 
	 * @param i			- Row index in Array
	 * @param j			- Col index in Array
	 * @param counter	- The number in the 2d Array that represents the current Player
	 */
	public void middleOfRoom(int i, int j,int counter) {
		cantEnter = false;
		//spa
		if(i==5 && j==5){
			if(Board.board[3][4]==1){
				Board.board[3][4]=counter;//player moving to middle of room
				Game.getCurrent().currentRoom = "Spa";
				cornerRoom =true;}
			else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//living room
		else if(i==8 && j==16){
			if(Board.board[4][16]==1){
				Board.board[4][16]=counter;
				Game.getCurrent().currentRoom = "Living Room";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//theatre
		else if(i==7 && j==10){
			if(Board.board[4][10]==1){
				Board.board[4][10]=counter;
				Game.getCurrent().currentRoom = "Theatre";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//observatory
		else if(i==8 && j==22){
			if(Board.board[5][23]==1){
				Board.board[5][23]=counter;
				Game.getCurrent().currentRoom = "Observatory";
				cornerRoom =true;
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//patio
		else if(i==10 && j==4){
			if(Board.board[14][4]==1){
				Board.board[14][4]=counter;
				Game.getCurrent().currentRoom = "Patio";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//pool
		else if(i==11 && j==14){
			if(Board.board[12][14]==1){
				Board.board[12][14]=counter;
				Game.getCurrent().currentRoom = "Pool";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//hall
		else if(i==11 && j==22){
			if(Board.board[12][22]==1){
				Board.board[12][22]=counter;
				Game.getCurrent().currentRoom = "Hall";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//dining room
		else if(i==20 && j==12){
			if(Board.board[22][12]==1){
				Board.board[22][12]=counter;
				Game.getCurrent().currentRoom = "Dining Room";
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//guest house
		else if(i==21 && j==20){
			if(Board.board[23][23]==1){
				Board.board[23][23]=counter;
				Game.getCurrent().currentRoom = "Guest House";
				cornerRoom =true;
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}
		}
		//kitchen
		else if(i==22 && j==6){
			if(Board.board[23][4]==1){
				Board.board[23][4]=counter;
				Game.getCurrent().currentRoom = "Kitchen";
				cornerRoom =true;
			}else{ System.out.println("Can't go inside, some one is in that room");
			cantEnter = true;
			}

		} else {
			//reset
			Game.getCurrent().currentRoom = null;	
		}
		//Make Suggestion
		if(!cantEnter){
			inRoom=true;
			Board.board[i][j]=2;	//door
			Board b = new Board(false);	//redraws
			if(!Game.getCurrent().currentRoom.equals("Pool")){
				if(!Game.textBased)GraphicalUserInterface.suggestionOrAccuse(false);
				suggestion();
			}else{
				if(!Game.textBased)GraphicalUserInterface.suggestionOrAccuse(true);
				accusation(12,14);}
			startTurn=false;
			endTurn = true;}
	}

	/**
	 * 	When a player enters a room he/she can announce a suggestion which consists of that room,
	 *	and a character and weapon (if necessary, the character and weapon pieces are moved into the room).
	 *	By making the announcement, the player is hypothesising that these solve the murder. 
	 *	When an announcement is made, each player responds in a clock-wise fashion until either: the
	 *	suggestion is refuted; or, all players have indicated they cannot refute the suggestion. A suggestion
	 *	is refuted by a player if that player contains a matching card. In other words, the player contains a
	 *	card and, hence, that card cannot be in the solution envelope.
	 */
	public void suggestion(){
		if(Game.textBased){ readSuggestionsOrAccusations(true); }
		int count = Game.getCurrent().getPlayerNumber()-1;		//Current Suggestor
		for (int i = 0; i < Game.getPlayers().size(); i++){		//Go through a whole round
			if (count == Game.getPlayers().size()){				
				count = 0;										//Reset
			}
			//If other players have the suggested cards, they reveal.
			if (revealSuggestionCards(i)){						//SHOW ONLY ONE CARD
				suggestedMurderer = null;
				suggestedWeapon = null;
				accusationRoom = null;
				break;
			} 						
			count++;
		}

		if (Game.textBased){
			System.out.println("Suggestion Ended.");
		}
	}

	/**
	 * Goes through each player in a clockwise rotation and checks if they have 
	 * any of the Suggested Cards, if they do... the card will be revealed
	 * and the suggestion will be over, otherwise if no player has the suggested
	 * cards the suggestion will end. 
	 * Returns True if the player has any of the suggested cards, otherwise false.
	 * @param i - Player index in the array 
	 * @return	Returns true, if player has any of the suggested cards, otherwise false.
	 */
	private boolean revealSuggestionCards(int i){
		try {
			for (Card cards: Game.getPlayers().get(i).getHand()){
				if(!Game.textBased){
					ImageIcon image = null;
					if (cards.toString().equalsIgnoreCase(suggestedWeapon.toString())){
						if (suggestedWeapon.toString().equals("Rope")){image = new ImageIcon(getClass().getResource("images/ropeCard.png"));}
						else if (suggestedWeapon.toString().equals("Axe")){image = new ImageIcon(getClass().getResource("images/axeCard.png"));}
						else if (suggestedWeapon.toString().equals("Poison")){image = new ImageIcon(getClass().getResource("images/poisonCard.png"));}
						else if (suggestedWeapon.toString().equals("Trophy")){image = new ImageIcon(getClass().getResource("images/trophyCard.png"));}
						else if (suggestedWeapon.toString().equals("Knife")){image = new ImageIcon(getClass().getResource("images/knifeCard.png"));}
						else if (suggestedWeapon.toString().equals("Baseball Bat")){image = new ImageIcon(getClass().getResource( "images/baseballCard.png"));}
						else if (suggestedWeapon.toString().equals("Candlestick")){image = new ImageIcon(getClass().getResource("images/candleCard.png"));}
						else if (suggestedWeapon.toString().equals("Pistol")){image = new ImageIcon(getClass().getResource("images/gunCard.png"));}
						else if (suggestedWeapon.toString().equals("Dumbbell")){image = new ImageIcon(getClass().getResource("images/dumbbellCard.png"));}
						JOptionPane.showMessageDialog(GraphicalUserInterface.getFrame(), 
								new JLabel((Game.getPlayers().get(i).getCharName() 
										+ " has the Card: " + cards.toString())),"Reveal Cards", 1, image);
						resetSuggestions();
						return true;									//Only shows one card, Cant show more							
					} else if (cards.toString().equalsIgnoreCase(suggestedMurderer.toString())){
						if (suggestedMurderer.toString().equals("Kasandra Scarlett")){image = new ImageIcon(getClass().getResource("images/scarCard.png"));}
						else if (suggestedMurderer.toString().equals("Jack Mustard")){image = new ImageIcon(getClass().getResource("images/musCard.png"));}
						else if (suggestedMurderer.toString().equals("Diane White")){image = new ImageIcon(getClass().getResource("images/whiCard.png"));}
						else if (suggestedMurderer.toString().equals("Jacob Green")){image = new ImageIcon(getClass().getResource("images/greCard.png"));}
						else if (suggestedMurderer.toString().equals("Eleanor Peacock")){image = new ImageIcon(getClass().getResource("images/peaCard.png"));}
						else if (suggestedMurderer.toString().equals("Victor Plum")){image = new ImageIcon(getClass().getResource( "images/pluCard.png"));}

						JOptionPane.showMessageDialog(GraphicalUserInterface.getFrame(), 
								new JLabel((Game.getPlayers().get(i).getCharName() 
										+ " has the Card: " + cards.toString())),"Reveal Cards", 1, image);
						resetSuggestions();
						return true;
					}else if (cards.toString().equalsIgnoreCase(Game.getCurrent().currentRoom)){
						if (Game.getCurrent().currentRoom.equals("Spa")){image = new ImageIcon(getClass().getResource("images/spa.png"));}
						else if (Game.getCurrent().currentRoom.equals("Theatre")){image = new ImageIcon(getClass().getResource("images/theatre.png"));}
						else if (Game.getCurrent().currentRoom.equals("Living Room")){image = new ImageIcon(getClass().getResource("images/lounge.png"));}
						else if (Game.getCurrent().currentRoom.equals("Observatory")){image = new ImageIcon(getClass().getResource("images/observatory.png"));}
						else if (Game.getCurrent().currentRoom.equals("Hall")){image = new ImageIcon(getClass().getResource("images/hall.png"));}
						else if (Game.getCurrent().currentRoom.equals("Guest House")){image = new ImageIcon(getClass().getResource( "images/guest.png"));}
						else if (Game.getCurrent().currentRoom.equals("Dining Room")){image = new ImageIcon(getClass().getResource("images/dining.png"));}
						else if (Game.getCurrent().currentRoom.equals("Kitchen")){image = new ImageIcon(getClass().getResource("images/kitchen.png"));}
						else if (Game.getCurrent().currentRoom.equals("Patio")){image = new ImageIcon(getClass().getResource("images/patio.png"));}
						else if (Game.getCurrent().currentRoom.equals("Pool")){image = new ImageIcon(getClass().getResource("images/pool.png"));}

						JOptionPane.showMessageDialog(GraphicalUserInterface.getFrame(), 
								new JLabel((Game.getPlayers().get(i).getCharName() 
										+ " has the Card: " + cards.toString())),"Reveal Cards", 1, image);
						resetSuggestions();
						return true;
					} 

				}
				else {
					if (cards.toString().equalsIgnoreCase(suggestedWeapon.toString())){
						System.out.println(Game.getPlayers().get(i).getCharName() + " has the Card: " + cards.toString());
						return true;									//Only shows one card, Cant show more							
					} else if (cards.toString().equalsIgnoreCase(suggestedMurderer.toString())){
						System.out.println(Game.getPlayers().get(i).getCharName() + " has the Card: " + cards.toString());
						return true;
					}	else if (cards.toString().equalsIgnoreCase(currentRoom)){
						System.out.println(Game.getPlayers().get(i).getCharName() + " has the Card: " + cards.toString());
						return true;
					} 
				}
			}	
		} catch (NullPointerException e){}
		CanvasPanel.getText().append(Game.getPlayers().get(i).getCharName()+ " does not have any of the Suggested Cards. \n");
		if (Game.textBased){
			System.out.println(Game.getPlayers().get(i).getCharName()+ " does not have any of the Suggested Cards. ");}
		return false;
	}

	private void resetSuggestions(){
		suggestedWeapon = null;
		suggestedMurderer = null;
		accusationRoom = null;
	}

	/**
	 * Shows all the possible Weapons that a user can suggest. 
	 */
	private void possibleWeapons() {
		for (int i = 0; i < Deck.weps.length; i++){
			if (i == 0){
				System.out.print("{ " + Deck.weps[i] + ", ");
			} else if (i == Deck.weps.length-1){
				System.out.print( Deck.weps[i]+" }");
			} else {
				System.out.print(Deck.weps[i] + ", ");
			}
		}
		System.out.println();
	}

	/**
	 * Shows all the possible characters that a user can suggest. 
	 */
	private void possibleCharacters() {
		for (int i = 0; i < Deck.people.length; i++){
			if (i == 0){
				System.out.print("{ " + Deck.people[i] + ", ");
			} else if (i == Deck.people.length-1){
				System.out.print( Deck.people[i]+" }");
			} else {
				System.out.print(Deck.people[i] + ", ");
			}
		}
		System.out.println();
	}


	public void readSuggestionsOrAccusationsGUI(boolean suggestion, String name, String weapon, String room){
		suggestedWeapon = new Weapon(weapon);
		suggestedMurderer = new Characters(name);
		if (suggestion){
			CanvasPanel.getText().append("Suggested "+ Game.getCurrent().currentRoom+ " as the Murder Room.\n");
			CanvasPanel.getText().append("Suggested "+ name +  " as the Murderer.\n");
			CanvasPanel.getText().append("Suggested "+ weapon +  " as the Murder Weapon.\n");
			return;											//EXIT METHOD if suggestion
		} 
		//OTHERWISE ACCUSATION
		accusationRoom = new Room(room);

	}

	/**
	 * Used for Suggestion or Accusation. 
	 * Shows all the possible Weapons, Characters and the current Room that the player is in for a suggestion.
	 * Shows all the possible Weapons, Characters and Rooms that the player can accuse. 
	 * 
	 * boolean suggestion - True, means that it is a suggestion, False, means it is an accusation
	 */
	public void readSuggestionsOrAccusations(boolean suggestion){
		Scanner scan = new Scanner(System.in);					//Input
		if (suggestion){
			System.out.printf("Player %d: %s please make a suggestion:\n", this.playerNum,this.getCharName());
			System.out.println("Please make a suggestion for Murder Weapon:");
		} else {
			System.out.printf("Player %d: %s please make a Accusation:\n", this.playerNum,this.getCharName());
			System.out.println("Please make a Accusation for Murder Weapon:");
		}
		possibleWeapons();
		//WEAPON INPUT
		while (scan != null){
			String next = scan.nextLine();
			if (next.equalsIgnoreCase("Rope") || next.equalsIgnoreCase("Candlestick") ||
					next.equalsIgnoreCase("Knife") || next.equalsIgnoreCase("Pistol") || 
					next.equalsIgnoreCase("Baseball Bat") || next.equalsIgnoreCase("Dumbbell") ||
					next.equalsIgnoreCase("Trophy") || next.equalsIgnoreCase("Poison") ||
					next.equalsIgnoreCase("Axe")){
				suggestedWeapon = new Weapon(next);
				break;										//Get out of loop
			}
			System.out.println("Invalid Weapon. Please type one of the below:");
			possibleWeapons();
		}
		if (suggestion){
			System.out.println("Please make a Suggestion for Murderer:");
		} else {
			System.out.println("Please make a Accusation for Murderer:");
		}
		//CHARACTER INPUT
		possibleCharacters();
		while (scan != null){
			String next = scan.nextLine();
			if (next.equalsIgnoreCase("Kasandra Scarlett") || next.equalsIgnoreCase("Jack Mustard") ||
					next.equalsIgnoreCase("Diane White") || next.equalsIgnoreCase("Jacob Green") || 
					next.equalsIgnoreCase("Eleanor Peacock") || next.equalsIgnoreCase("Victor Plum")){
				suggestedMurderer = new Characters(next);
				break;										//Get out of loop
			}
			System.out.println("Invalid Character. Please type one of the below:");
			possibleCharacters();
		}
		if (suggestion){
			System.out.printf("Suggested %s as the Murder Room.\n", Game.getCurrent().currentRoom);
			System.out.printf("Suggested %s as the Murderer.\n", suggestedMurderer);
			System.out.printf("Suggested %s as the Murder Weapon.\n", suggestedWeapon);
			suggestedMurderer = null;
			suggestedWeapon =  null;
			return;											//EXIT METHOD if suggestion
		} 
		//OTHERWISE ACCUSATION
		//ROOM INPUT
		System.out.println("Please make a Accusation for Murder Room:");
		possibleRooms();
		while (scan != null){
			String next = scan.nextLine();
			if (next.equalsIgnoreCase("Spa") || next.equalsIgnoreCase("Theatre") ||
					next.equalsIgnoreCase("Living Room") || next.equalsIgnoreCase("Observatory") || 
					next.equalsIgnoreCase("Hall") || next.equalsIgnoreCase("Guest House") || 
					next.equalsIgnoreCase("Dining Room") || next.equalsIgnoreCase("Kitchen") || 
					next.equalsIgnoreCase("Patio") || next.equalsIgnoreCase("Pool")){
				accusationRoom = new Room(next);
				break;										//Get out of loop
			}
			System.out.println("Invalid Room. Please type one of the below:");
			possibleRooms();
		}

		suggestedMurderer = null;
		suggestedWeapon =  null;
		accusationRoom = null;
	}

	/**
	 * Shows all the possible Rooms that the player can choose from. (Accusation)
	 */
	private void possibleRooms() {
		for (int i = 0; i < Deck.room.length; i++){
			if (i == 0){
				System.out.print("{ " + Deck.room[i] + ", ");
			} else if (i == Deck.room.length-1){
				System.out.print( Deck.room[i]+" }");
			} else {
				System.out.print(Deck.room[i] + ", ");
			}
		}
		System.out.println();
	}

	/**
	 *	Should move to the swimming pool, and make an accusation consisting of a weapon, character and
	 *	room. The accusation is then checked by the player against the solution. If it is correct the player
	 *	wins, otherwise the player is eliminated from the game.
	 * @param i	- row index of the 2d array
	 * @param j	- col index of the 2d array
	 */
	public void accusation(int i, int j){
		try{
			if (!Game.getCurrent().currentRoom.equals("Pool")){		//Can only accuse in pool room
				return; 
			}
			if(Game.textBased){
				readSuggestionsOrAccusations(false);	//Accuse
				System.out.println("Player "+this.playerNum+" :"+this.getCharName()+" :\n");
				System.out.println("Accusation "+accusationRoom+" as the Murder Room.\n");
				System.out.println("Accusation "+suggestedMurderer+" as the Murderer.\n");
				System.out.println("Accusation "+suggestedWeapon+" as the Murder Weapon.\n");
			}else{
				CanvasPanel.getText().append("Player "+this.playerNum+" :"+this.getCharName()+" :\n");
				CanvasPanel.getText().append("Accusation "+accusationRoom+" as the Murder Room.\n");
				CanvasPanel.getText().append("Accusation "+suggestedMurderer+" as the Murderer.\n");
				CanvasPanel.getText().append("Accusation "+suggestedWeapon+" as the Murder Weapon.\n");
			}
			//WINNER
			if (suggestedMurderer.toString().equalsIgnoreCase(Solution.getMurderer().toString())
					&& suggestedWeapon.toString().equalsIgnoreCase(Solution.getMurderWeapon().toString())
					&& accusationRoom.toString().equalsIgnoreCase(Solution.getMurderRoom().toString())){
				JOptionPane.showMessageDialog(GraphicalUserInterface.getFrame(), 
						new JLabel((String.format("CONGRATULATIONS! %s: %s HAS WON THE GAME!!!", Game.getCurrent().getName().toString(), Game.getCurrent().getCharName().toString()))));
				CanvasPanel.getText().append("CONGRATULATIONS!!! PLAYER "+this.playerNum+" : "+this.getCharName()+" HAS WON THE GAME!!!\n");
				Game.gameWon = true;
			} else {
				//LOSER
				JOptionPane.showMessageDialog(GraphicalUserInterface.getFrame(), 
						new JLabel((String.format("%s: %s HAS LOST THE GAME!!!", Game.getCurrent().getName().toString(), Game.getCurrent().getCharName().toString()))));
				//SHOULD REMOVE IMAGE AS WELL
				if(!Game.textBased){
					CanvasPanel.getText().append(String.format("%s: %s HAS LOST THE GAME!!!", Game.getCurrent().getName().toString(), Game.getCurrent().getCharName().toString()));

				}
				//Remove from players list
				Game.getPlayers().remove(Game.getCurrent());
				Board.board[i][j] = 1;
				//Redraw Board
				Board b = new Board(false);
				GraphicalUserInterface.getFrame().repaint();
			}
			accusationRoom = null;
			suggestedMurderer = null;
			suggestedWeapon = null;
		} catch (NullPointerException e){}
	}


	/**
	 * Returns the Players Name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Moves the player, depending on the character that the player is. 
	 * Matches current player name and current token spot
	 */
	public  void move(){
		for (int i = 0; i < Board.board.length; i++){
			for(int j = 0; j < Board.boardSize; j++){
				if(Game.getCurrent().character.toString().equals("Kasandra Scarlett") && Board.board[i][j] == 4){
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 4);
						else exitRoom(i,j,4);
					}
					else move(dir,i,j,4);}
				else if(Game.getCurrent().character.toString().equals("Jack Mustard") && Board.board[i][j] == 5){
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 5);
						else exitRoom(i,j,5);
					}
					else move(dir,i,j,5);}
				else if(Game.getCurrent().character.toString().equals("Diane White") && Board.board[i][j] == 6) {
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 6);
						else exitRoom(i,j,6);
					}
					else move(dir,i,j,6);}
				else if(Game.getCurrent().character.toString().equals("Jacob Green") && Board.board[i][j] == 7) {
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 7);
						else exitRoom(i,j,7);
					}
					else move(dir,i,j,7);}
				else if(Game.getCurrent().character.toString().equals("Eleanor Peacock") && Board.board[i][j] == 8){ 
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 8);
						else exitRoom(i,j,8);
					}
					else move(dir,i,j,8);}
				else if(Game.getCurrent().character.toString().equals("Victor Plum") && Board.board[i][j] == 9) {
					if(inRoom && startTurn){
						if(cornerRoom) roomPrompt(i,j, 9);
						else exitRoom(i,j,9);
					}
					else move(dir,i,j,9);}
			}}
	}

	public int getCounter(){
		if(Game.getCurrent().character.toString().equals("Kasandra Scarlett")) return 4;
		else if(Game.getCurrent().character.toString().equals("Jack Mustard")) return 5;
		else if(Game.getCurrent().character.toString().equals("Diane White")) return 6;
		else if(Game.getCurrent().character.toString().equals("Jacob Green")) return 7;
		else if(Game.getCurrent().character.toString().equals("Eleanor Peacock")) return 8;
		else if(Game.getCurrent().character.toString().equals("Victor Plum")) return 9;
		else return 0;
	}
	public int getRoll(){
		return roll;
	}
	public void setRoll(int r){
		roll = r;
	}
	public boolean getInRoom(){
		return inRoom;
	}
	public boolean getCornerRoom(){
		return cornerRoom;
	}
	public boolean getStartTurn(){
		return startTurn;
	}
	public boolean getEndTurn(){
		return endTurn;
	}

	public String getCurrentRoom(){
		return currentRoom;
	}
	public boolean comeFromRoom(){
		return comeFromRoom;
	}
	public void setComeFromRoom(boolean c){
		comeFromRoom = c;
	}

	private int[] door = new int[2];
	public int[] currentDoor(){
		if(Game.getCurrent().currentRoom.equals("Spa")){
			door[0] = 5; door[1] = 5;
		}else if(Game.getCurrent().currentRoom.equals("Living Room")){
			door[0] = 8; door[1] = 16;
		}else if(Game.getCurrent().currentRoom.equals("Theatre")){
			door[0] = 7; door[1] = 10;
		}else if(Game.getCurrent().currentRoom.equals("Observatory")){
			door[0] = 8; door[1] = 22;
		}else if(Game.getCurrent().currentRoom.equals("Patio")){
			door[0] = 10; door[1] = 4;
		}else if(Game.getCurrent().currentRoom.equals("Hall")){
			door[0] = 11; door[1] = 22;
		}else if(Game.getCurrent().currentRoom.equals("Dining Room")){
			door[0] = 20; door[1] = 12;
		}else if(Game.getCurrent().currentRoom.equals("Guest House")){
			door[0] = 21; door[1] = 20;
		}else if(Game.getCurrent().currentRoom.equals("Kitchen")){
			door[0] = 22; door[1] = 6;
		}
		return door;
	}


}
