package cards;

import java.util.*;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	public ArrayList<Card> characters = new ArrayList<>();
	public ArrayList<Card> rooms = new ArrayList<>();
	public ArrayList<Card> weapons = new ArrayList<>();

	public Deck() {
		setCards();
	}

	/**
	 * clears and repopulates the various card lists
	 */
	public void setCards(){
		characters.clear();
		rooms.clear();
		weapons.clear();
		deck.clear();

		characters.add(new CharacterCard("Miss Scarlett"));
		characters.add(new CharacterCard("Colonel Mustard"));
		characters.add(new CharacterCard("Mrs White"));
		characters.add(new CharacterCard("The Reverend Green"));
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

		deck.addAll(characters);
		deck.addAll(rooms);
		deck.addAll(weapons);
	}

	/**
	 * Picks 1 weapon 1 character 1	room as the solution
	 * @return arraylist of the solution
	 */
	public ArrayList<Card> setSolution(){
		ArrayList<Card> solution = new ArrayList<Card>();
		while(solution.isEmpty()){
			Card card= deal();
			if(card instanceof WeaponCard){
				solution.add(card);
			}
			else{deck.add(card);} //need to add card back to deck as getRandomCard removes it.
		}
		while(solution.size()==1){
			Card card= deal();
			if(card instanceof RoomCard){
				solution.add(card);
			}
			else{deck.add(card);}
		}
		while(solution.size()==2){
			Card card= deal();
			if(card instanceof CharacterCard){
				solution.add(card);
			}
			else{deck.add(card);}
		}
		return solution;

	}

	/**
	 * Deal one card randomly from deck
	 * @return
	 */
	public Card deal(){
		Random rand = new Random();
		int index=rand.nextInt(deck.size());
		Card card= deck.get(index);
		deck.remove(index);
		return card;

	}


	public ArrayList<Card> getDeck(){
		return deck;
	}
}
