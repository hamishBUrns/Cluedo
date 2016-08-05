package game;

import java.util.*;

public class Room {
	private String name;
	private ArrayList<Token> pieces;
	private ArrayList<Tile> tiles;

	public Room(String name) {
		pieces= new ArrayList<Token>();
		tiles=new ArrayList<Tile>();
		this.name=name;
	}
	public void addTile(Tile t){
		tiles.add(t);
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
