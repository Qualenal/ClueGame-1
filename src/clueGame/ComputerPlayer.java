package clueGame;

import java.util.ArrayList;
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
	public void createSuggestion(){
		
	}
	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		Random random = new Random();
		//Iterate through hand, add matching cards
		ArrayList<Card> matchingCards = new ArrayList<Card>();
		for(Card c : getCards()){
			if(c.getName().equals(person) || c.getName().equals(room) || c.getName().equals(weapon))
				matchingCards.add(c);
		}
		if(matchingCards.size() == 0)
			return null;
		else if(matchingCards.size() == 1)
			return matchingCards.get(0);
		else{
			int i = random.nextInt(matchingCards.size());
			return matchingCards.get(i);
		}
	}
	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}
	
}
