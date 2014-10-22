package clueGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public ComputerPlayer(String name, String color, int x, int y) {
		super(name, color, x, y);
		lastRoomVisited = ' ';
	}
	public BoardCell pickLocation(Set<BoardCell> targets){
		Random randomizer = new Random();
		BoardCell pickedCell = null;
		HashSet<BoardCell> toBeRemoved = new HashSet<BoardCell>();
		for(BoardCell c : targets){
			if(c.isRoom()){
				RoomCell cell = (RoomCell) c;
				if(cell.getInitial() != lastRoomVisited)
					return c;
				if(cell.getInitial() == lastRoomVisited)
					toBeRemoved.add(c);
			}
		}
		targets.removeAll(toBeRemoved);
		int item = randomizer.nextInt(targets.size());
		int i = 0;
		for(BoardCell c : targets){
			if(item == i)
				pickedCell = c;
			i++;
		}
		return pickedCell;
	}
	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}
	
}
