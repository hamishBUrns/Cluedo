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
	 * set up the menu
	 *
	 * @return
	 */
	private JMenu menu() {
		JMenu menu = new JMenu("File");
		menu.add(new JMenuItem("Help"));
		menu.add(new JMenuItem("Quit"));
		return menu;
	}

	/**
	 * set up the tapped pane
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

	private JPanel mainPanel() {
		JPanel main = new JPanel();

		JPanel labels = new JPanel(new GridLayout(2,1,5,5));
		nick = new JLabel("nickname");
		chara = new JLabel("character");
		labels.add(nick);
		labels.add(chara);

		JPanel buttons = new JPanel(new GridLayout(3,1,5,5));
		buttons.setPreferredSize(new Dimension(200, 100));

		JButton sug = new JButton("Suggest");
		sug.setMnemonic(KeyEvent.VK_S);// actually alt + s
		sug.setActionCommand("suggest");
		sug.addActionListener(control);

		JButton acc = new JButton("Accuse");
		acc.setMnemonic(KeyEvent.VK_A);// actually alt + a
		acc.setActionCommand("accuse");
		acc.addActionListener(control);

		JButton end = new JButton("End Turn");
		end.setMnemonic(KeyEvent.VK_E);// actually alt + e
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
	 * set up the checklist tab panel
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
	 * set up the player hand tab panel
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

	public void updateNick(String newNick){
		nick.setText(newNick);
	}

	public void updateChara(String newChara){
		chara.setText(newChara);
	}

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

	public void warningMessage(String msg, String title){
		JOptionPane.showMessageDialog((JFrame) this, msg, title, JOptionPane.ERROR_MESSAGE);
	}

	public void infoMessage(String msg, String title){
		JOptionPane.showMessageDialog((JFrame) this, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void gameWonMessage(String winner){
		String msg = "Egads! " + winner + " has solved it!";
	}

}
