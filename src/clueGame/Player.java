package clueGame;

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int x;
	private int y;
	private ArrayList<Card> cards;
	
	
	public Player(String name, String color, int x, int y) {
		super();
		this.name = name;
		this.color = color;
		this.x = x;
		this.y = y;
		cards = new ArrayList<Card>();
	}


	public abstract Card disproveSuggestion(String person, String room, String weapon);

	public void addCard(Card newCard){
		cards.add(newCard);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public String getName() {
		return name;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	
	public String getColor(){
		return color;
	}
}
