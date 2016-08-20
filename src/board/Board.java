package board;

import java.util.*;

public class Board {

	private Tile[][] tiles;

	private ArrayList<Room> rooms;
	private String[] hardCodedBoard={
			"|W|W|W|W|W|W|W|W|W|S|W|W|W|W|W|S|W|W|W|W|W|W|W|W|W|",
			"|k|k|k|k|k|k|W|_|_|_|b|b|b|b|b|_|_|_|W|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|D|b|b|b|b|b|b|b|b|b|D|_|D|c|c|c|c|W|",
			"|W|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|_|_|_|_|_|S|",
			"|_|_|_|_|D|_|_|_|b|b|b|b|b|b|b|b|b|_|_|_|_|_|_|_|W|",
			"|W|_|_|_|_|_|_|_|_|D|_|_|_|_|_|D|_|_|_|r|r|r|r|r|r|",
			"|d|d|d|d|d|_|_|_|_|_|_|_|_|_|_|_|_|_|D|r|r|r|r|r|r|",
			"|d|d|d|d|d|d|d|d|_|_|W|W|W|W|W|W|_|_|_|r|r|r|r|r|r|",
			"|d|d|d|d|d|d|d|d|_|_|W|W|W|W|W|W|_|_|_|r|r|r|r|r|r|",
			"|d|d|d|d|d|d|d|d|D|_|W|W|W|W|W|W|_|_|_|r|r|r|r|r|r|",
			"|d|d|d|d|d|d|d|d|_|_|W|W|W|W|W|W|_|_|_|_|_|D|_|D|W|",
			"|d|d|d|d|d|d|d|d|_|_|W|W|W|W|W|W|_|_|_|l|l|l|l|l|W|",
			"|d|d|d|d|d|d|d|d|_|_|W|W|W|W|W|W|_|_|l|l|l|l|l|l|l|",
			"|W|_|_|_|_|_|D|_|_|_|W|W|W|W|W|W|_|D|l|l|l|l|l|l|l|",
			"|S|_|_|_|_|_|_|_|_|_|_|D|D|D|_|_|_|_|l|l|l|l|l|l|l|",
			"|W|_|_|_|_|_|D|_|_|h|h|h|h|h|h|h|_|_|_|l|l|l|l|l|W|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|_|_|_|_|_|S|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|D|_|_|D|_|_|_|_|W|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|s|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|s|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|s|",
			"|n|n|n|n|n|n|W|S|W|h|h|h|h|h|h|h|W|_|_|s|s|s|s|s|s|"};

	public Board() {
		tiles= new Tile[25][25];
		rooms= new ArrayList<Room>();
		rooms.add(new Room("kitchen"));
		rooms.add(new Room("ballroom"));
		rooms.add(new Room("conservatory"));
		rooms.add(new Room("dining room"));
		rooms.add(new Room("billiard room"));
		rooms.add(new Room("library"));
		rooms.add(new Room("lounge"));
		rooms.add(new Room("hall"));
		rooms.add(new Room("study"));
		parseBoard();
		addDoors();

	}
	/**
	 * Reads in the hardcoded string array board and creates a 2d array of tiles to use as actual board
	 * Also when a new roomtile is created, it adds this tile to the room's list of tiles
	 */
	public void parseBoard() {
		int row = 0;
		int col;
		String delims = "[|]+";
		String[] tokens;
		for (String s : hardCodedBoard) {
			s.replaceAll("\\s+", "");
			tokens = s.split(delims);
			for (col = 0; col < 25; col++) {
				switch (tokens[col + 1]) {
				case "W":
					tiles[row][col]= new Tile("wall",null, row, col);
					break;
				case "S":
					tiles[row][col]= new Tile("start",null, row, col);
					break;
				case "_":
					tiles[row][col]= new Tile("floor",null, row, col);
					break;
				case "D":
					tiles[row][col]= new Tile("door",null, row, col);
					break;

				case "k":
					tiles[row][col]= new Tile("room","kitchen", row, col);
					rooms.get(0).addTile(tiles[row][col]);
					break;
				case "b":
					tiles[row][col]= new Tile("room","ballroom", row, col);
					rooms.get(1).addTile(tiles[row][col]);
					break;
				case "c":
					tiles[row][col]= new Tile("room","conservatory", row, col);
					rooms.get(2).addTile(tiles[row][col]);
					break;
				case "d":
					tiles[row][col]= new Tile("room","dining room", row, col);
					rooms.get(3).addTile(tiles[row][col]);
					break;
				case "r":
					tiles[row][col]= new Tile("room","billiard room", row, col);
					rooms.get(4).addTile(tiles[row][col]);
					break;
				case "l":
					tiles[row][col]= new Tile("room","library", row, col);
					rooms.get(5).addTile(tiles[row][col]);
					break;
				case "n":
					tiles[row][col]= new Tile("room","lounge", row, col);
					rooms.get(6).addTile(tiles[row][col]);
					break;
				case "h":
					tiles[row][col]= new Tile("room","hall", row, col);
					rooms.get(7).addTile(tiles[row][col]);
					break;
				case "s":
					tiles[row][col]= new Tile("room","study", row, col);
					rooms.get(8).addTile(tiles[row][col]);
					break;
				}
			}
			row++;
		}
	}

	public void addDoors(){
		Map<String, Tile> doors = new HashMap<>();
		doors.put("south", getTile(7,4));
		doors.put("secret stairs", getTile(24,21));
		rooms.get(0).addDoors(doors);

		doors.clear();
		doors.put("west", getTile(5,7));
		doors.put("south-west", getTile(8,9));
		doors.put("south-east", getTile(8,15));
		doors.put("east", getTile(5,17));
		rooms.get(1).addDoors(doors);

		doors.clear();
		doors.put("south", getTile(5,19));
		doors.put("secret stairs", getTile(19,0));
		rooms.get(2).addDoors(doors);

		doors.clear();
		doors.put("east", getTile(12,8));
		doors.put("south", getTile(16,6));
		rooms.get(3).addDoors(doors);

		doors.clear();
		doors.put("west", getTile(9,18));
		doors.put("south", getTile(13,23));
		rooms.get(4).addDoors(doors);

		doors.clear();
		doors.put("north", getTile(13,21));
		doors.put("west", getTile(16,17));
		rooms.get(5).addDoors(doors);

		doors.clear();
		doors.put("north", getTile(18,6));
		doors.put("secret stairs", getTile(5,23));
		rooms.get(6).addDoors(doors);

		doors.clear();
		doors.put("north-west", getTile(17,11));
		doors.put("north-north", getTile(17,12));
		doors.put("north-east", getTile(17,13));
		doors.put("east", getTile(20,16));
		rooms.get(7).addDoors(doors);

		doors.clear();
		doors.put("north", getTile(20,19));
		doors.put("secret stairs", getTile(1,5));
		rooms.get(8).addDoors(doors);

	}

	public Tile getTile(int row, int col){
		return tiles[row][col];
	}


	public ArrayList<Room> getRooms(){
		return rooms;
	}

	public Room currentRoom(Token t){
		for(Room r : rooms){
			if(r.getTiles().contains(getTile(t.getRow(),t.getCol()))){
				return r;
			}
		}

		return null;

	}

	public void printBoard() {

		for (Tile[] tArray : tiles) {
			System.out.print("|");
			for (Tile t : tArray) {
				if (t == null) {
					System.out.print("!");
				} else {
					System.out.print(t.toString() + "|");
				}
			}
			System.out.println();
		}
	}

	/**
	 * check if a move is valid and and if so move the player
	 *
	 * @param oldRow
	 * @param oldCol
	 * @param newRow
	 * @param newCol
	 * @param p
	 * @return
	 */
	public boolean moveValid(int oldRow, int oldCol, int newRow, int newCol, Player p) {

		if (newRow < 0 || newRow >= tiles.length || newCol < 0 || newCol >= tiles[0].length) {
			System.out.println("Can't go out of bounds");
			return false;
		}
		if (tiles[newRow][newCol].getType().equals("room") && !(tiles[oldRow][oldCol].getType().equals("door"))) {
			System.out.println("You must be on a door to enter a room");
			return false;
		}
		if (tiles[newRow][newCol].getType().equals("wall")) {
			System.out.println("Can't walk through walls");
			return false;
		}
		if(tiles[newRow][newCol].getToken()!=null){
			System.out.println("Can't walk through players");
			return false;
		}
		tiles[oldRow][oldCol].setToken(null);
		p.setRow(newRow);
		p.setCol(newCol);
		tiles[newRow][newCol].setToken(p);
		return true;
	}
}