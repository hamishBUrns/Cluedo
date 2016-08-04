package cards;

import java.util.*;

public class Deck {
	public ArrayList<Card> deck = new ArrayList<Card>();
	public ArrayList<Card> characters = new ArrayList<>();
	public ArrayList<Card> rooms = new ArrayList<>();
	public ArrayList<Card> weapons = new ArrayList<>();

	public Deck() {
		setCards();

	}
	public void setCards(){
		characters.add(new CharacterCard("Miss Scarlett"));
		characters.add(new CharacterCard("Colonel Mustard"));
		characters.add(new CharacterCard("Mrs White"));
		characters.add(new CharacterCard("The Reverend green"));
		characters.add(new CharacterCard("Mrs Peacock"));
		characters.add(new CharacterCard("Professor Plum"));

		rooms.add(new RoomCard("Kitchen"));
		rooms.add(new RoomCard("Ballroom"));
		rooms.add(new RoomCard("Conservatory"));
		rooms.add(new RoomCard("Billiard room"));
		rooms.add(new RoomCard("Library"));
		rooms.add(new RoomCard("Study"));
		rooms.add(new RoomCard("Hall"));
		rooms.add(new RoomCard("Lounge"));
		rooms.add(new RoomCard("Dining room"));

		weapons.add(new WeaponCard("Candlestick"));
		weapons.add(new WeaponCard("Dagger"));
		weapons.add(new WeaponCard("Lead Pipe"));
		weapons.add(new WeaponCard("Revolver"));
		weapons.add(new WeaponCard("Rope"));
		weapons.add(new WeaponCard("Spanner"));

		for(Card c: characters){
			deck.add(c);
		}
		for(Card c: rooms){
			deck.add(c);
		}
		for(Card c: weapons){
			deck.add(c);
		}
	}
	public ArrayList<Card> setSolution(){
		ArrayList<Card> solution = new ArrayList<Card>();
		solution.add(getRandomCard(characters));
		solution.add(getRandomCard(rooms));
		solution.add(getRandomCard(weapons));
		return solution;

	}
	public Card getRandomCard(ArrayList<Card> set){
		Random rand = new Random();
		int index=rand.nextInt(set.size());
		Card card= set.get(index);
		set.remove(index);
		return card;
		//only removes from one set but you have those two sets.
	}
}
