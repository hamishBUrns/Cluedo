package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

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

}
