package game;

public class Board {

	private Tile[][] board;
	private String[] hardCodedBoard={
			"|W|W|W|W|W|W|W|W|W|S|W|W|W|W|W|S|W|W|W|W|W|W|W|W|W|",
			"|R|R|R|R|R|D|W|_|_|_|R|R|R|R|R|_|_|_|W|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|_|D|R|R|R|R|R|R|R|R|R|D|_|D|R|R|R|D|W|",
			"|W|R|R|R|R|R|_|_|R|R|R|R|R|R|R|R|R|_|_|_|_|_|_|_|S|",
			"|_|_|_|_|_|_|_|_|R|R|R|R|R|R|R|R|R|_|_|_|_|_|_|_|W|",
			"|W|_|_|_|_|_|_|_|_|D|_|_|_|_|_|D|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|_|_|_|_|_|_|_|_|_|_|_|_|_|D|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|R|R|_|_|W|W|W|W|W|W|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|R|R|_|_|W|W|W|W|W|W|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|R|R|D|_|W|W|W|W|W|W|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|R|R|_|_|W|W|W|W|W|W|_|_|_|_|_|D|_|D|W|",
			"|R|R|R|R|R|R|R|R|_|_|W|W|W|W|W|W|_|_|_|R|R|R|R|R|W|",
			"|R|R|R|R|R|R|R|R|_|_|W|W|W|W|W|W|_|_|R|R|R|R|R|R|R|",
			"|W|_|_|_|_|_|D|_|_|_|W|W|W|W|W|W|_|D|R|R|R|R|R|R|R|",
			"|S|_|_|_|_|_|_|_|_|_|_|D|D|D|_|_|_|_|R|R|R|R|R|R|R|",
			"|W|_|_|_|_|_|D|_|_|R|R|R|R|R|R|R|_|_|_|R|R|R|R|R|W|",
			"|D|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|_|_|_|_|_|_|_|_|S|",
			"|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|D|_|_|D|_|_|_|_|W|",
			"|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|_|_|_|R|R|R|R|R|D|",
			"|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|R|_|_|R|R|R|R|R|R|R|_|_|_|R|R|R|R|R|R|",
			"|R|R|R|R|R|R|W|S|W|R|R|R|R|R|R|R|W|_|_|R|R|R|R|R|R|"};

	public Board() {

	}

	public boolean moveValid(int oldRow, int oldCol, int newRow, int newCol, Player p) {
		if (newRow < 0 || newRow > board.length || newCol < 0 || newCol > board[0].length) {
			return false;
		}
		if (board[newRow][newCol] instanceof RoomTile && !(board[oldRow][oldCol] instanceof DoorTile)) {
			System.out.println("You must be on a door to enter a room");
			return false;
		}
		if(board[newRow][newCol] instanceof WallTile){
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