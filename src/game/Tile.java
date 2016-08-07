package game;

public class Tile {

	private String type;
	private String roomName;
	private String roomLetter;

	private Token token;
	private int row;
	private int col;

	public Tile(String typ, String roomNam, int row, int col) {
		this.type = typ;
		roomName = roomNam;
		this.row = row;
		this.col = col;
		if (roomName != null) {
			switch (roomName) {
			case ("kitchen"):
				roomLetter = "k";
				break;
			case ("ballroom"):
				roomLetter = "b";
				break;
			case ("conservatory"):
				roomLetter = "c";
				break;
			case ("dining room"):
				roomLetter = "d";
				break;
			case ("billiard room"):
				roomLetter = "r";
				break;
			case ("library"):
				roomLetter = "l";
				break;
			case ("lounge"):
				roomLetter = "n";
				break;
			case ("hall"):
				roomLetter = "h";
				break;
			case ("study"):
				roomLetter = "s";
				break;
			}
		}
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token toke) {
		token = toke;
	}

	public String getType() {
		return type;
	}

	public String getRoomName() {
		return roomName;
	}

	@Override
	public String toString() {
		if (token != null) {
			return token.symbol();
		} else {
			switch (type) {
			case ("wall"):
				return "W";
			case ("floor"):
				return "_";
			case ("door"):
				return "D";
			case ("start"):
				return "S";
			case ("room"):
				return roomLetter;
			default:
				return "?";
			}

		}
	}

}
