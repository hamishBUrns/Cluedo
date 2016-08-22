package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.ButtonModel;
import board.Player;

import board.Tile;
import main.Game;
import main.TextClient;

/**
 * i'll do this later
 *
 * @author kraemezoe
 *
 */
public class Controller implements MouseListener, ActionListener, KeyListener {

	Game game;
	BoardFrame view;
	PlayerSetupDialog playerSetup;
	int numPlayers;

	public Controller() {
		this.game = new Game();
		this.view = new BoardFrame(this);
		doGameSetup();
	}

	public Controller(BoardFrame view, Game game) {
		this.game = game;
		this.view = view;

	}

	/**
	 * for testing the view/controller interaction without worrying game logic
	 * yet
	 *
	 * @param view
	 */
	public Controller(BoardFrame view) {
		this.view = view;

	}

	public void doGameSetup() {
		numPlayers = view.getNumPlayers();
		playerSetup = new PlayerSetupDialog(view, this);
	}

	public void finishGameSetup() {
		game.dealCards();
		game.setCurrentPlayer();
		updateView();
		playerSetup.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("setUpPlayer")) {
			ButtonModel chara = playerSetup.returnCharaSelection();
			String nick = playerSetup.returnNick();
			if (chara != null && !nick.isEmpty()) {
				game.addPlayer(game.playerFromString(chara.getActionCommand()), nick);
				playerSetup.resetDialog();
				numPlayers--;
			}
			if (numPlayers == 0) {
				finishGameSetup();
			}
		}

		if (e.getActionCommand().equals("suggest")) {
//			// do suggest things
//			// System.out.println(getSuspect());
//			if (game.canSuggest()) {
//				String s = getSuspect();
//				if (s == null) {
//					return;
//				}
//				String w = getMurderWeapon();
//				if (w == null) {
//					return;
//				}
//				String r = game.currentRoomName();
//				view.infoMessage("\"Perhaps it was " + s + " in the " + r + " with the " + w + "?\"",
//						game.getCurrentNick() + ":");
//				String result = game.refute(game.suggest(s, w));
//				if (result == null) {
//					// game won stuff
//				} else {
//					// refuted dialog
//					view.infoMessage("There's irrefutable proof that '" + result + "' was not involved.", "But Wait!");
//					game.endTurn();
//					updateView();
//				}
//			} else {
//				view.warningMessage("You must be inside a valid room to suggest", "Uh Oh!");
//			}
		} else if (e.getActionCommand().equals("accuse")) {
			// do accuse things
			String s = getSuspect();
			if (s == null) {
				return;
			}
			String r = getCrimeScene();
			if (r == null) {
				return;
			}
			String w = getMurderWeapon();
			if (w == null) {
				return;
			}
			game.accusationCorrect(s, r, w);
		} else if (e.getActionCommand().equals("end")) {
			game.endTurn();
			updateView();
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
		int id = e.getKeyCode();
		switch (id) {
		case (KeyEvent.VK_DOWN):
			game.tryMove("S");
			break;
		case (KeyEvent.VK_LEFT):
			game.tryMove("W");
			break;
		case (KeyEvent.VK_RIGHT):
			game.tryMove("E");
			break;
		case (KeyEvent.VK_UP):

			game.tryMove("N");
			break;
		default:
			System.out.println("Not an option");
		}
		view.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		view.findComponentAt(e.getPoint()).requestFocus();

		System.out.println("x is:"+e.getX()+"y is:"+e.getY()+"width is:"+view.getCanvas().getWidth());
		int col = pointToPos(e.getX());
		int row = pointToPos(e.getY());
		System.out.println("row, col"+row+","+col);
		if(0<col&&col<26&&0<row&&row<26){
			game.tryLeaveRoom(row, col);
		}
		else {
			System.out.println("outside of canvas");
		}
		view.repaint();

	}

	private int pointToPos(int point) {
		return point / (view.getCanvas().getWidth() / 25);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * returns a list of name strings of all the characters in the game
	 *
	 * @return
	 */
	public List<String> getAllCharacters() {
		return game.allCharaNames();
	}

	public Tile[][] getTiles() {
		return game.getBoard().getTiles();
	}

	public Tile getTile(int row, int col) {
		return game.getBoard().getTile(row, col);
	}

	/**
	 * gets a list of characters and calls the view to display a dialog box
	 * asking the user to choose one, which is then returned
	 *
	 * @return
	 */
	public String getSuspect() {
		Object[] options = game.allValidCharaNames().toArray();
		return view.guessDialog("Choose a character", "Who dunnit?", options);
	}

	/**
	 * gets a list of rooms and calls the view to display a dialog box asking
	 * the user to choose one, which is then returned
	 *
	 * @return
	 */
	public String getCrimeScene() {
		Object[] options = game.allValidRoomNames().toArray();
		return view.guessDialog("Choose a room", "Scene of the crime?", options);
	}

	/**
	 * gets a list of weapons and calls the view to display a dialog box asking
	 * the user to choose one, which is then returned
	 *
	 * @return
	 */
	public String getMurderWeapon() {
		Object[] options = game.allValidWeapNames().toArray();
		return view.guessDialog("Choose a weapon", "Murder weapon?", options);
	}

	/**
	 * updates the view's checklist to display that of the current player
	 */
	public void updateView() {
		view.updateNick(game.getCurrentNick());
		view.updateChara(game.getCurrentChara());
		view.updateChecklist(game.currentPlayerChecklist());
		view.repaint();
	}

	public void printBoard() {
		game.getBoard().printBoard();
	}

	public static void main(String args[]) {
		new Controller();
	}

}
