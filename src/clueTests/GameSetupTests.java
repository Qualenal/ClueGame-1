package clueTests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.Card.CardType;

public class GameSetupTests {
	private static Board board;
	private static ClueGame game;
	public static int NUM_CARDS = 22;
	public static int NUM_WEAPONS = 8;
	public static int NUM_PERSONS = 6;
	public static int NUM_ROOMS = 8;

	@BeforeClass
	public static void setUp() {
		game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}

	@Test
	public void testCardLoad() {
		ArrayList<Card> deck = game.getDeck();
		//Check to make sure there are 22 cards in the deck
		Assert.assertEquals(NUM_CARDS, deck.size());
		
		int counter = 0;
		//Check to see if there are the right number of weapons
		for(Card c : deck){
			if(c.getType() == CardType.WEAPON)
				counter++;
		}
		Assert.assertEquals(NUM_WEAPONS, counter);
		
		//Check to see if there are the right number of room cards
		counter = 0;
		for(Card c : deck){
			if(c.getType() == CardType.ROOM)
				counter++;
		}
		Assert.assertEquals(NUM_ROOMS, counter);
		
		//Check to see if there are the right number of persons
		for(Card c : deck){
			if(c.getType() == CardType.PERSON)
				counter++;
		}
		Assert.assertEquals(NUM_PERSONS, counter);
		
		//Check to make sure the deck contains our cards
		Card testRoom = new Card("Kitchen" ,CardType.ROOM);
		Assert.assertTrue(deck.contains(testRoom));
		Card testWeapon = new Card("Knife" ,CardType.WEAPON);
		Assert.assertTrue(deck.contains(testWeapon));
		Card testPerson = new Card("Mrs.White" ,CardType.PERSON);
		Assert.assertTrue(deck.contains(testPerson));
	}

}
