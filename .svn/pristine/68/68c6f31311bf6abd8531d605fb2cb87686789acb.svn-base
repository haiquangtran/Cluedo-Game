package Cluedo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	private static ArrayList<ImageIcon> weaponImages = new ArrayList<ImageIcon>();
	private static JPanel centre = new JPanel();
	private boolean rolled = false;
	private boolean canMove = true;
	private static int moveCount = 0;
	private static JLabel[][] board;
	
	public BoardPanel(){
		setUpWeaponImages();
		centre.setPreferredSize(new Dimension(400,400));
		this.add(centre,BorderLayout.CENTER);
	}
	
	public void setUpWeaponImages(){
		//Add all images to the array
		weaponImages.add(getImage("images/axe.png"));
		weaponImages.add(getImage("images/candlestick.png"));
		weaponImages.add(getImage("images/rope.png"));
		weaponImages.add(getImage("images/baseball.png"));
		weaponImages.add(getImage("images/poison.png"));
		weaponImages.add(getImage("images/knife.png"));
		weaponImages.add(getImage("images/pistol.png"));
		weaponImages.add(getImage("images/dumbbell.png"));
		weaponImages.add(getImage("images/trophy.png"));
		//RANDOMIZES THE WEAPONS
		Collections.shuffle(weaponImages);
	}

	public String getWeaponToolTip(int index){
		if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/axe.png").toString())){
			return "Axe";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/candlestick.png").toString())){
			return "Candlestick";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/rope.png").toString())){
			return "Rope";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/baseball.png").toString())){
			return "Baseball Bat";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/poison.png").toString())){
			return "Poison";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/knife.png").toString())){
			return "Knife";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/pistol.png").toString())){
			return "Pistol";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/dumbbell.png").toString())){
			return "Dumbbell";
		} else if (weaponImages.get(index).toString().equalsIgnoreCase(getClass().getResource("images/trophy.png").toString())){
			return "Trophy";
		}
		else return null;
	}
	/**
	 * Adds icons and tool tips to all the JLabels that make up the board. Each icon depends on the number in the
	 * Board.board array. Also allows each label to be clicked on
	 */
	public void redraw(){
		centre.removeAll();
		board= new JLabel[Board.board.length][Board.boardSize];
		GridLayout grid = new GridLayout(Board.board.length,Board.boardSize,1,1);
		centre.setLayout(grid);
		for(int i =0; i<Board.board.length; i++){
			for(int j=0;j<Board.boardSize;j++){
				board[i][j] = new JLabel();
				centre.add(board[i][j]);
				//Hover for doors
				if((i>0 && i<8 && j>14 && j<19)||(i == 8 && j==16)){board[i][j].setToolTipText("Living Room");}
				else if((i>0 && i<7 && j>0 && j<5) || (i == 5 && j==5)){board[i][j].setToolTipText("Spa");}
				else if((i>0 && i<7 && j>8 && j<12)||(i == 7 && j==10)){board[i][j].setToolTipText("Theatre");}
				else if((i>0 && i<9 && j==23)||(i == 8 && j==22)){board[i][j].setToolTipText("Observatory");}
				else if((i>10 && i<18 && j>0 && j<8)||(i == 10 && j==4)){board[i][j].setToolTipText("Patio");}
				else if((i>11&&i<18 && j>19 &&j<24)||(i == 11 && j==22)){board[i][j].setToolTipText("Hall");}
				else if((j>9&&j<16&&i<28&&i>20)||(i == 20&& j==12)){board[i][j].setToolTipText("Dining Room");}
				else if((i<28 && i>21 &&j>20 &&j<24)||(i == 21&& j==20)){board[i][j].setToolTipText("Guest House");}
				else if((i<28 && i>21 && j>0 &&j<6)||(i == 22&& j==6)){board[i][j].setToolTipText("Kitchen");}
				else if((i<16 && i>11 && j>11 &&j<17)||(i == 11&& j==14)){board[i][j].setToolTipText("Pool");}
				//Draws players token in the right place
				if( Board.board[i][j]==4){board[i][j].setIcon(getImage("images/scarlett.png"));
				board[i][j].setToolTipText("Kasandra Scarlett");}
				else if(Board.board[i][j]==5){board[i][j].setIcon(getImage("images/mustard.png"));
				board[i][j].setToolTipText("Jack Mustard");}
				else if(Board.board[i][j]==6){board[i][j].setIcon(getImage("images/white.png"));
				board[i][j].setToolTipText("Diane White");}
				else if(Board.board[i][j]==7){board[i][j].setIcon(getImage("images/green.png"));
				board[i][j].setToolTipText("Jacob Green");}
				else if(Board.board[i][j]==8){board[i][j].setIcon(getImage("images/peacock.png"));
				board[i][j].setToolTipText("Eleanor Peacock");}
				else if(Board.board[i][j]==9){board[i][j].setIcon(getImage("images/plum.png"));
				board[i][j].setToolTipText("Victor Plum");}
				else if(Board.board[i][j]==3){board[i][j].setIcon(getImage("images/wall.png"));}
				else if (Board.board[i][j]==1){board[i][j].setIcon(getImage("images/room.png"));}
				else if (Board.board[i][j]==2){board[i][j].setIcon(getImage("images/door.png"));}
				else{//else draw floor
					board[i][j].setIcon(getImage("images/floor.png"));
				}
				//Draws WEAPONS in the rooms - RANDOMIZES THE WEAPONS 
				if(Board.board[i][j]==10){board[i][j].setIcon(weaponImages.get(0));
				board[i][j].setToolTipText(getWeaponToolTip(0));}
				else if(Board.board[i][j]==11){board[i][j].setIcon(weaponImages.get(1));
				board[i][j].setToolTipText(getWeaponToolTip(1));}
				else if(Board.board[i][j]==12){board[i][j].setIcon(weaponImages.get(2));
				board[i][j].setToolTipText(getWeaponToolTip(2));}
				else if(Board.board[i][j]==13){board[i][j].setIcon(weaponImages.get(3));
				board[i][j].setToolTipText(getWeaponToolTip(3));}
				else if(Board.board[i][j]==14){board[i][j].setIcon(weaponImages.get(4));
				board[i][j].setToolTipText(getWeaponToolTip(4));}
				else if(Board.board[i][j]==15){board[i][j].setIcon(weaponImages.get(5));
				board[i][j].setToolTipText(getWeaponToolTip(5));}
				else if(Board.board[i][j]==16){board[i][j].setIcon(weaponImages.get(6));
				board[i][j].setToolTipText(getWeaponToolTip(6));}
				else if (Board.board[i][j]==17){board[i][j].setIcon(weaponImages.get(7));
				board[i][j].setToolTipText(getWeaponToolTip(7));}
				else if (Board.board[i][j]==18){board[i][j].setIcon(weaponImages.get(8));
				board[i][j].setToolTipText(getWeaponToolTip(8));}
				else if (Board.board[i][j]==19){board[i][j].setIcon(getImage("images/room.png"));
				board[i][j].setToolTipText("No Weapon");}
				board[i][j].setName(i+" "+j);
				//If square is clicked
				final JLabel l = board[i][j];
				l.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						for(int i =0; i< Board.board.length; i++){
							for(int j=0;j< Board.boardSize;j++){
								if(Board.board[i][j] == Game.getCurrent().getCounter()){
									int[] coords ={i,j};//finds counters start position
									move(coords,((JLabel) e.getSource()).getName());
								}
							}
						}
					}
				});
			}
		}
		//trap doors in corner rooms
		board[1][1].setIcon(getImage("images/trap.png"));
		board[1][1].setToolTipText("Door to Guest House");
		board[1][23].setIcon(getImage("images/trap.png"));
		board[1][23].setToolTipText("Door to Kitchen");
		board[27][1].setIcon(getImage("images/trap.png"));
		board[27][1].setToolTipText("Door to Observatory");
		board[27][23].setIcon(getImage("images/trap.png"));
		board[27][23].setToolTipText("Door to Spa");
	}
	/**
	 * Takes the start (current position) and end (click position) and figures out which actions
	 * should be taken by the player eg entering a room, or moving in the corridor
	 * @param start 
	 * @param end
	 */
	public void move(int[] start, String end){
		String[] endCoords = end.split("\\s+");
		int endX = Integer.parseInt(endCoords[0]);
		int endY = Integer.parseInt(endCoords[1]);
		int startX =(start[0]);
		int startY =(start[1]);
		int moves = Math.max(Math.abs((endX - startX)), Math.abs((endY - startY)));
		//If player is currently in a room
		if(Game.getCurrent().getInRoom() && Game.getCurrent().getStartTurn()){
			CanvasPanel.getText().append("Click door to leave. The rooms in the corner rooms have secret paths.\n");
			//Cant click back into to the corridor from room, must leave using doors
			if(Board.board[endX][endY] ==2 || Board.board[endX][endY] ==1){
				if(Game.getCurrent().getCornerRoom()){
					//Go to Corner Room
					if((Game.getCurrent().getCurrentRoom().equals("Spa") && endX==1 && endY==1) ||
							(Game.getCurrent().getCurrentRoom().equals("Observatory") && endX==1 && endY==23)||
							(Game.getCurrent().getCurrentRoom().equals("Guest House") && endX==27 && endY==23)||
							(Game.getCurrent().getCurrentRoom().equals("Kitchen") && endX==27 && endY==1)){
						Game.getCurrent().cornerRoom(startX, startY, Game.getCurrent().getCounter());
						if(Game.getCurrent().getInRoom())canMove = false;
						moveCount = 0;
						centre.removeAll();
						redraw();
					}}
				//Leaving room
				int[] door = Game.getCurrent().currentDoor();
				if (endX==door[0] && endY==door[1]){
					Game.getCurrent().exitRoom(startX, startY, Game.getCurrent().getCounter());
					centre.removeAll();
					redraw();
				}
			}
			else{CanvasPanel.getText().append("Can't move there, click a door.\n");}	
		}else{
			//Just moving around the corridor
			if(rolled){
				if(canMove){
					if(moves <= Game.getCurrent().getRoll() - moveCount){
						if(!Game.getCurrent().getEndTurn()){
							if(Board.board[endX][endY]<3 && Board.board[endX][endY]!=1){
								Game.getCurrent().moveGUI(startX, startY, endX, endY);
								if(Game.getCurrent().getEndTurn()) canMove=false;//gone into room
								moveCount += moves;
								CanvasPanel.getText().append("You've moved " +(moveCount)+ " squares\n");
								Board board = new Board(false);
								centre.removeAll();
								redraw();}
						}
					}
					else{
						CanvasPanel.getText().append("You're trying to move too many squares at once. Try again\n");
					}
				}else{
					CanvasPanel.getText().append("You have no more moves left.\nPlease press the Next button so the next player can go.\n");
				}

				//RESETS THE MOVE
				if (moveCount >= Game.getCurrent().getRoll() ){
					canMove = false;
					moveCount = 0;
				}
			}else{
				CanvasPanel.getText().append("Please roll the dice before moving \n");
			}}
	}

	/**
	 * Finds where the suggested weapon is currently and swaps it into the players current room
	 * @param currentRoom the current room of the player
	 * @param weapon the weapon chosen from the suggestion
	 */
	public static void moveWeapon(String currentRoom, String weapon) {
		for(int i =0; i<Board.board.length; i++){
			for(int j=0;j<Board.boardSize;j++){
				if(board[i][j].getToolTipText()!=null && board[i][j].getToolTipText().equals(weapon) && Board.board[i][j]!=weaponNo(currentRoom)){
					Icon oldIcon = board[i][j].getIcon(); //Weapon that needs to be swapped
					int oldIndex = getWeaponIndex(oldIcon);

					for(int x =0; x<Board.board.length; x++){
						for(int y=0;y<Board.boardSize;y++){
							if(Board.board[x][y]==weaponNo(currentRoom)){//where new weapon goes
								Icon newIcon = board[x][y].getIcon();
								int newIndex = getWeaponIndex(newIcon);
								
								weaponImages.set(oldIndex, (ImageIcon) newIcon);
								weaponImages.set(newIndex, (ImageIcon)oldIcon);
							}
						}
					}
				}
			}
		}
	}

	public static int getWeaponIndex(Icon icon){
		for (int index = 0; index < weaponImages.size(); index++){
			if (weaponImages.get(index) == icon){
				return index;
			}
		}
		return -1;
	}

	public void getWeaponIndex(int weaponNumber, int i, int j){
		if(weaponNumber==10){board[i][j].setIcon(weaponImages.get(0));
		board[i][j].setToolTipText(getWeaponToolTip(0));}
		else if(weaponNumber==11){board[i][j].setIcon(weaponImages.get(1));
		board[i][j].setToolTipText(getWeaponToolTip(1));}
		else if(weaponNumber==12){board[i][j].setIcon(weaponImages.get(2));
		board[i][j].setToolTipText(getWeaponToolTip(2));}
		else if(weaponNumber==13){board[i][j].setIcon(weaponImages.get(3));
		board[i][j].setToolTipText(getWeaponToolTip(3));}
		else if(weaponNumber==14){board[i][j].setIcon(weaponImages.get(4));
		board[i][j].setToolTipText(getWeaponToolTip(4));}
		else if(weaponNumber==15){board[i][j].setIcon(weaponImages.get(5));
		board[i][j].setToolTipText(getWeaponToolTip(5));}
		else if(weaponNumber==16){board[i][j].setIcon(weaponImages.get(6));
		board[i][j].setToolTipText(getWeaponToolTip(6));}
		else if (weaponNumber==17){board[i][j].setIcon(weaponImages.get(7));
		board[i][j].setToolTipText(getWeaponToolTip(7));}
		else if (weaponNumber==18){board[i][j].setIcon(weaponImages.get(8));
		board[i][j].setToolTipText(getWeaponToolTip(8));
		}


	}

	public static int weaponNo(String currentRoom){
		if(currentRoom.equals("Spa")) return 10;
		else if(currentRoom.equals("Patio")) return 11;
		else if(currentRoom.equals("Kitchen")) return 12;
		else if(currentRoom.equals("Theatre")) return 13;
		else if(currentRoom.equals("Pool")) return 14;
		else if(currentRoom.equals("Dining Room")) return 15;
		else if(currentRoom.equals("Guest House")) return 16;
		else if(currentRoom.equals("Hall")) return 17;
		else if(currentRoom.equals("Observatory")) return 18;
		else if(currentRoom.equals("Living Room")) return 19;
		else return -1;
	}


	public ImageIcon getImage(String file){
		ImageIcon image = new ImageIcon(getClass().getResource(file));
		return image;
	}
	public ArrayList<ImageIcon> getWeaponImages(){
		return weaponImages;
	}
	public boolean getRolled(){
		return rolled;
	}
	public void setRolled(boolean r){
		rolled=r;
	}
	public static void setMoveCount(int reset){
		moveCount = reset;
	}
	public static int getMoveCount(){
		return moveCount;
	}
	public boolean getCanMove(){
		return canMove;
	}
	public void setCanMove(boolean c){
		canMove = c;
	}
}
