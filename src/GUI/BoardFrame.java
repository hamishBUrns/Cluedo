package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

import main.Game;

/**
 * Main GUI for the Cluedo game
 * @author kraemezoe
 *
 */
public class BoardFrame extends JFrame{
	//selector fields for choosing character/room/weapons?
	//draw board from images essentially replacing the toString bits
	//buttons to: end turn, suggest, accuse (only pressable after moving finished)
	//dialogue boxes for suggest, accuse, leaving room, new turn, public dialogues
	//keyListener to handle movement

	//STEP 1: set up pop-up dialogues that start the game
			//Players put in name //text field
			// players choose character //radio box

	//STEP 1.5: Board drawing
			//Drawing weapon and players
			//Drawing on weapon and player movement

	//STEP 2: change move to use key listener

	//STEP 2.5: End Turn
			// integrate logic

	//STEP 3: Dialogue boxes at start of turn with information
			//Dialogue box at end of game

	//STEP 4: Suggestion/accuse
			//Pop ups with combo boxes or text fields

	//STEP 5: Dialogue box when attempting to close main window/ new game etc

	//STEP 6: When exiting room select door with mouse to choose

	//STEP 7: Displaying hand as images
			//Otherwise text

	//STEP 8: Change logic of checklist to be personal
			//Display Checklist

	private Controller control;
	private JMenuBar menuBar;
	private JTextArea checklist;
	private MyCanvas canvas;

	public BoardFrame(Controller ctrl) {
		super("Cluedo");


		control = ctrl;
		menuBar = new JMenuBar();
		canvas = new MyCanvas(control);
		menuBar.add(menu());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setJMenuBar(menuBar);

		add(createTabbedPane(), BorderLayout.EAST);
		add(canvas,BorderLayout.CENTER);
		pack();
		setVisible(true);

		//startGameSetup();

	}
	public JPanel getCanvas(){
		return canvas;
	}
	public int getNumPlayers(){
		//setUpPSDPanel();
		Object [] options = {2, 3, 4, 5, 6};
		int numPlayers = (int) JOptionPane.showInputDialog(
				(JFrame) this,
				"How many players?",
				"Welcome to Cluedo!",
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]);
		return numPlayers;
	}

	/**
	 * set up the menu
	 * @return
	 */
	private JMenu menu(){
		JMenu menu = new JMenu("File");
		menu.add(new JMenuItem("Help"));
		menu.add(new JMenuItem("Quit"));
		return menu;
	}

	/**
	 * set up the tapped pane
	 * @return
	 */
	private JTabbedPane createTabbedPane(){
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setPreferredSize(new Dimension(300,500));
		tabPane.addTab("Actions", testPanel());
		tabPane.addTab("Checklist", checklistPanel());
		tabPane.addTab("Cards", handPanel());
		return tabPane;
	}

	private JPanel testPanel(){
		JPanel test = new JPanel();

		JButton sug = new JButton("Suggest");
		sug.setMnemonic(KeyEvent.VK_S);//actually alt + s bc weird swing stuff??
		sug.setActionCommand("suggest");
		sug.addActionListener(control);

		JButton acc = new JButton("Accuse");
		acc.setMnemonic(KeyEvent.VK_A);//actually alt + a
		acc.setActionCommand("accuse");
		acc.addActionListener(control);

		JButton end = new JButton("End Turn");
		end.setMnemonic(KeyEvent.VK_E);//actually alt + e
		end.setActionCommand("end");
		end.addActionListener(control);

		test.add(sug);
		test.add(acc);
		test.add(end);
		return test;
	}

	/**
	 * set up the checklist tab panel
	 * @return
	 */
	private JPanel checklistPanel(){
		JPanel checkPanel = new JPanel();
		checklist = new JTextArea("the checklist goes here");
		checklist.setEditable(false);
		checkPanel.add(checklist);
		return checkPanel;
	}

	/**
	 * set up the player hand tab panel
	 * @return
	 */
	private JPanel handPanel(){
		JPanel hand = new JPanel();
		JTextArea cards = new JTextArea("the hand goes here");
		cards.setEditable(false);
		hand.add(cards);
		return hand;
	}

	public void updateChecklist(String text){
		checklist.setText(text);
	}

	public String guessDialog(String title, String msg, Object[] options){
		String s = (String) JOptionPane.showInputDialog(
				(JFrame) this,
				msg,
				title,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		return s;
	}

}
