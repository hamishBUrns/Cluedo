package GUI;

import java.awt.event.*;

public class Controller implements ActionListener, KeyListener{

	BoardFrame view;

	public Controller(BoardFrame view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("suggest")){
			//do suggest things
		}else if(e.getActionCommand().equals("accuse")){
			//do accuse things
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
