package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, String color, int x, int y) {
		super(name, color, x, y);
		// TODO Auto-generated constructor stub
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

}
