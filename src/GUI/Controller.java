package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.ButtonModel;
import board.Tile;
import main.Game;

/**
 *Controller to connect view and model (boardframe and game)
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

	/**
	 * creates a new Game, calls for dialog to get the number of players,
	 * and creates a new PlayerSetupDialog to set up the players
	 */
	public void doGameSetup() {
		numPlayers = view.getNumPlayers();
		playerSetup = new PlayerSetupDialog(view, this);
	}

	/**
	 * completes the game set up by calling to deal cards and set the current player
	 * updates the view and disposes of the player setup dialog
	 */
	public void finishGameSetup() {
		game.dealCards();
		game.setCurrentPlayer();
		updateView();
		playerSetup.dispose();
		playerSetup = null;
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

		}else{
			if (e.getActionCommand().equals("suggest")) {
				// do suggest things
				suggest();
			} else if (e.getActionCommand().equals("accuse")) {
				// do accuse things
				accuse();
			} else if (e.getActionCommand().equals("end")) {
				game.endTurn();
			} else if (e.getActionCommand().equals("help")){
				new HelpDialog(view);
			} else if (e.getActionCommand().equals("new")){
				if(view.startNewGame()){
					//make a new Game object and do the set up
					game = new Game();
					doGameSetup();
					return;
				}
			} else if (e.getActionCommand().equals("quit")){
				System.out.println("leave");
			}
			updateView();
		}
	}

	/**
	 * Obtains strings of the suggested cards from the user to be checked by the game logic
	 * and displays the finalised suggestion. Calls to display the appropriate dialog depending
	 * on whether the suggestion is correct
	 */
	public void suggest() {
		if (game.canSuggest()) {
			// get strings for suggested character & weapon
			// if null, player has canceled the action, so return without doing
			// anything
			String s = getSuspect();
			if (s == null) {
				return;
			}
			String w = getMurderWeapon();
			if (w == null) {
				return;
			}
			String r = game.currentRoomName();
			view.infoMessage("\"Perhaps it was " + s + " in the " + r + " with the " + w + "?\"",
					game.getCurrentNick() + ":");
			String result = game.refute(game.suggest(s, w));
			if (result == null) {
				// game won stuff
				view.gameWonMessage(game.getCurrentNick());
			} else {
				// refuted dialog
				view.infoMessage("There's irrefutable proof that '" + result + "' was not involved.", "But Wait!");
				game.endTurn();
				// updateView();
			}
		} else {
			view.warningMessage("You must be inside a valid room to suggest", "Uh Oh!");
		}
	}

	/**
	 * Obtains strings of the accused cards from the user to be checked by the game logic
	 * and displays the finalised accusation. Calls to display the appropriate dialog depending
	 * on whether the accusation is correct
	 */
	public void accuse() {
		// get strings for accused character, room, & weapon
		// if null, player has canceled the action, so return without doing
		// anything
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
		view.infoMessage("\"It was " + s + " in the " + r + " with the " + w + "!\"",
				game.getCurrentNick() + ":");
		if (game.accusationCorrect(s, r, w)) {
			// game is won
			view.gameWonMessage(game.getCurrentNick());
		} else {
			// player has lost
			game.endTurn();
			view.playerLostMessage(game.getCurrentNick());
			if (!game.playersLeft()) {
				// if no one is left in the game, game is lost
				view.gameLostMessage();
			}
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
		if (playerSetup != null) {
			return;
		}
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
			//System.out.println("Not an option");
		}
		view.updateStepsLeft(game.getDiceRoll());
		view.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (playerSetup != null || game.getDiceRoll() == 0) {
			return;
		}
		view.findComponentAt(e.getPoint()).requestFocus();

		int col = pointToPos(e.getX());
		int row = pointToPos(e.getY());
		if (0 < col && col < 26 && 0 < row && row < 26) {
			game.tryLeaveRoom(row, col);
		} else {
			//System.out.println("outside of canvas");
		}
		updateView();
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
		view.findComponentAt(e.getPoint()).requestFocus();

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

	/**
	 * returns the game board's array of Tiles
	 * @return
	 */
	public Tile[][] getTiles() {
		return game.getBoard().getTiles();
	}

	/**
	 * returns the tile at the given row and column
	 * @param row
	 * @param col
	 * @return
	 */
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
	 * updates the view's display to reflect any changes that have occurred in
	 * the game
	 */
	public void updateView() {
		view.updateNick(game.getCurrentNick());
		view.updateChara(game.getCurrentChara());
		view.updateStepsLeft(game.getDiceRoll());
		view.updateHand(game.currentPlayerHand());
		view.updateChecklist(game.currentPlayerChecklist());
		view.repaint();
	}

	public void printBoard() {
		// game.getBoard().printBoard();
	}

	public static void main(String args[]) {
		new Controller();
	}

}
