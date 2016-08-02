package cards;

import java.util.*;

public class Deck {
	public ArrayList<Card> deck = new ArrayList<Card>();
	public List<Card> characters = new ArrayList<>();
	public List<Card> rooms = new ArrayList<>();
	public List<Card> weapons = new ArrayList<>();

	public Deck() {
		setCards();

	}
	public void setCards(){
		deck.add(new CharacterCard("Miss Scarlett"));
		deck.add(new CharacterCard("Colonel Mustard"));
		deck.add(new CharacterCard("Mrs White"));
		deck.add(new CharacterCard("The Reverend green"));
		deck.add(new CharacterCard("Mrs Peacock"));
		deck.add(new CharacterCard("Professor Plum"));

		deck.add(new RoomCard("Kitchen"));
		deck.add(new RoomCard("Ballroom"));
		deck.add(new RoomCard("Conservatory"));
		deck.add(new RoomCard("Billiard room"));
		deck.add(new RoomCard("Library"));
		deck.add(new RoomCard("Study"));
		deck.add(new RoomCard("Hall"));
		deck.add(new RoomCard("Lounge"));
		deck.add(new RoomCard("Dining room"));

		deck.add(new WeaponCard("Candlestick"));
		deck.add(new WeaponCard("Dagger"));
		deck.add(new WeaponCard("Lead Pipe"));
		deck.add(new WeaponCard("Revolver"));
		deck.add(new WeaponCard("Rope"));
		deck.add(new WeaponCard("Spanner"));
	}
}
