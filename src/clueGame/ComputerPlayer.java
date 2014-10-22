package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	private String cardFile;
	private String[] suggestion = new String[3];
	private Set<Card> seenCards = new HashSet<Card>();
	private ArrayList<Card> personDeck;
	private ArrayList<Card> weaponDeck;
	
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

	public String[] createSuggestion(){
		suggestion[0]= "testperson";
		suggestion[1]= "testweapon";
		suggestion[2]= "testroom";
		return suggestion;
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
	
	public void seeCard(Card card){
		seenCards.add(card);
	}
	
	public void loadChoices() throws BadConfigFormatException{
		Scanner in = null;
		try{
		FileReader fileIn = new FileReader(cardFile);
		in = new Scanner(fileIn);
		} catch(FileNotFoundException e){
			System.err.println(e.getLocalizedMessage());
			System.exit(0);
		}
		while(in.hasNextLine()){
			String nextLine = in.nextLine();
			if(nextLine.equals("PEOPLE")){
				//While loops for people and rooms
				while(true){
					nextLine = in.nextLine();
					if(nextLine.equals("ROOMS"))
						break;
					Card newCard = new Card(nextLine,CardType.PERSON);
					personDeck.add(newCard);
				}
				while(true){
					nextLine = in.nextLine();
					if(nextLine.equals("WEAPONS")){
						//Advance past WEAPONS for the final iteration
						nextLine = in.nextLine();
						break;
					}
					Card newCard = new Card(nextLine,CardType.ROOM);
				}
			}
			//Only thing left is the weapons
			Card newCard = new Card(nextLine,CardType.WEAPON);
			weaponDeck.add(newCard);
		}
	}
	
}
