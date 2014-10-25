package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;



public abstract class Player {
	private String name;
	private String sColor;
	private Color color;
	private int x;
	private int y;
	private ArrayList<Card> cards;
	
	
	public Player(String name, String sColor, int x, int y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.sColor = sColor;
		this.color = convertColor(sColor);
		cards = new ArrayList<Card>();
	}
	
	public Color convertColor(String sColor){
		Color color;
		try{
			Field field = Class.forName("java.awt.Color").getField(sColor.trim());
			color = (Color)field.get(null);}
		catch (Exception e) {
			color = null;
		}
		return color;
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
	
	public Color getColor(){
		return color;
	}
}
