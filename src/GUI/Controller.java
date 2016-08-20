package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.JOptionPane;

import board.Player;
import main.Game;

/**
 * i'll do this later
 * @author kraemezoe
 *
 */
public class Controller implements ActionListener, KeyListener{

	Game game;
	BoardFrame view;
	PlayerSetupDialog playerSetup;

	public Controller(BoardFrame view, Game game){
		this.game = game;
		this.view = view;
	}

	/**
	 * for testing the view/controller interaction without worrying game logic yet
	 * @param view
	 */
	public Controller(BoardFrame view){
		this.view = view;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("suggest")){
			//do suggest things
			System.out.println(getSuspect());
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

	/**
	 * returns a list of name strings of all the characters in the game
	 * @return
	 */
	public List<String> getAllCharacters(){
		List<String> charas = new ArrayList<>();
		for(Player p : game.getPlayers()){
			charas.add(p.getName());
		}
		return charas;
	}

	public String getSuspect(){
		String[] options = {"is", "this","working", "?"};
		return view.suspectDialog(options);
	}

}
