package game;

import java.util.ArrayList;

public class Board {

	private Tile[][] board;

	private ArrayList<Room> rooms;
	private String[] hardCodedBoard={
			"|W|W|W|W|W|W|W|W|W|S|W|W|W|W|W|S|W|W|W|W|W|W|W|W|W|",
			"|k|k|k|k|k|D|W|_|_|_|b|b|b|b|b|_|_|_|W|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|D|b|b|b|b|b|b|b|b|b|D|_|D|c|c|c|D|W|",
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
			"|D|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|_|_|_|_|_|S|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|D|_|_|D|_|_|_|_|W|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|D|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|s|",
			"|n|n|n|n|n|n|n|_|_|h|h|h|h|h|h|h|_|_|_|s|s|s|s|s|s|",
			"|n|n|n|n|n|n|W|S|W|h|h|h|h|h|h|h|W|_|_|s|s|s|s|s|s|"};

	public Board() {
		board= new Tile[25][25];
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

	}

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
					board[row][col]= new Tile("wall",null, row, col);
					break;
				case "S":
					board[row][col]= new Tile("start",null, row, col);
					break;
				case "_":
					board[row][col]= new Tile("floor",null, row, col);
					break;
				case "D":
					board[row][col]= new Tile("door",null, row, col);
					break;

				case "k":
					board[row][col]= new Tile("room","kitchen", row, col);
					rooms.get(0).addTile(board[row][col]);
					break;
				case "b":
					board[row][col]= new Tile("room","ballroom", row, col);
					rooms.get(1).addTile(board[row][col]);
					break;
				case "c":
					board[row][col]= new Tile("room","conservatory", row, col);
					rooms.get(2).addTile(board[row][col]);
					break;
				case "d":
					board[row][col]= new Tile("room","dining room", row, col);
					rooms.get(3).addTile(board[row][col]);
					break;
				case "r":
					board[row][col]= new Tile("room","billiard room", col, col);
					rooms.get(4).addTile(board[row][col]);
					break;
				case "l":
					board[row][col]= new Tile("room","library", row, col);
					rooms.get(5).addTile(board[row][col]);
					break;
				case "n":
					board[row][col]= new Tile("room","lounge", row, col);
					rooms.get(6).addTile(board[row][col]);
					break;
				case "h":
					board[row][col]= new Tile("room","hall", row, col);
					rooms.get(7).addTile(board[row][col]);
					break;
				case "s":
					board[row][col]= new Tile("room","study", row, col);
					rooms.get(8).addTile(board[row][col]);
					break;
				}

			}
			row++;
		}
	}

	public Tile getTile(int row, int col){
		return board[row][col];
	}

	public void printBoard() {

		for (Tile[] tArray : board) {
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

		if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
			System.out.println("Can't go out of bounds");
			return false;
		}
		System.out.println("oldPlace: "+getTile(oldRow,oldCol).getType()+" new place: "+getTile(newRow,newCol).getType());
		System.out.println("player col: "+ p.getCol()+"player row"+p.getRow());
		if (board[newRow][newCol].getType().equals("room") && !(board[oldRow][oldCol].getType().equals("door"))) {
			System.out.println("You must be on a door to enter a room");
			return false;
		}
		if (board[newRow][newCol].getType().equals("wall")) {
			System.out.println("Can't walk through walls");
			return false;
		}
		if(board[newRow][newCol].getToken()!=null){
			System.out.println("Can't walk through players");
			return false;
		}
		board[oldRow][oldCol].setToken(null);
		p.setRow(newRow);
		p.setCol(newCol);
		board[newRow][newCol].setToken(p);
		return true;
	}
}