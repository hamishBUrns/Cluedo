package game;

public class Board {

	//some kind of 2d array here
	private Tile [][] board;

	public Board(){
		
	}

	public boolean goNorth(Player p){
		//stuff happens, do checks that move is valid
		board[p.getRow()][p.getCol()].getPlayer = null;
		p.setRow(p.getRow()+1);
		board[p.getRow()][p.getCol()].getplayer = p;
		return true;
	}
}
