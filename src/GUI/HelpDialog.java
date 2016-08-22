package GUI;

import javax.swing.*;

public class HelpDialog extends JDialog {

	public HelpDialog(JFrame frame){
		super(frame, "Help");

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		createTabbedPanel();
		pack();
		setVisible(true);
	}

	private void createTabbedPanel(){
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("Main", mainHelp());
		tabPane.addTab("Move", moveHelp());
		tabPane.addTab("Suggest", sugHelp());
		tabPane.add("Accuse", accHelp());
		add(tabPane);
	}

	private JPanel mainHelp(){
		JPanel main = new JPanel();
		String s = "There's been a murder in the manor!\n\n"
				+ "By visiting rooms and making suggestions, you must solve the \n"
				+ "mystery and uncover who the murderer is, the scene of the crime, \n"
				+ "and the murder weapon.\n\n"
				+ "Your checklist keeps track of all the cards you've seen (and \n"
				+ "therefore cannot be part of the solution), and can be found in the \n"
				+ "'Checklist' tab on the right panel. You can see what cards are in \n"
				+ "your hand under the 'Cards' tab\n\n"
				+ "To short cut to a button, hold alt and the underlined letter keys.\n"
				+ "Press N to start a new game, Q to quit, and H for help.";
		main.add(new JTextArea(s));
		return main;
	}

	private JPanel moveHelp(){
		JPanel move = new JPanel();
		String s = "Use the arrow keys to move around the board (be sure to move the \n"
				+ "cursor within the board area first). The number of steps you have \n"
				+ "left is displayed on the main tab on the right. \n\n"
				+ "To exit a room, click on one of the 'door' squares OR on the secret\n"
				+ "staircase square of the **opposite corner room** (that is, the room \n"
				+ "you will end up in).\n\n"
				+ "Board square key: \n"
				+ "White: room\n"
				+ "Yellow: hallway\n"
				+ "Orange: door\n"
				+ "Magenta: secret staircase\n"
				+ "Black: wall\n";
		move.add(new JTextArea(s));
		return move;
	}

	private JPanel sugHelp(){
		JPanel sug = new JPanel();
		String s = "When in a room, you can make a suggestion as to what might have \n"
				+ "happened. If another player has a card in their hand that was used \n"
				+ "in your suggestion, then the card is announced and the suggestion \n"
				+ "is refuted.\n\n";
		sug.add(new JTextArea(s));
		return sug;
	}

	private JPanel accHelp(){
		JPanel acc = new JPanel();
		String s = "If you think you know the solution you can make an accusation, \n"
				+ "which can be made from anywhere at any time. But be careful! \n"
				+ "Unlike suggestions, if you make a false accusation you will be \n"
				+ "booted from the game.";
		acc.add(new JTextArea(s));
		return acc;
	}
}
