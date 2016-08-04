package game;

public class Board {

	private Tile[][] board;

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
		board[oldRow][oldCol].setPlayer(null);
		p.setRow(newRow);
		p.setCol(newCol);
		board[newRow][newCol].setPlayer(p);
		return true;
	}
}