package game;

public class Tile{

	private String type;
	private String roomName;
	private Token token;
	private int row;
	private int col;

	public Tile(String typ,String roomNam,int row, int col){
		this.type=typ;
		roomName=roomNam;
		this.row=row;
		this.col=col;

	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public Token getToken(){
		return token;
	}
	public void setToken(Token toke){
		token=toke;
	}
	public String getType(){
		return type;
	}
	public String getRoomName(){
		return roomName;
	}
	@Override
	public String toString(){
		if(token!=null){
			return token.symbol();
		}
		else{
			switch(type){
			case ("wall"):
				return "W";
			case("floor"):
				return"_";
			case("door"):
				return"D";
			case("start"):
				return "S";
			case("room"):
				return "R";
			default:
				return "?";
			}

		}
	}

}
