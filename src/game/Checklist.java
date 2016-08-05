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
		getCheckedCharas();
		System.out.println("Rooms:");
		getCheckedRooms();
		System.out.println("Weapons:");
		getCheckedWeaps();
	}

	public void getCheckedCharas(){
		for(CharacterCard c : characters){
			System.out.println(c.getName());
		}
	}

	public void getCheckedRooms(){
		for(RoomCard r : rooms){
			System.out.println(r.getName());
		}
	}

	public void getCheckedWeaps(){
		for(WeaponCard w : weapons){
			System.out.println(w.getName());
		}
	}

}
