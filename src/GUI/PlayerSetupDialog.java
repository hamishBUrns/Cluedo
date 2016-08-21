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

	public void setUpPanel() {
		panel = new JPanel(new BorderLayout());

		nickInput = new JTextField("select a nickname");
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

	public String returnNick() {
		return nickInput.getText();
	}

	public ButtonModel returnCharaSelection() {
		return psdRadioGroup.getSelection();
	}

	public void resetDialog() {
		psdRadioGroup.getSelection().setEnabled(false);
		psdRadioGroup.clearSelection();
		nickInput.setText("select a nickname");
	}

}
