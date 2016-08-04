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
		while(solution.isEmpty()){
			Card card= getRandomCard(deck);
			if(card instanceof WeaponCard){
				solution.add(card);
			}
			else{deck.add(card);}
		}
		while(solution.size()==1){
			Card card= getRandomCard(deck);
			if(card instanceof RoomCard){
				solution.add(card);
			}
			else{deck.add(card);}
		}
		while(solution.size()==2){
			Card card= getRandomCard(deck);
			if(card instanceof CharacterCard){
				solution.add(card);
			}
			else{deck.add(card);}
		}
		return solution;

	}
	public Card deal(){
		if(deck.size()==0){
			System.out.println("no cards in deck");
			return null;
		}
		return getRandomCard(deck);

	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	public Card getRandomCard(ArrayList<Card> set){
		Random rand = new Random();
		int index=rand.nextInt(set.size());
		Card card= set.get(index);
		set.remove(index);
		return card;

	}
}
