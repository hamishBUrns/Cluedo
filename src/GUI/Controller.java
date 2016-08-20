package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.JOptionPane;

import board.Player;
<<<<<<< HEAD
import board.Tile;
import main.Game;
import main.TextClient;
=======
import main.Game;
>>>>>>> 3211853901dd5c05f14444ba8bc7b587b6ebabd6

/**
 * i'll do this later
 * @author kraemezoe
 *
 */
public class Controller implements ActionListener, KeyListener{

	Game game;
	BoardFrame view;
	PlayerSetupDialog playerSetup;

<<<<<<< HEAD
	public Controller(){
		this.game = new Game(new TextClient(),3);
		this.view = new BoardFrame(this);
=======
	public Controller(BoardFrame view, Game game){
		this.game = game;
		this.view = view;
>>>>>>> 3211853901dd5c05f14444ba8bc7b587b6ebabd6
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

<<<<<<< HEAD
	public Tile[][] getTiles(){
		for(Tile[] rows:game.getBoard().getTiles()){
			for(Tile t: rows ){
				System.out.print(t.getType());
			}
			System.out.println();
		}
		return game.getBoard().getTiles();
	}

=======
>>>>>>> 3211853901dd5c05f14444ba8bc7b587b6ebabd6
	public String getSuspect(){
		String[] options = {"is", "this","working", "?"};
		return view.suspectDialog(options);
	}

<<<<<<< HEAD
	public static void main(String args[]){
		new Controller();
	}
=======
>>>>>>> 3211853901dd5c05f14444ba8bc7b587b6ebabd6
}
