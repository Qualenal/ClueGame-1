package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public ComputerPlayer(String name, String color, int x, int y) {
		super(name, color, x, y);
		lastRoomVisited = ' ';
	}
	public BoardCell pickLocation(Set<BoardCell> targets){
		return null;
	}
	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
