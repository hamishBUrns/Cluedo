package GUI;

import java.awt.*;
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

	JMenuBar menuBar;

	public BoardFrame() {
		super("Cluedo");

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
		test.add(new JButton("Suggest"));
		test.add(new JButton("Accuse"));
		test.add(new JButton("End Turn"));
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

	public static void main(String args[]){
		new BoardFrame();
	}
}
