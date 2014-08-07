package Cluedo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GraphicalUserInterface{
	private static final int imageWidth = 600;
	private static final int imageHeight = 730;
	private static CanvasPanel frame;
	private JFrame radio;
	private static Game game;
	//Used for adding of players
	private int numPlayers = 0;
	private int playerCount = 0;
	
	private static BoardPanel outer;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public GraphicalUserInterface() {
		frame = new CanvasPanel(this,(outer = new BoardPanel()));
		//Popup position of frame
		frame.setBounds(screenSize.width/2 - imageWidth/2, 0, imageWidth, imageHeight);

		frame.textOrGUI();
		if (!Game.textBased){
			setUpPlayers();
		} 
	}

	
	public void setUpPlayers(){
		//RESET
		numPlayers = 0; 
		playerCount = 0;
		//STARTING GAME RADIO
		radio = new JFrame ("Players");
		radio.setSize(250,100);
		radio.setResizable(false);
		final JRadioButton three = new JRadioButton("3");
		three.setActionCommand("3");
		final JRadioButton four = new JRadioButton("4");
		four.setActionCommand("4");
		final JRadioButton five = new JRadioButton("5");
		five.setActionCommand("5");
		final JRadioButton six = new JRadioButton("6");
		six.setActionCommand("6");
		ButtonGroup group = new ButtonGroup();
		group.add(three);
		group.add(four);
		group.add(five);
		group.add(six);
		JButton selectPlayers = new JButton("Select number");
		//ACTION LISTENER FOR SELECT PLAYERS
		selectPlayers.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				numPlayers = 0; 					//Reset
				if(three.isSelected()||four.isSelected()||
						five.isSelected()||six.isSelected()){
					if (three.isSelected()){
						Game.setPlayers(3);  
						numPlayers = 3;
					}else if (four.isSelected()){
						Game.setPlayers(4);
						numPlayers = 4;
					}else if (five.isSelected()){
						Game.setPlayers(5);
						numPlayers = 5;
					}else if (six.isSelected()){
						Game.setPlayers(6);
						numPlayers = 6;
					}
					radio.dispose();				//Exit the radio menu
					setUpCharacterSelection();		//CHARACTER SELECTION
				} 
			}
		});
		//ADDITIONS
		JPanel radioPanel = new JPanel(); 
		radioPanel.add(three);
		radioPanel.add(four);
		radioPanel.add(five);
		radioPanel.add(six);
		radioPanel.add(selectPlayers);
		radio.add(radioPanel);
		radio.setBounds(screenSize.width/2 - radio.getWidth()/2, imageHeight/2 - radio.getHeight()/2, radio.getWidth(), radio.getHeight());
		radio.setVisible(true);
	}
	
	private void setUpCharacterSelection(){		
		//PLAYERS WINDOW
		final JFrame window = new JFrame("Player " + (playerCount+1) + " :");
		window.setLayout(new BorderLayout());
		final JTextField name = new JTextField(15);
		//RADIO NAMES
		final JRadioButton scarlett = new JRadioButton("Kasandra Scarlett");
		final JRadioButton mustard = new JRadioButton("Jack Mustard");
		final JRadioButton white = new JRadioButton("Diane White");
		final JRadioButton green = new JRadioButton("Jacob Green");
		final JRadioButton peacock = new JRadioButton("Eleanor Peacock");
		final JRadioButton plum = new JRadioButton("Victor Plum");
		final JButton selectPlayers = new JButton("Done");

		//Grey out already used Characters
		for (Characters people: Game.getCharacters()){
			if (people.toString().equalsIgnoreCase(scarlett.getText())){scarlett.setEnabled(false);}
			if (people.toString().equalsIgnoreCase(mustard.getText())){mustard.setEnabled(false);}
			if (people.toString().equalsIgnoreCase(white.getText())){white.setEnabled(false);}
			if (people.toString().equalsIgnoreCase(green.getText())){green.setEnabled(false);}
			if (people.toString().equalsIgnoreCase(peacock.getText())){peacock.setEnabled(false);}
			if (people.toString().equalsIgnoreCase(plum.getText())){plum.setEnabled(false);}
		}
		//DONE ACTION LISTENER
		selectPlayers.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				//Does not allow duplicate names in the game.
				boolean duplicate = false;
				for (Player players: Game.getPlayers()){
					if (players.getName().toString().equalsIgnoreCase(name.getText())){
						CanvasPanel.getText().append("There is another player with the same name, please choose another.\n");
						duplicate = true;
						break;
					}
				}
				//Makes sure the user enters a name and selects a character
				if(!duplicate && !name.getText().equals("") && (scarlett.isSelected()||mustard.isSelected()||white.isSelected()||
						green.isSelected()||  peacock.isSelected()||plum.isSelected())){
					Characters current = null;
					if (scarlett.isSelected()){
						current = new Characters("Kasandra Scarlett");
						Game.getPlayers().add(new Player(playerCount++, current, name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Kasandra Scarlett.\n"
								, (playerCount),name.getText()));
						scarlett.setEnabled(false);
					}else if (mustard.isSelected()){
						current = new Characters("Jack Mustard");
						Game.getPlayers().add(new Player(playerCount++, current,name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Jack Mustard.\n"
								, (playerCount),name.getText()));
						mustard.setEnabled(false);
					}else if (white.isSelected()){
						current = new Characters("Diane White");
						Game.getPlayers().add(new Player(playerCount++, current,name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Diane White.\n"
								, (playerCount),name.getText()));
						white.setEnabled(false);
					}else if (green.isSelected()){
						current =  new Characters("Jacob Green");
						Game.getPlayers().add(new Player(playerCount++,current,name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Jacob Green.\n"
								, (playerCount),name.getText()));
						green.setEnabled(false);
					}else if (peacock.isSelected()){
						current = new Characters("Eleanor Peacock");
						Game.getPlayers().add(new Player(playerCount++, current,name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Eleanor Peacock.\n"
								, (playerCount),name.getText()));
						peacock.setEnabled(false);
					}else if (plum.isSelected()){
						current = new Characters("Victor Plum");
						Game.getPlayers().add(new Player(playerCount++, current,name.getText()));
						CanvasPanel.getText().append(String.format("Player %d - %s has joined the game with Victor Plum.\n"
								, (playerCount),name.getText()));
						plum.setEnabled(false);
					}
					Game.getCharacters().add(current);
					duplicate = false;
					window.dispose();		//exit window
					if (playerCount < numPlayers){
						setUpCharacterSelection();	//Recursive - Opens up same amount of windows as players.
					} else {
						CanvasPanel.getText().append("All players have joined the game. Let the game now commence!\n");
						game = new Game(false);
						new CanvasPanel(game);
						outer.redraw();
						frame.drawHand();
					}
				} 
			}
		});
		//Button ADDITIONS
		ButtonGroup group = new ButtonGroup();
		group.add(scarlett);
		group.add(mustard);
		group.add(peacock);
		group.add(white);
		group.add(plum);
		group.add(green);

		JPanel panel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Player enter your name"));
		topPanel.add(name);
		panel.add(new JLabel("Choose a Character"));
		panel.add(scarlett);
		panel.add(mustard);
		panel.add(white);
		panel.add(green);
		panel.add(peacock);
		panel.add(plum);
		window.add(topPanel, BorderLayout.NORTH);
		window.add(panel,BorderLayout.WEST);
		window.add(selectPlayers, BorderLayout.SOUTH);
		window.setResizable(false);
		//DIMENSIONS
		topPanel.setPreferredSize(new Dimension(150, 60));
		panel.setPreferredSize(new Dimension(150,300));
		window.setSize(250,350);
		window.setBounds(screenSize.width/2 - window.getWidth()/2, imageHeight/2 - window.getHeight()/2, window.getWidth(), window.getHeight());
		window.setVisible(true);
	}
	public void reset(){
		CanvasPanel.getText().setText(null);
		Game.getCharacters().clear();
	}
	public ImageIcon getImage(String file){
		ImageIcon image = new ImageIcon(getClass().getResource(file));
		return image;
	}
	
	/**
	 * Sets up the frame for the suggestion/accusation. Rooms are not displayed if its only
	 * a suggestion as the player's current room is used.
	 * @param accuse
	 */
	public static void suggestionOrAccuse(final boolean accuse){
		//New Window
		final JFrame window = new JFrame("Suggestion");
		if (accuse){
			window.setTitle("Accusation");
		}
		window.setLayout(new BorderLayout());
		//GROUPS
		final ButtonGroup ppl = new ButtonGroup();
		final ButtonGroup weps = new ButtonGroup();
		final ButtonGroup rooms = new ButtonGroup();
		//PANELS
		JPanel topPanel = new JPanel();
		JPanel pplPanel = new JPanel();
		JPanel wepsPanel = new JPanel();
		JPanel roomsPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		pplPanel.setPreferredSize(new Dimension(120, 600));
		wepsPanel.setPreferredSize(new Dimension(120, 600));
		if (accuse){
			roomsPanel.setPreferredSize(new Dimension(100,600));
		}
		//BUTTONS 
		for (int i =0; i < Deck.people.length; i++){
			final JRadioButton butt = new JRadioButton(Deck.people[i]);
			butt.setActionCommand(Deck.people[i]);
			ppl.add(butt);
			pplPanel.add(butt);
		}
		for (int i =0; i < Deck.weps.length; i++){
			final JRadioButton butt = new JRadioButton(Deck.weps[i]);
			butt.setActionCommand(Deck.weps[i]);
			weps.add(butt);
			wepsPanel.add(butt);
		}
		if (accuse){
			for (int i = 0; i < Deck.room.length; i++){
				final JRadioButton butt = new JRadioButton(Deck.room[i]);
				butt.setActionCommand(Deck.room[i]);
				rooms.add(butt);
				roomsPanel.add(butt);
			}
		}
		JButton done = new JButton("Suggest");
		if (accuse){
			done = new JButton("Accuse");
		}
		//ADDITIONS
		JLabel displayText = new JLabel("Characters                      Weapons                       Room: "+Game.getCurrent().getCurrentRoom());
		if (accuse){
			displayText = new JLabel("Characters                      Weapons                       Rooms");
		}
		topPanel.add(displayText);
		mainPanel.add(pplPanel, BorderLayout.WEST);
		mainPanel.add(wepsPanel, BorderLayout.CENTER);
		if (accuse){
			mainPanel.add(roomsPanel, BorderLayout.EAST);
		}
		window.add(topPanel, BorderLayout.NORTH);
		window.add(mainPanel, BorderLayout.WEST);
		window.add(done, BorderLayout.SOUTH);
		//Window Features
		window.setSize(400,400);
		window.setResizable(false);
		//Position
		window.setBounds(screenSize.width/2 - window.getWidth()/2, imageHeight/2 - window.getHeight()/2, window.getWidth(), window.getHeight());
		window.setVisible(true);

		done.addActionListener(new ActionListener(){				//DONE BUTTON
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ACCUSATION - all button groups must be selected... i.e. wep, room and murderer
				if (ppl.getSelection() != null && weps.getSelection() != null && rooms.getSelection() != null){
					readSelectedCards(ppl,weps,rooms, true, window);
				}
				//SUGGESTION - all button groups must be selected except for room, i.e. wep and murderer
				else if (!accuse && ppl.getSelection() != null && weps.getSelection() != null){
					readSelectedCards(ppl,weps,rooms,false, window);
				}
			}
		});
	}

	/**
	 * Reads which suggestions were picked. This is then fed to another method which determines
	 * if other players have those cards or not / if the accusation is correct
	 * @param ppl
	 * @param weps
	 * @param rooms
	 * @param accuse
	 * @param window
	 */
	public static void readSelectedCards(ButtonGroup ppl, ButtonGroup weps, ButtonGroup rooms, boolean accuse, JFrame window){
		//Matches selected item to a name/weapon/room
		String name = null;
		String room = null;
		String weapon = null;
		if(ppl.getSelection().getActionCommand().equalsIgnoreCase("Kasandra Scarlett")){name = "Kasandra Scarlett";}
		else if (ppl.getSelection().getActionCommand().equalsIgnoreCase("Jack Mustard")){name = "Jack Mustard";}
		else if (ppl.getSelection().getActionCommand().equalsIgnoreCase("Diane White")){name = "Diane White";}
		else if (ppl.getSelection().getActionCommand().equalsIgnoreCase("Jacob Green")){name = "Jacob Green";}
		else if (ppl.getSelection().getActionCommand().equalsIgnoreCase("Eleanor Peacock")){name = "Eleanor Peacock";}
		else if (ppl.getSelection().getActionCommand().equalsIgnoreCase("Victor Plum")){name = "Victor Plum";}
		if (weps.getSelection().getActionCommand().equalsIgnoreCase("Rope")){weapon = "Rope";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Axe")){weapon = "Axe";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Poison")){weapon = "Poison";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Trophy")){weapon = "Trophy";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Knife")){weapon = "Knife";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Baseball Bat")){weapon = "Baseball Bat";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Candlestick")){weapon = "Candlestick";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Pistol")){weapon = "Pistol";}
		else if (weps.getSelection().getActionCommand().equalsIgnoreCase("Dumbbell")){weapon = "Dumbbell";}
		if (accuse){
			if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Spa")){room = "Spa";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Theatre")){room = "Theatre";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Living Room")){room = "Living Room";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Observatory")){room = "Observatory";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Hall")){room = "Hall";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Guest Room")){room = "Guest Room";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Dining Room")){room = "Dining Room";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Kitchen")){room = "Kitchen";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Patio")){room = "Patio";}
			else if (rooms.getSelection().getActionCommand().equalsIgnoreCase("Pool")){room = "Pool";}
		}
		window.dispose();
		//Moves weapon to correct room and reads selections, checking other players or solutions
		if(accuse){
			outer.moveWeapon(room, weapon);
			Game.getCurrent().readSuggestionsOrAccusationsGUI(false, name, weapon, room);
			Game.getCurrent().accusation(12, 14);
		}
		else{
			Game.getCurrent().readSuggestionsOrAccusationsGUI(true, name, weapon, room);
			Game.getCurrent().suggestion();
			outer.moveWeapon(Game.getCurrent().getCurrentRoom(), weapon);
			outer.redraw();
		}
	}
	

	public static JFrame getFrame(){
		return frame;
	}
	public static Game getGame(){
		return game;
	}
	public BoardPanel getBoard(){
		return outer;
	}
	
	
	
}
