package game;

public class Weapon implements Token {
	private int row;
	private int col;
	private String name;
	public Weapon(int row, int col,String name) {
		this.row=row;
		this.col=col;
		this.name=name;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public String symbol() {
		switch(name){
			case "dagger":
				return "^";
			case "rope":
				return "~";
			case "candlestick":
				return "$";
			case "leadpipe":
				return "(";
			case "revolver":
				return "@";
			case "spanner":
				return "*";
			default :
				return "&";
		}
	}
	@Override
	public void setRow(int row) {
		this.row=row;
	}

	@Override
	public void setCol(int col) {
		this.col=col;
	}

}
