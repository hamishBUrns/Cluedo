package game;

public class Tile{

	private String type;
	private String roomName;
	private Player player;

	public Tile(String typ,String roomNam){
		this.type=typ;
		roomName=roomNam;

	}
	public Player getPlayer(){
		return player;
	}
	public void setPlayer(Player p){
		player=p;
	}
	public String getType(){
		return type;
	}
	@Override
	public String toString(){
		if(player!=null){
			return Integer.toString(player.getPlayerNumber());
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
