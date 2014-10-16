package clueTests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.Card.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

public class GameSetupTests {
	private static Board board;
	private static ClueGame game;
	public static int NUM_CARDS = 22;
	public static int NUM_WEAPONS = 8;
	public static int NUM_PERSONS = 6;
	public static int NUM_ROOMS = 8;

	@BeforeClass
	public static void setUp() {
		game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt","ClueCards.txt","CluePlayers.txt");
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
		counter = 0;
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
		Card testPerson = new Card("Mrs. White" ,CardType.PERSON);
		Assert.assertTrue(deck.contains(testPerson));
	}
	@Test
	public void testPlayerLoad(){
		ArrayList<Player> players = game.getPlayers();
		//Human Player will be Miss Scarlet, check that they have the correct variables
		//Human Player should be index 0 in the arraylist, check that too
		HumanPlayer human = game.getHuman();
		Assert.assertEquals("Miss Scarlet", human.getName());
		Assert.assertEquals(19, human.getX());
		Assert.assertEquals(0, human.getY());
		Assert.assertEquals("RED", human.getColor());
		human = (HumanPlayer) players.get(0);
		Assert.assertEquals("Miss Scarlet", human.getName());
		
		
		//Check Mr. Green, the last computer player (index 5)
		ComputerPlayer player = (ComputerPlayer) players.get(5);
		Assert.assertEquals("Mr. Green", player.getName());
		Assert.assertEquals(22, player.getX());
		Assert.assertEquals(9, player.getY());
		Assert.assertEquals("GREEN", player.getColor());
		
		//Check Professor Plum, the first computer player (index 1)
		player = (ComputerPlayer) players.get(1);
		Assert.assertEquals("Professor Plum", player.getName());
		Assert.assertEquals(0, player.getX());
		Assert.assertEquals(4, player.getY());
		Assert.assertEquals("PURPLE", player.getColor());
	}
	
	@Test
	public void testDeal(){
		//Deal the cards
		game.deal();
		//Check that the deck is empty
		Assert.assertTrue(game.getDeck().isEmpty());
		//Number of Cars 22 / Number of Players 6, each player should get 3 to 4 cards
		//Check to make sure each player has 3 or 4 cards
		ArrayList<Player> players = game.getPlayers();
		for(Player p : players){
			ArrayList<Card> hand = p.getCards();
			Assert.assertTrue(hand.size() == 3 || hand.size() == 4);
		}
		
		//Check that the Knife only appears once in the hands of the players
		Card testCard = new Card("Knife",CardType.WEAPON);
		int counter = 0;
		for(Player p : players){
			if(p.getCards().contains(testCard))
				counter++;
		}
		Assert.assertEquals(1, counter);
		
		//Check that Kitchen only appears once in the hands of the players
		testCard = new Card("Kitchen", CardType.ROOM);
		counter = 0;
		for(Player p : players){
			if(p.getCards().contains(testCard))
				counter++;
		}
		Assert.assertEquals(1, counter);
	}
}
