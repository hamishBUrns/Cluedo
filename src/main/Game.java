package main;

import java.util.*;

import cards.*;
import game.*;

public class Game {

	ArrayList<Player> players = new ArrayList<Player>();
	TextClient client;
	Deck deck;
	Board board;
	boolean gameStillGoing;

	public Game(TextClient c) {
		client = c;
		deck = new Deck();
		gameStillGoing = true;
		int numPlayers = client.readInt("How many players?");
		while (numPlayers > 6 || numPlayers < 2) {
			numPlayers = client.readInt("Must have between 2 and 6 players");
		}
		while (numPlayers > 0) {
			// add players
		}
		board = new Board();

		while (gameStillGoing){
			for(Player p : players){
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

	/*
	 * gets user input to move them around the board
	 */
	public void move(int diceRoll, Player p) {
		while (diceRoll > 0) {
			System.out.println("Steps left: " + diceRoll);
			String dir = client.readString("Choose a direction");
			switch (dir) {
			case ("N"):
				// go north
				break;
			case ("S"):
				// go south
				break;
			case ("E"):
				// go east
				break;
			case ("W"):
				// go west
				break;
			default:
				System.out.println("Invalid input. Please use one of the following: N, S, E, W");
				diceRoll++; //so steps left don't go down if you didn't move
			}
			diceRoll--;
			// check if player is in a room
			// if yes return?? i'm guessing here
		}
		return;
	}

	public void suggest(RoomCard room, Player p){
		Card c = cardFromString(client.readString("Who dunnit?"));
		while(c == null || !(c instanceof CharacterCard)){
			c = cardFromString(client.readString("Please input the name of a valid character"));
		}
		Card w = cardFromString(client.readString("Murder weapon?"));
		while(w == null || !(w instanceof WeaponCard)){
			w = cardFromString(client.readString("Please input the name of a valid weapon"));
		}
		System.out.println("Perhaps it was "+c.getName()+" in the "+room.getName()+" with the "+w.getName());
	}

	public void accuse(Player p){
		Card c = cardFromString(client.readString("Who dunnit?"));
		while(c == null || !(c instanceof CharacterCard)){
			c = cardFromString(client.readString("Please input the name of a valid character"));
		}
		Card r = cardFromString(client.readString("Scene of the crime?"));
		while(r == null || !(r instanceof RoomCard)){
			r = cardFromString(client.readString("Please input the name of a valid room"));
		}
		Card w = cardFromString(client.readString("Murder weapon?"));
		while(w == null || !(w instanceof WeaponCard)){
			w = cardFromString(client.readString("Please input the name of a valid weapon"));
		}
		System.out.println("It was "+c.getName()+" in the "+r.getName()+" with the "+w.getName()+"!");
	}

	public Card cardFromString(String s){
		for(Card c : deck.deck){
			if(c.getName().equalsIgnoreCase(s)){
				return c;
			}
		}
		return null;
	}

}
