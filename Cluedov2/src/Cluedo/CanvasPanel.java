package Cluedo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

public class CanvasPanel extends JFrame {
	private static Game game;
	private static JTextArea textOutput;
	private final int imageWidth = 600;
	private final int imageHeight = 730;
	private static BoardPanel outer;
	private JPanel cardPanel;
	private JPanel top;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	GraphicalUserInterface gui;
	private JLabel turnText = new JLabel();
	
	public CanvasPanel(GraphicalUserInterface gui, BoardPanel outer){
		this.gui = gui;
		this.outer = outer;
		setUpInterface();
	}
	public CanvasPanel(Game game){
	this.game = game;}
	
	/**
	 * Adds all components to frame
	 */
	public void setUpInterface() {
		//WINDOW
		this.setTitle("Cluedo");
		this.setSize(imageWidth+20, imageHeight+20);				//SIZE
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//PANELS
		top = new JPanel(new BorderLayout());
		JPanel bottom = new JPanel(new BorderLayout());
		JPanel left = new JPanel();
		JPanel textPanel = new JPanel();
		cardPanel = new JPanel();
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));

		JScrollPane scroll = new JScrollPane(cardPanel);
		scroll.setPreferredSize(new Dimension(80*5,120));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		outer.setPreferredSize(new Dimension(400,400));
		top.setLayout(new FlowLayout());
		bottom.add(scroll, BorderLayout.EAST);
		bottom.add(left, BorderLayout.WEST);
		bottom.add(textPanel, BorderLayout.SOUTH);
		this.add(outer,BorderLayout.CENTER);
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		this.setSize(imageWidth, imageHeight);

		//COMPONENTS
		JButton diceButton = new JButton("Dice");
		JButton nextButton = new JButton("Next");
		textOutput = new JTextArea(5, 50);
		textOutput.setEditable(false);
		JScrollPane textSP = new JScrollPane(textOutput);
		textSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//MENU COMPONENTS
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu infoMenu = new JMenu("Info");
		JMenuItem shortCutsInfo = new JMenuItem("View Short Cut Keys");		//MENU ITEMS
		JMenuItem quit = new JMenuItem("Quit");	
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem playersGame = new JMenuItem("Players");
		JMenuItem gameType = new JMenuItem("Game Mode");

		//ADD COMPONENTS
		left.add(diceButton, BorderLayout.WEST);
		left.add(nextButton,BorderLayout.WEST);
		textPanel.add(textSP,BorderLayout.PAGE_END);
		this.setJMenuBar(menuBar);							//MENU										
		infoMenu.add(shortCutsInfo);
		gameMenu.add(newGame);
		gameMenu.add(gameType);
		gameMenu.add(playersGame);
		gameMenu.add(quit);
		menuBar.add(gameMenu);
		menuBar.add(infoMenu);

		//KEYBOARD SHORTCUTS 
		//Next Player = n 
		nextButton.setMnemonic('N');
		nextButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke('n'), "Next" );
		nextButton.getActionMap().put("Next", new ClickAction(nextButton));
		//Dice roll = d
		diceButton.setMnemonic('D');
		diceButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke('d'), "Dice" );
		diceButton.getActionMap().put("Dice", new ClickAction(diceButton));
		//New Game = ctrl + n
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		//Quit  = ctrl + q
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		//To see Players = ctrl + p
		playersGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		//To view Short cut keys = ctrl + v
		shortCutsInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		//To view Short cut keys = ctrl + v
		gameType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));


		//ACTION LISTENERS
		diceButton.addActionListener(new ActionListener() {			//DICE
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(game!=null){
					if(!outer.getRolled()){
						outer.setRolled(true);
						textOutput.append("Tip: To go into a room click the door.\n");
						game.play();
					}
				}		
				else{System.out.println("This breaks");}
			}});
		nextButton.addActionListener(new ActionListener() {			//NEXT
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!outer.getCanMove()){
						//resets players booleans so the next player can move
						game.next();
						drawHand();
						textOutput.setText(null);
						textOutput.append(Game.getCurrent().getCharName() +"'s turn\n");
						outer.setRolled(false);
						outer.setCanMove(true);
						Game.getCurrent().setRoll(0);
						BoardPanel.setMoveCount(0);
						outer.redraw();
					}
					else{
						textOutput.append("You still have moves to use up!\n");
					}
				} catch(NullPointerException e1){}
			}
		});
		//MENU ACTION LISTENERS
		playersGame.addActionListener(new ActionListener(){					//PLAYERS
			@Override
			public void actionPerformed(ActionEvent arg0){
				viewPlayers();
			}
		});
		newGame.addActionListener(new ActionListener(){						//NEW GAME
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ask the user to confirm they wanted to do this
				int r = JOptionPane.showConfirmDialog(getFrame(), new JLabel(
						"Are you sure you want to start a new game? \nYour previous game will be lost."), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					Game.getPlayers().clear();
					outer.getWeaponImages().clear();
					gui.reset();
					gui.setUpPlayers();
					outer.setUpWeaponImages();
					outer.setCanMove(true);
					outer.setRolled(false); 
					BoardPanel.setMoveCount(0);
					outer.redraw();
				}
			}
		});
		quit.addActionListener(new ActionListener() {						//QUIT 
			@Override
			public void actionPerformed(ActionEvent e) {
				//Confirmation popup 
				int r = JOptionPane.showConfirmDialog(getFrame(), new JLabel(
						"Are you sure you want Quit? \nYour previous game will be lost."), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		shortCutsInfo.addActionListener(new ActionListener() {				//SHORTCUTS
			@Override
			public void actionPerformed(ActionEvent e) {
				viewShortCuts();
			}
		});
		gameType.addActionListener(new ActionListener() {					//GAME MODE
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textOrGUI();
			}
		});

		this.addWindowListener(new WindowListener() {						//WINDOW LISTENER
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {
				// Ask the user to confirm they wanted to do this
				int r = JOptionPane.showConfirmDialog(getFrame(), new JLabel(
						"Are you sure you want to quit Cluedo?"), "Confirm Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.setVisible(true);
	}
	
	//Displays shortcuts for the game so players can use them readily
	public void viewShortCuts(){
		final JFrame window = new JFrame("Short Cuts");
		//PANELS
		JPanel shortCutPanel = new JPanel();
		JPanel confirmPanel = new JPanel();
		JPanel topPanel = new JPanel();
		//BUTTONS
		JButton confirm = new JButton("Confirm");
		//ADDITIONS
		Font font = new Font("Times new roman", Font.ITALIC, 32);
		JLabel title = new JLabel("SHORT CUTS");
		title.setFont(font);
		topPanel.add(title);
		JLabel dice = new JLabel("Roll Dice = Keys: d\n");
		JLabel nextPlayer = new JLabel("Next Player = Keys: n\n");
		JLabel newGame = new JLabel("New Game = Keys: Ctrl + n\n");
		JLabel players = new JLabel("View Players = Keys: Ctrl + p\n");
		JLabel quit = new JLabel("Quit Game = Keys: Ctrl + q\n");
		JLabel shortCuts = new JLabel("View Short Cuts = Keys: Ctrl + v\n");
		JLabel gameType = new JLabel("Choose Game Type = Keys: Ctrl + g\n");
		shortCutPanel.add(dice);
		shortCutPanel.add(nextPlayer);
		shortCutPanel.add(newGame);
		shortCutPanel.add(players);
		shortCutPanel.add(quit);
		shortCutPanel.add(shortCuts);
		shortCutPanel.add(gameType);
		confirmPanel.add(confirm);
		//LAYOUTS
		window.add(topPanel, BorderLayout.NORTH);
		window.add(shortCutPanel, BorderLayout.CENTER);
		window.add(confirmPanel, BorderLayout.SOUTH);
		//ACTION LISTENER
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { window.dispose(); }
		});
		window.setSize(220,280);
		window.setResizable(false);
		window.setBounds(screenSize.width/2 - window.getWidth()/2, this.getHeight()/2 - window.getHeight()/2, window.getWidth(), window.getHeight());
		window.setVisible(true);		
	}
	
	public void textOrGUI(){
		String[] buttons = { "Text", "Graphical User Interface" };
		//Text or GUI based 
		int textOrGui = JOptionPane.showOptionDialog(this, "Please choose a Game Type.", "Cluedo Game Type",
				JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[1]);
		if (textOrGui == JOptionPane.YES_OPTION) {
			this.dispose();
			Game.textBased = true;
			Game.getPlayers().clear();
			Game.setPlayers(0);
			game = new Game(true);
		} 
	}
	
	//Displays current players from menu
	public void viewPlayers(){
		final JFrame window = new JFrame("Players");
		//PANELS
		JPanel playerPanel = new JPanel();
		JPanel confirmPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		JPanel topPanel = new JPanel();
		//BUTTONS
		JButton confirm = new JButton("Confirm");
		//ADDITIONS
		Font font = new Font("Times new roman", Font.ITALIC, 32);
		JLabel title = new JLabel("PLAYERS");
		title.setFont(font);
		topPanel.add(title);
		for (Player players: Game.getPlayers()){
			JLabel image = new JLabel();
			JLabel label = new JLabel(String.format("Player %d: %s has the character %s\n",(players.getPlayerNumber()+1)
					, players.getName().toString(), players.getCharName().toString()));
			if (players.getCharName().toString().equals("Kasandra Scarlett")){image.setIcon(getImage("images/scarlett.png"));}
			else if (players.getCharName().toString().equals("Jack Mustard")){image.setIcon(getImage("images/mustard.png"));}
			else if (players.getCharName().toString().equals("Diane White")){image.setIcon(getImage("images/white.png"));}
			else if (players.getCharName().toString().equals("Jacob Green")){image.setIcon(getImage("images/green.png"));}
			else if (players.getCharName().toString().equals("Eleanor Peacock")){image.setIcon(getImage("images/peacock.png"));}
			else if (players.getCharName().toString().equals("Victor Plum")){image.setIcon(getImage("images/plum.png"));}
			imagePanel.add(image);
			playerPanel.add(label);

		}
		confirmPanel.add(confirm);
		//LAYOUTS
		imagePanel.setPreferredSize(new Dimension(30,window.getHeight()));
		window.add(topPanel, BorderLayout.NORTH);
		window.add(imagePanel, BorderLayout.WEST);
		window.add(playerPanel, BorderLayout.CENTER);
		window.add(confirmPanel, BorderLayout.SOUTH);
		//ACTION LISTENER
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { window.dispose(); }
		});
		window.setSize(400,300);
		window.setResizable(false);
		window.setBounds(screenSize.width/2 - window.getWidth()/2, imageHeight/2 - window.getHeight()/2, window.getWidth(), window.getHeight());
		window.setVisible(true);
	}
	
	/**
	 * Draws players hand and displays whose turn it is at the top of the screen
	 */
	public void drawHand(){
		cardPanel.removeAll();
		top.removeAll();
		turnText.setText(Game.getCurrent().getName()+": " + Game.getCurrent().getCharName() + "'s Turn");
		Font font = new Font("Times new roman", Font.BOLD + Font.ITALIC, 20);
		turnText.setFont(font);
		JLabel icon = new JLabel();
		//Counter image shown at the top
		if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Kasandra Scarlett")){
			icon.setIcon((getImage("images/scarlett.png")));
		} else if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Jack Mustard")){
			icon.setIcon((getImage("images/mustard.png")));
		} else if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Diane White")){
			icon.setIcon((getImage("images/white.png")));
		} else if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Jacob Green")){
			icon.setIcon((getImage("images/green.png")));
		} else if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Eleanor Peacock")){
			icon.setIcon((getImage("images/peacock.png")));
		} else if (Game.getCurrent().getCharName().toString().equalsIgnoreCase("Victor Plum")){
			icon.setIcon((getImage("images/plum.png")));
		}
		top.add(icon, BorderLayout.WEST);
		top.add(turnText,BorderLayout.CENTER);
		GridLayout grid = new GridLayout(0, Game.deck.getDeck().size()/(Game.getPlayers().size()));
		JLabel[] cards = new JLabel[Game.deck.getDeck().size()/(Game.getPlayers().size())];
		//Card images shown for correct players hand
		for (int i =0; i < Game.getCurrent().getHand().size(); i++){
			cards[i] = new JLabel();
			cardPanel.add(cards[i]);
			//Characters
			if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Kasandra Scarlett")){ cards[i].setIcon(( getImage("images/scarCard.png")));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Jack Mustard")){cards[i].setIcon(getImage("images/musCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Diane White")){ cards[i].setIcon(getImage("images/whiCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Jacob Green")){ cards[i].setIcon(getImage("images/greCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Eleanor Peacock")){ cards[i].setIcon(getImage("images/peaCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Victor Plum")){ cards[i].setIcon(getImage("images/pluCard.png"));}
			//Weapons
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Rope")){cards[i].setIcon(getImage("images/ropeCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Candlestick")){ cards[i].setIcon(getImage("images/candleCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Knife")){ cards[i].setIcon(getImage("images/knifeCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Pistol")){ cards[i].setIcon(getImage("images/gunCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Baseball Bat")){ cards[i].setIcon(getImage("images/baseballCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Trophy")){cards[i].setIcon(getImage("images/trophyCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Dumbbell")){ cards[i].setIcon(getImage("images/dumbbellCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Poison")){ cards[i].setIcon(getImage("images/poisonCard.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Axe")){ cards[i].setIcon(getImage("images/axeCard.png"));}
			//Rooms
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Spa")){cards[i].setIcon(getImage("images/spa.png"));}
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Theatre")){ cards[i].setIcon(getImage("images/theatre.png"));}		
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Living Room")){ cards[i].setIcon(getImage("images/lounge.png"));} 
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Observatory")){ cards[i].setIcon(getImage("images/observatory.png"));}	
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Guest House")){ cards[i].setIcon(getImage("images/guest.png"));}	
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Dining Room")){cards[i].setIcon(getImage("images/dining.png"));}	
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Kitchen")){ cards[i].setIcon(getImage("images/kitchen.png"));}		
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Patio")){ cards[i].setIcon(getImage("images/patio.png"));}			
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Pool")){ cards[i].setIcon(getImage("images/pool.png"));}			
			else if (Game.getCurrent().getHand().get(i).toString().equalsIgnoreCase("Hall")){ cards[i].setIcon(getImage("images/hall.png")); 			
			}
		}
		grid.setVgap(0);
		grid.setHgap(0);
	}
	
	public int getHeight(){
		return imageHeight;
	}
	public int getWight(){
		return imageWidth;
	}
	public static JTextArea getText(){
		return textOutput;
	}
	
	public final JFrame getFrame(){
		return this;
	}
	
	public final JPanel getCardPanel(){
		return cardPanel;
	}
	public final JPanel getTop(){
		return top;
	}
	public ImageIcon getImage(String file){
		ImageIcon image = new ImageIcon(getClass().getResource(file));
		return image;
	}

}
