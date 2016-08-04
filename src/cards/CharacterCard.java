package cards;
public class CharacterCard implements Card {

	private String name;

	public CharacterCard(String id){
		name=id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof CharacterCard){
			CharacterCard other = (CharacterCard) o;
			return name.equals(other.getName());
		}
		return false;
	}

}
