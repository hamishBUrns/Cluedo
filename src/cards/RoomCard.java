package cards;
public class RoomCard implements Card {
	private String name;
	public RoomCard(String id) {
		name= id;
		}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof RoomCard){
			RoomCard other = (RoomCard) o;
			return name.equals(other.getName());
		}
		return false;
	}

}
