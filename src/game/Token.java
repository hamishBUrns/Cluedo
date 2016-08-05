package game;

public interface Token {
	public int getRow();
	public int getCol();
	public String symbol();
	public void setRow(int row);
	public void setCol(int col);
}
