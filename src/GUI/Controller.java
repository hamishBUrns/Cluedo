package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.JOptionPane;

import board.Player;

import board.Tile;
import main.Game;
import main.TextClient;


/**
 * i'll do this later
 * @author kraemezoe
 *
 */
public class Controller implements ActionListener, KeyListener{

	Game game;
	BoardFrame view;
	PlayerSetupDialog playerSetup;

	public Controller(){
		this.game = new Game(new TextClient());
		this.view = new BoardFrame(this);
		doPlayerSetupView();
	}

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

	public void doPlayerSetupView(){
		playerSetup = new PlayerSetupDialog(view, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("playerSetup")){
			if(playerSetup.returnChara() != null && !playerSetup.returnNick().isEmpty()){
				System.out.println(playerSetup.returnNick());
				System.out.println(playerSetup.returnChara());
				playerSetup.resetDialog();
			}

		}
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
		int id = e.getID();
		System.out.println("got here?");
		switch(id){
		case(KeyEvent.VK_KP_DOWN):
			game.tryMove("S");
			break;
		case(KeyEvent.VK_KP_LEFT):
			game.tryMove("W");
			break;
		case(KeyEvent.VK_KP_RIGHT):
			game.tryMove("E");
			break;
		case(KeyEvent.VK_KP_UP):
			game.tryMove("N");
			break;
		}

	}

	/**
	 * returns a list of name strings of all the characters in the game
	 * @return
	 */
	public List<String> getAllCharacters(){
		List<String> charas = new ArrayList<>();
		for(Player p : game.getAllCharas()){
			charas.add(p.getName());
		}
		return charas;
	}

	public Tile[][] getTiles(){
		return game.getBoard().getTiles();
	}

	public Tile getTile(int row, int col){
		return game.getBoard().getTile(row, col);
	}

	public String getSuspect(){
		String[] options = {"is", "this","working", "?"};
		return view.suspectDialog(options);
	}


	public void printBoard(){
		game.getBoard().printBoard();
	}

	public static void main(String args[]){
		new Controller();
	}

}
