package game;

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

	public void addCard(Card c){
		if(c instanceof CharacterCard){
			characters.add((CharacterCard) c);
		}else if(c instanceof RoomCard){
			rooms.add((RoomCard) c);
		}else{
			weapons.add((WeaponCard) c);
		}
	}

	public void printChecklist(){
		System.out.println("//Checklist//");
		System.out.println("Characters:");
		printCheckedCharas();
		System.out.println("Rooms:");
		printCheckedRooms();
		System.out.println("Weapons:");
		printCheckedWeaps();
	}

	public void printCheckedCharas(){
		for(CharacterCard c : characters){
			System.out.println(c.getName());
		}
	}

	public void printCheckedRooms(){
		for(RoomCard r : rooms){
			System.out.println(r.getName());
		}
	}

	public void printCheckedWeaps(){
		for(WeaponCard w : weapons){
			System.out.println(w.getName());
		}
	}

	public boolean contains(Card c){
		return characters.contains(c) || rooms.contains(c) || weapons.contains(c);
	}

}
