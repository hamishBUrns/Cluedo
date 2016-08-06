package game;

import java.util.*;

public class Room {
	private String name;
	private ArrayList<Token> pieces;
	private ArrayList<Tile> tiles;
	private Map<String, Tile> doors;
	private int index;

	public Room(String name) {
		tiles = new ArrayList<>();
		pieces = new ArrayList<>();
		doors = new HashMap<>();
		this.name=name;
	}
	public void addTile(Tile t){
		tiles.add(t);
	}

	public void addDoors(Map<String, Tile> d){
		doors.putAll(d);
	}

	public Map<String, Tile> getDoors(){
		return doors;
	}

	public void putInRoom(Token token){
		pieces.add(token);
		Tile place= tiles.get(pieces.indexOf(token));

		place.setToken(token);
		token.setRow(place.getRow());
		token.setCol(place.getCol());

	}
	public void takeFromRoom(Token token){
		Tile place=tiles.get(pieces.indexOf(token));
		place.setToken(null);
		pieces.remove(token);
		//need to move Token col and row in outside method depending on where its going
	}
	public String getName(){
		return this.name;
	}
	public List<Token> getTokens(){
		return pieces;
	}

	public List<Tile> getTiles(){
		return tiles;
	}

}
