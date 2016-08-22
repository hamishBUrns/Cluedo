package board;

public interface Token {
	public int getRow();
	public int getCol();
	public String id();
	public void setRow(int row);
	public void setCol(int col);
}
