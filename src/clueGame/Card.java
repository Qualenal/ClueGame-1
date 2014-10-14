package clueGame;

public class Card {
	public enum CardType{ROOM,WEAPON,PERSON};
	
	private String name;
	private CardType type;
	
	public Card(String name, CardType type) {
		super();
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean equals(Object other){
		if(other == this)
			return true;
		if(other == null || this.getClass() != other.getClass())
			return false;
		Card otherCard = (Card) other;
		return this.name == otherCard.getName() &&
				this.type == otherCard.getType();
	}
	public String getName() {
		return name;
	}

	public CardType getType() {
		return type;
	}
}
