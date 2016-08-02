package cards;
public class WeaponCard implements Card{
	private String name;
	public WeaponCard(String id) {
		name=id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
