package Cluedo;
import java.util.ArrayList;

/**
 * This class is repsonsible for the setting up of the board
 * and the redrawing of the board and the components on the board. (in ASCII Characters.)
 * Each number in the 2d array represents a certain component on the board.
 * 3 - Walls of rooms
 * 0 - Corridor
 * 1 - Inside a room
 * 2 - Door
 * (4-9) - represents players
 * 
 * @author Crystal Johnston, Quang Tran
 *
 */
public class Board {

	public static int boardSize = 25;
	public static int [][] board ={
	{3,	3,	3,	3,	3,	3,	8,	0,	3,	3,	3,	3,	3,	0,	3,	3,	3,	3,	3,	3,	9,	0,	3,	3,	3},
	{3,	1,	1,	1,	1,	3,	0,	0,	3,	13,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	3},
	{3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	3},
	{3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	3},
	{3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	18,	3},
	{3,	1,	1,	1,	1,	2,	0,	0,	3,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	3},
	{3,	10,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3,	0,	0,	3,	1,	3},
	{3,	3,	3,	3,	3,	0,	0,	0,	3,	3,	2,	3,	3,	0,	3,	1,	1,	1,	3,	3,	0,	0,	3,	1,	3},
	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	3,	2,	3,	0,	0,	0,	0,	2,	1,	3},
	{7,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	3,	3,	3},
	{3,	3,	3,	3,	2,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
	{3,	1,	1,	1,	1,	3,	3,	3,	3,	0,	0,	3,	3,	3,	2,	3,	3,	3,	0,	3,	3,	3,	2,	3,	3},
	{3,	1,	1,	1,	1,	1,	1,	1,	3,	0,	0,	3,	14,	1,	1,	1,	1,	3,	0,	3,	17,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	3,	0,	3,	1,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	1,	1,	3,	0,	0,	3,	3,	3,	3,	3,	3,	3,	0,	3,	1,	1,	1,	1,	3},
	{3,	11,	1,	1,	1,	3,	3,	3,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	3,	1,	1,	1,	1,	3},
	{3,	3,	3,	3,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	3,	3,	3,	3,	3,	3},
	{6,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	3,	3,	2,	3,	3,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0},
	{3,	3,	3,	3,	3,	3,	0,	0,	0,	0,	3,	15,	1,	1,	1,	3,	0,	0,	0,	0,	2,	3,	3,	3,	3},
	{3,	12,	1,	1,	1,	1,	2,	0,	0,	0,	3,	1,	1,	1,	1,	3,	0,	0,	0,	0,	3,	16,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	1,	3,	0,	0,	0,	0,	3,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	3},
	{3,	1,	1,	1,	1,	1,	3,	0,	0,	3,	1,	1,	1,	1,	1,	1,	3,	0,	0,	0,	3,	1,	1,	1,	3},
	{3,	3,	3,	3,	3,	3,	3,	5,	0,	3,	3,	3,	3,	3,	3,	3,	3,	0,	4,	0,	3,	3,	3,	3,	3}};

	public Board(boolean restart){
		if(restart)setUp();
		redraw();
	}

	/**
	 * Sets up the board and the components on the board, drawing the 
	 * appropriate symbols for each component and player. 
	 */
	private void redraw() {
		if(Game.textBased){
		System.out.println();
		for (int i = 0; i < board.length; i++){
			for(int j = 0; j < boardSize; j++){
				if (board[i][j] == 1) System.out.print("  ");
				else if(board[i][j] == 3) System.out.print("x ");
				else if(board[i][j] == 2) System.out.print("# ");
				else if(board[i][j] == 4) {System.out.print("KS");}
				else if(board[i][j] == 5) {System.out.print("JM");}
				else if(board[i][j] == 6) {System.out.print("DW");}
				else if(board[i][j] == 7) {System.out.print("JG");}
				else if(board[i][j] == 8) {System.out.print("EP");}
				else if(board[i][j] == 9){ System.out.print("VP");}
				else System.out.print("* ");
			}
			System.out.println();
		}
		}
	}
	public void setUp() {
		for (int i = 0; i < board.length; i++){
			for(int j = 0; j < boardSize; j++){
				if (board[i][j] ==4 || board[i][j] ==5 ||board[i][j] ==6 || board[i][j] ==7 ||board[i][j] ==8 ||board[i][j] ==9 ){
				if((i==3 ||i==4 ||i==5 ||i==14 ||i==12 ||i==22 ||i==23)
				&&(j==4||j==16||j==10||j==23||j==14||j==12||j==22||j==23)){
					//Player was in a room
					board[i][j] = 1;
				}
				else board[i][j] = 0;
				}
			}
		}
		//Resets players
		board[28][18] = 4;
		board[28][7] = 5;
		board[19][0] = 6;
		board[9][0] = 7;
		board[0][6] = 8;
		board[0][20] = 9;		
	}

}
