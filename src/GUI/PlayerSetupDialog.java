package GUI;

import javax.swing.*;

/**
 * Pop up dialog that allows players to choose their nickname and character
 * @author kraemezoe
 *
 */
public class PlayerSetupDialog extends JDialog {

	private Controller control;

	public PlayerSetupDialog(JFrame main, Controller control, ButtonGroup group){
		super(main, "Welcome to Cluedo!");

		this.control = control;
	}

}
