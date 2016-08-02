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

}
