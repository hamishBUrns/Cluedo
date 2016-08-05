package game;

import java.util.ArrayList;

public class Board {

	private Tile[][] board;
	private ArrayList<ArrayList<Tile>> rooms;
	private String[] hardCodedBoard={
			"|W|W|W|W|W|W|W|W|W|S|W|W|W|W|W|S|W|W|W|W|W|W|W|W|W|",
			"|k|k|k|k|k|D|W|_|_|_|b|b|b|b|b|_|_|_|W|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|c|c|c|c|c|c|",
			"|k|k|k|k|k|k|_|D|b|b|b|b|b|b|b|b|b|D|_|D|c|c|c|D|W|",
			"|W|k|k|k|k|k|_|_|b|b|b|b|b|b|b|b|b|_|_|_|_|_|_|_|S|",
			"|_|_|_|_|_|_|_|_|b|b|b|b|b|b|b|b|b|_|_|_|_|_|_|_|W|",
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
		rooms= new ArrayList<ArrayList<Tile>>();
		for(int i=0;i<9;i++){
			rooms.add(new ArrayList<Tile>());
		}
		parseBoard();

	}

	public void parseBoard(){
		int row=0;
		int col;
		String delims = "[|]+";
		String[]tokens;
		for(String s:hardCodedBoard){
			s.replaceAll("\\s+","");
			tokens = s.split(delims);
			for(col=0; col<25;col++){

				switch(tokens[col+1]){

				case "W":
					board[row][col]= new Tile("wall",null);
					break;
				case "S":
					board[row][col]= new Tile("start",null);
					break;
				case "_":
					board[row][col]= new Tile("floor",null);
					break;
				case "D":
					board[row][col]= new Tile("door",null);
					break;

				case "k":
					board[row][col]= new Tile("room","kitchen");
					rooms.get(0).add(board[row][col]);
					break;
				case "b":
					board[row][col]= new Tile("room","ballroom");
					rooms.get(1).add(board[row][col]);
					break;
				case "c":
					board[row][col]= new Tile("room","conservatory");
					rooms.get(2).add(board[row][col]);
					break;
				case "d":
					board[row][col]= new Tile("room","dining room");
					rooms.get(3).add(board[row][col]);
					break;
				case "r":
					board[row][col]= new Tile("room","billiard room");
					rooms.get(4).add(board[row][col]);
					break;
				case "l":
					board[row][col]= new Tile("room","library");
					rooms.get(5).add(board[row][col]);
					break;
				case "n":
					board[row][col]= new Tile("room","lounge");
					rooms.get(6).add(board[row][col]);
					break;
				case "h":
					board[row][col]= new Tile("room","hall");
					rooms.get(7).add(board[row][col]);
					break;
				case "s":
					board[row][col]= new Tile("room","study");
					rooms.get(8).add(board[row][col]);
					break;
				}

			}
			row++;
		}
	}
	public void printBoard(){

		for(Tile[] tArray: board ){
			System.out.print("|");
			for(Tile t: tArray){
				if(t==null){
					System.out.print("!");
				}
				else{
				System.out.print(t.toString()+"|");
				}
			}
			System.out.println();
		}
	}

	/**
	 * check if a move is valid and and if so move the player
	 * @param oldRow
	 * @param oldCol
	 * @param newRow
	 * @param newCol
	 * @param p
	 * @return
	 */
	public boolean moveValid(int oldRow, int oldCol, int newRow, int newCol, Player p) {
		if (newRow < 0 || newRow > board.length || newCol < 0 || newCol > board[0].length) {
			return false;
		}
		if (board[newRow][newCol].getType().equals("room") && !(board[oldRow][oldCol].getType().equals("door"))) {
			System.out.println("You must be on a door to enter a room");
			return false;
		}
		if(board[newRow][newCol].getType().equals("wall")){
			System.out.println("Can't walk through walls");
			return false;
		}
		if(board[newRow][newCol].getPlayer()!=null){
			System.out.println("Can't walk through players");
			return false;
		}
		board[oldRow][oldCol].setPlayer(null);
		p.setRow(newRow);
		p.setCol(newCol);
		board[newRow][newCol].setPlayer(p);
		return true;
	}
}