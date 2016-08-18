package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

import main.Game;

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
	}

	private JMenu menu(){
		JMenu menu = new JMenu("File");
		menu.add(new JMenuItem("Help"));
		menu.add(new JMenuItem("Quit"));
		return menu;
	}

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
		sug.setMnemonic(KeyEvent.VK_S);//need key listener obvs
		sug.setActionCommand("suggest");
		sug.addActionListener(control);

		JButton acc = new JButton("Accuse");
		acc.setMnemonic(KeyEvent.VK_A);
		acc.setActionCommand("accuse");
		acc.addActionListener(control);

		test.add(sug);
		test.add(acc);
		test.add(new JButton("End Turn"));
		test.add(suspects());
		return test;
	}

	private JPanel checklistPanel(){
		JPanel checklist = new JPanel();
		checklist.add(new JTextArea("the checklist goes here"));
		return checklist;
	}

	private JPanel handPanel(){
		JPanel hand = new JPanel();
		hand.add(new JTextArea("the hand goes here"));
		return hand;
	}

	private JComboBox suspects(){
		String [] items = {"a", "b", "c"};
		JComboBox sus = new JComboBox(items);
		return sus;
	}

	public static void main(String args[]){
		new BoardFrame();
	}
}
