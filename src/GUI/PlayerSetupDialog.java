package GUI;

import javax.swing.*;

public class PlayerSetupDialog extends JDialog {

	private Controller control;

	public PlayerSetupDialog(JFrame main, Controller control, ButtonGroup group){
		super(main, "Welcome to Cluedo!");

		this.control = control;
	}

}
