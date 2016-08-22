package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

/**
 * Pop up dialog that allows players to choose their nickname and character
 *
 * @author kraemezoe
 *
 */
public class PlayerSetupDialog extends JDialog {

	private ButtonGroup psdRadioGroup;
	private JPanel panel;
	private JTextField nickInput;

	private Controller control;

	public PlayerSetupDialog(JFrame frame, Controller control) {
		super(frame, "Welcome to Cluedo!");

		this.control = control;
		setUpPanel();

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 200));
		add(panel);
		pack();
		setVisible(true);
	}

	private void setUpPanel() {
		panel = new JPanel(new BorderLayout());

		nickInput = new JTextField("nickname (max 15 characters)");
		nickInput.addActionListener(control);
		panel.add(nickInput, BorderLayout.NORTH);

		JPanel rbs = new JPanel(new GridLayout(6, 1));

		psdRadioGroup = new ButtonGroup();
		JRadioButton newButt;
		for (String s : control.getAllCharacters()) {
			newButt = new JRadioButton(s);
			newButt.setActionCommand(s);
			psdRadioGroup.add(newButt);
			rbs.add(newButt);
		}

		panel.add(rbs, BorderLayout.CENTER);

		JButton confirm = new JButton("Confirm");
		confirm.setActionCommand("setUpPlayer");
		confirm.addActionListener(control);

		panel.add(confirm, BorderLayout.SOUTH);
	}

	/**
	 * returns up to the 15th character in the nickInput field
	 * @return
	 */
	public String returnNick() {
		String nick = nickInput.getText();
		if (nick.length()>15){
			return nick.substring(0, 14);
		}
		return nick;
	}

	public ButtonModel returnCharaSelection() {
		return psdRadioGroup.getSelection();
	}

	public void resetDialog() {
		psdRadioGroup.getSelection().setEnabled(false);
		psdRadioGroup.clearSelection();
		nickInput.setText("nickname (max 15 characters)");
	}

}
