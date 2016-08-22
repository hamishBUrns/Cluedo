package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.Game;

/**
 * Main GUI for the Cluedo game
 *
 * @author kraemezoe
 *
 */
public class BoardFrame extends JFrame {


	/**
	 * To Do:
	 *
	 * ESSENTIAL
	 * Java Doc everything
	 * Internal comment stuff
	 * Remove System Out
	 * Sequence diagram
	 * Object diagram
	 * Individual report
	 *
	 * Implement images for tokens (JLabel)
	 * Implement hover for labels
	 * Show number of dice steps left
	 * Suggest/checklist logic
	 * Name the rooms (labels?)
	 *
	 * OTHER
	 * Showing the secret staircases
	 * Change resizeable logic so that selecting doors wont bug out
	 * Have doors change color when mousing over them
	 * Dialogue boxes for
	 * 		Change of players turn
	 *
	 *
	 *
	 */
	private Controller control;
	private JMenuBar menuBar;
	private JTextArea cards;
	private JTextArea checklist;
	private JLabel nick;
	private JLabel chara;
	private JLabel stepsLeft;
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
		add(canvas, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public JPanel getCanvas() {
		return canvas;
	}

	/**
	 * get user input for the number of characters playing
	 *
	 * @return
	 */
	public int getNumPlayers() {
		Object[] options = { 2, 3, 4, 5, 6 };
		Object numPlayers = JOptionPane.showInputDialog(
				(JFrame) this,
				"How many players?",
				"Welcome to Cluedo!",
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]);
		if(numPlayers == null){System.exit(0);}//exit if canceled
		return (int) numPlayers;
	}

	/**
	 * set up and return the menu with its menu items
	 *
	 * @return
	 */
	private JMenu menu() {
		JMenu menu = new JMenu("File");

		JMenuItem help = new JMenuItem("Help");
		help.setAccelerator(KeyStroke.getKeyStroke('h'));
		help.setActionCommand("help");
		help.addActionListener(control);
		menu.add(help);

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke('n'));
		newGame.setActionCommand("new");
		newGame.addActionListener(control);
		menu.add(newGame);

		JMenuItem quit = new JMenuItem("Quit");
		quit.setAccelerator(KeyStroke.getKeyStroke('q'));
		quit.setActionCommand("quit");
		quit.addActionListener(control);
		menu.add(quit);

		return menu;
	}

	/**
	 * set up and return the tapped pane
	 *
	 * @return
	 */
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setPreferredSize(new Dimension(225, 500));
		tabPane.addTab("Main", mainPanel());
		tabPane.addTab("Cards", handPanel());
		tabPane.addTab("Checklist", checklistPanel());
		return tabPane;
	}

	/**
	 * set up and return the main tab panel
	 * @return
	 */
	private JPanel mainPanel() {
		JPanel main = new JPanel();

		JPanel labels = new JPanel(new GridLayout(3,1,5,5));
		nick = new JLabel("nickname");
		chara = new JLabel("character");
		stepsLeft = new JLabel();
		labels.add(nick);
		labels.add(chara);
		labels.add(stepsLeft);

		JPanel buttons = new JPanel(new GridLayout(3,1,5,5));
		buttons.setPreferredSize(new Dimension(200, 100));

		JButton sug = new JButton("Suggest");
		sug.setMnemonic(KeyEvent.VK_S);// actually alt + s
		sug.setToolTipText("You must be in the room you're suggesting to do this");
		sug.setActionCommand("suggest");
		sug.addActionListener(control);

		JButton acc = new JButton("Accuse");
		acc.setMnemonic(KeyEvent.VK_A);// actually alt + a
		acc.setToolTipText("Careful! If you're wrong, you'll be out of the game!");
		acc.setActionCommand("accuse");
		acc.addActionListener(control);

		JButton end = new JButton("End Turn");
		end.setMnemonic(KeyEvent.VK_E);// actually alt + e
		end.setToolTipText("End your turn");
		end.setActionCommand("end");
		end.addActionListener(control);

		buttons.add(sug);
		buttons.add(acc);
		buttons.add(end);

		main.add(labels);
		main.add(buttons);
		return main;
	}

	/**
	 * set up and return the checklist tab panel
	 *
	 * @return
	 */
	private JPanel checklistPanel() {
		JPanel checkPanel = new JPanel();
		checklist = new JTextArea("the checklist goes here");
		checklist.setEditable(false);
		checkPanel.add(checklist);
		return checkPanel;
	}

	/**
	 * set up and return the player hand tab panel
	 *
	 * @return
	 */
	private JPanel handPanel() {
		JPanel hand = new JPanel();
		cards = new JTextArea("the hand goes here");
		cards.setEditable(false);
		hand.add(cards);
		return hand;
	}

	/**
	 * updates what is displayed in the 'hand' tab
	 * @param text
	 */
	public void updateHand(String text){
		cards.setText(text);
	}

	/**
	 * updates what is displayed in the 'checklist' tab
	 * @param text
	 */
	public void updateChecklist(String text) {
		checklist.setText(text);
	}

	/**
	 * updates what is displayed on the 'nick' label
	 * @param newNick
	 */
	public void updateNick(String newNick){
		nick.setText(newNick);
	}

	/**
	 * updates what is displayed on the 'chara' label
	 * @param newChara
	 */
	public void updateChara(String newChara){
		chara.setText(newChara);
	}

	public void updateStepsLeft(int steps){
		stepsLeft.setText("Steps left: "+steps);
	}

	/**
	 * display a dialog box that asks the user to choose one of the given options
	 * and returns their choice
	 * @param title
	 * @param msg
	 * @param options
	 * @return
	 */
	public String guessDialog(String title, String msg, Object[] options) {
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

	/**
	 * displays a message of warning to the user, used for when they attempt an
	 * illegal action such as trying to suggest without being in a valid room
	 * @param msg
	 * @param title
	 */
	public void warningMessage(String msg, String title){
		JOptionPane.showMessageDialog((JFrame) this, msg, title, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * displays a message of the given msg string to the user, used to display finalised
	 * suggestions/accusations
	 * @param msg
	 * @param title
	 */
	public void infoMessage(String msg, String title){
		JOptionPane.showMessageDialog((JFrame) this, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void gameWonMessage(String winner){
		JOptionPane.showMessageDialog((JFrame) this,
				winner + " has solved it!",
				"Egads!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void playerLostMessage(String loser){
		JOptionPane.showMessageDialog((JFrame) this,
				loser + " is out of the game.",
				"What Poppycock!", JOptionPane.ERROR_MESSAGE);
	}

	public void gameLostMessage(){
		JOptionPane.showMessageDialog((JFrame) this,
				"You all suck at this. Game over I guess.",
				"Wow.", JOptionPane.ERROR_MESSAGE);
	}

	public boolean startNewGame(){
		Object [] options = {"Start sleuthing!", "Cancel"};
		int choice = JOptionPane.showOptionDialog(
				(JFrame) this,
				"Would you like to start a new game?",
				"Murder Most Foul!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == JOptionPane.YES_OPTION){
			return true;
		}else{
			return false;
		}
	}

}
