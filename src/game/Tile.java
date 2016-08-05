package game;

public class Tile {

	private String type;
	private String roomName;
	private String roomLetter;
	private Player player;

	public Tile(String typ, String roomNam) {
		this.type = typ;
		roomName = roomNam;
		if(roomName != null){
			switch(roomName){
			case("kitchen"):
				roomLetter = "k";
			break;
			case("ballroom"):
				roomLetter = "b";
				break;
			case("conservatory"):
				roomLetter = "c";
				break;
			case("dining room"):
				roomLetter = "d";
			break;
			case("billiard room"):
				roomLetter = "r";
			break;
			case("library"):
				roomLetter = "l";
			break;
			case("lounge"):
				roomLetter = "n";
			break;
			case("hall"):
				roomLetter = "h";
			break;
			case("study"):
				roomLetter = "s";
			break;
			}
		}


	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		if (player != null) {
			return Integer.toString(player.getPlayerNumber());
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
