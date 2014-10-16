package clueGame;

public abstract class Player {
	private String name;
	private String color;
	private int x;
	private int y;
	
	
	public Player(String name, String color, int x, int y) {
		super();
		this.name = name;
		this.color = color;
		this.x = x;
		this.y = y;
	}


	public abstract Card disproveSuggestion(String person, String room, String weapon);


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
