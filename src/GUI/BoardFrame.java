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

	//STEP 1.5: get the board drawing again + the skeleton of the rest of the GUI

	//STEP 2: change move to use key listener

	//STEP 2.5: get end turn button to work

	//STEP 3: add dialogue pop-ups that the start of turns

	//STEP 4: get suggestion n accusation pop-ups/buttons working

	//STEP 5: figure out how we're doing the hands???

	//STEP 6: ???

	//STEP 7: profit

	Controller control;
	JMenuBar menuBar;
	JTextArea checklist;
	private ButtonGroup psdRadioGroup;

	public BoardFrame() {
		super("Cluedo");

		control = new Controller(this);
		menuBar = new JMenuBar();
		menuBar.add(menu());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setJMenuBar(menuBar);
		add(createTabbedPane());
		pack();
		setVisible(true);

		startGameSetup();
	}

	public void startGameSetup(){
		setUpPSDButtons();
		PlayerSetupDialog playerSetup = new PlayerSetupDialog(this, control, psdRadioGroup);
	}

	public void setUpPSDButtons(){
		psdRadioGroup = new ButtonGroup();
		for(String s : control.getAllCharacters()){
			JRadioButton newButt = new JRadioButton(s);
			newButt.setActionCommand(s);
			psdRadioGroup.add(newButt);
		}
	}

	public void updatePSDOptions(){
		psdRadioGroup.getSelection().setEnabled(false);
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
		end.setMnemonic(KeyEvent.VK_E);
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
		hand.add(new JTextArea("the hand goes here"));
		return hand;
	}

	public void updateChecklist(String text){
		checklist.setText(text);
	}

	public String suspectDialog(String [] options){
		//JComboBox<String> sus = new JComboBox<String>(options);
		String s = (String) JOptionPane.showInputDialog(
				(JFrame) this,
				"Who dunnit?",
				"u talkin to ME??",
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		return s;
	}

	public static void main(String args[]){
		new BoardFrame();
	}
}
