package GUI;

import javax.swing.*;

public class HelpDialog extends JDialog {

	private Controller control;

	public HelpDialog(JFrame frame, Controller control){
		super(frame, "Help");

		this.control = control;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void createTabbedPanel(){
		JTabbedPane tabPane = new JTabbedPane();
		
	}

	private JPanel mainPanel(){
		JPanel main = new JPanel();
		main.add(new JTextArea());
		return main;
	}

	private String moveHelp(){
		String s = "Use the arrow keys to move around the board (be sure to move the \n"
				+ "cursor within the board area first). The number of steps you have \n"
				+ "left is displayed on the main tab on the right. \n"
				+ "To exit a room, click on one of the 'door' squares OR on the secret\n"
				+ "staircase square of the **opposite corner room** (that is, the room \n"
				+ "you will end up in).\n\n"
				+ "Board square key: \n"
				+ "White: room, yellow: hall, orange: door, magenta: secret staircase\n"
				+ "black: wall.\n";
		return s;
	}

}
