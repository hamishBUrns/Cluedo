package main;

import java.util.*;

import cards.*;
import game.*;

public class Game {

	private ArrayList<Player> players = new ArrayList<Player>();
	private TextClient client;
	private Deck deck;
	private Board board;
	private int turnIndex;
	private boolean gameStillGoing;

	public Game(TextClient c, int numPlayers) {
		client = c;
		deck = new Deck();
		gameStillGoing = true;
		while (numPlayers > 0) {
			// add players
			numPlayers--;
		}
		board = new Board();
	}

	public void runGame(){
		while (gameStillGoing) {
			for (Player p : players) {
				turn(p);
			}
		}
	}

	public void turn(Player p) {
		Random rand = new Random();
		int diceRoll = rand.nextInt(11) + 2; // generate a random number between 2 and 12

		move(diceRoll, p);
		// suggestion/accusation mechanics go here

	}

	/**
	 * gets user input to move them around the board
	 */
	public void move(int diceRoll, Player p) {
		while (diceRoll > 0) {
			System.out.println("Steps left: " + diceRoll);
			String dir = client.readString("Choose a direction").toUpperCase();
			switch (dir) {
			case ("N"):
				// go north
				if(board.moveValid(p.getRow(), p.getCol(), p.getRow()+1, p.getCol(), p)){
					diceRoll--;
				}
				break;
			case ("S"):
				// go south
				if(board.moveValid(p.getRow(), p.getCol(), p.getRow()-1, p.getCol(), p)){
					diceRoll--;
				}
				break;
			case ("E"):
				// go east
				if(board.moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol()+1, p)){
					diceRoll--;
				}
				break;
			case ("W"):
				// go west
				if(board.moveValid(p.getRow(), p.getCol(), p.getRow(), p.getCol()-1, p)){
					diceRoll--;
				}
				break;
			default:
				System.out.println("Invalid input. Please use one of the following: N, S, E, W");
			}
			// check if player is in a room
			// if yes return?? i'm guessing here
		}
		return;
	}

	/**
	 * get input for the weapon and character for this suggestion from the user
	 * @param room
	 * @param p
	 */
	public void suggest(Card room, Player p) {
		Card c = cardFromString(client.readString("Who dunnit?"));
		while (c == null || !(c instanceof CharacterCard)) {
			c = cardFromString(client.readString("Please input the name of a valid character"));
		}
		Card w = cardFromString(client.readString("Murder weapon?"));
		while (w == null || !(w instanceof WeaponCard)) {
			w = cardFromString(client.readString("Please input the name of a valid weapon"));
		}
		System.out.println("Perhaps it was " + c.getName() + " in the " + room.getName() + " with the " + w.getName());
	}

	/**
	 * get input for the room, weapon, and character for this accusation from the user
	 * @param p
	 */
	public void accuse(Player p) {
		Card c = cardFromString(client.readString("Who dunnit?"));
		while (c == null || !(c instanceof CharacterCard)) {
			c = cardFromString(client.readString("Please input the name of a valid character"));
		}
		Card r = cardFromString(client.readString("Scene of the crime?"));
		while (r == null || !(r instanceof RoomCard)) {
			r = cardFromString(client.readString("Please input the name of a valid room"));
		}
		Card w = cardFromString(client.readString("Murder weapon?"));
		while (w == null || !(w instanceof WeaponCard)) {
			w = cardFromString(client.readString("Please input the name of a valid weapon"));
		}
		System.out.println("It was " + c.getName() + " in the " + r.getName() + " with the " + w.getName() + "!");
	}

	/**
	 * if suggestion is refuted, returns that card, otherwise returns null
	 */
	public Card refute(List<Card> suggested) {
		int current = turnIndex + 1;
		while (current != turnIndex) {
			for (Card c : players.get(current).getHand()) {
				for (Card s : suggested) {
					if (c.equals(s)) {
						return c;
					}
				}
			}
			current++;
			if (current == players.size()) {
				current = 0;
			}
		}
		return null;
	}

	/**
	 * return the card whose name matches the input string, null if no match found
	 * @param s
	 * @return card
	 */
	public Card cardFromString(String s) {
		for (Card c : deck.deck) {
			if (c.getName().equalsIgnoreCase(s)) {
				return c;
			}
		}
		return null;
	}

	public static void main(String args[]) {
		TextClient client = new TextClient();

		int numPlayers = client.readInt("How many players?");
		while (numPlayers > 6 || numPlayers < 2) {
			numPlayers = client.readInt("Must have between 2 and 6 players");
		}

		Game game = new Game(client, numPlayers);
		game.runGame();
	}

}
