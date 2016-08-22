package main;

import java.util.*;

import cards.*;

public class Checklist {

	private List<CharacterCard> characters;
	private List<RoomCard> rooms;
	private List<WeaponCard> weapons;

	public Checklist() {
		// TODO Auto-generated constructor stub
		weapons = new ArrayList<>();
		rooms = new ArrayList<>();
		characters = new ArrayList<>();
	}

	/**
	 * add a card to this checklist into the appropriate sub-list
	 * @param c
	 */
	public void addCard(Card c){
		if(c instanceof CharacterCard){
			characters.add((CharacterCard) c);
		}else if(c instanceof RoomCard){
			rooms.add((RoomCard) c);
		}else{
			weapons.add((WeaponCard) c);
		}
	}

	/**
	 * returns true if any of the character, room, or weapon lists contain the given card
	 * @param c
	 * @return
	 */
	public boolean contains(Card c){
		return characters.contains(c) || rooms.contains(c) || weapons.contains(c);
	}

}
