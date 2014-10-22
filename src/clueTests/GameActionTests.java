package clueTests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionTests {
	private Board board;
	private ClueGame game;
	private ArrayList<Player> players;
	@Before
	public void setUp() {
		game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt","ClueCards.txt","CluePlayers.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
		players = game.getPlayers();
	}

	@Test
	public void checkAccusationTest() {
		//Set the solution for the game
		Solution gameSolution = new Solution("Kitchen","Knife","Mr. Green");
		game.setSolution(gameSolution);
		
		//Test a correct solution
		Solution testSolution = new Solution("Kitchen","Knife","Mr. Green");
		Assert.assertTrue(game.checkAccusation(testSolution));
		
		//Test a false solution
		testSolution = new Solution("Conservatory","Shuriken","Miss Scarlet");
		Assert.assertFalse(game.checkAccusation(testSolution));
		
		//Test a slightly false solution
		testSolution = new Solution("Kitchen","Knife","Miss Scarlet");
		Assert.assertFalse(game.checkAccusation(testSolution));
	}
	
	@Test
	public void testRandomlySelectingLocation(){
		//This test will ensure that distribution of randomly selecting targets is even
		ComputerPlayer testPlayer = new ComputerPlayer("Test","BLACK",15,6);
		//Selecting a location where all 4 targets should be walkways
		board.calcTargets(15,6,2);
		Set<BoardCell> targets = board.getTargets();
		int loc0 = 0;
		int loc1 = 0;
		int loc2 = 0;
		int loc3 = 0;
		for(int i = 0; i < 100; i++){
			BoardCell pickedCell = testPlayer.pickLocation(targets);
			if(pickedCell == board.getCellAt(15, 4))
				loc0++;
			else if(pickedCell == board.getCellAt(14, 5))
				loc1++;
			else if(pickedCell == board.getCellAt(14, 7))
				loc2++;
			else if(pickedCell == board.getCellAt(15, 8))
				loc3++;
			else
				fail("No location picked.");
		}
		//Assert the total locations picked is 100
		Assert.assertEquals(100, loc0+loc1+loc2+loc3);
		//Assert that each location was picked more than once
		Assert.assertTrue(loc0 > 5);
		Assert.assertTrue(loc1 > 5);
		Assert.assertTrue(loc2 > 5);
		Assert.assertTrue(loc3 > 5);
	}
	
	@Test
	public void testRoomSelected(){
		//This test will assure that a room that was not previously visited is always chosen
		ComputerPlayer testPlayer = new ComputerPlayer("Test","BLACK",15,6);
		//Select a location where there is a room in range
		board.calcTargets(21, 7, 2);
		//Test 50 times to ensure the room is always picked
		for(int i = 0; i < 50; i++){
			BoardCell testCell = testPlayer.pickLocation(board.getTargets());
			Assert.assertTrue(testCell == board.getCellAt(21, 6));
		}
	}

	@Test
	public void testVisitedRoomNotSelected(){
		ComputerPlayer testPlayer = new ComputerPlayer("Test","BLACK",21,7);
		testPlayer.setLastRoomVisited('K');
		board.calcTargets(21, 7, 1);
		int loc0 = 0;
		int loc1 = 0;
		for(int i = 0; i < 50; i++){
			BoardCell testCell = testPlayer.pickLocation(board.getTargets());
			if(testCell == board.getCellAt(22, 7))
				loc0++;
			if(testCell == board.getCellAt(21, 8))
				loc1++;
			Assert.assertFalse(testCell == board.getCellAt(21, 6));
		}
		Assert.assertTrue(loc0 > 10);
		Assert.assertTrue(loc1 > 10);
	}
	@Test
	public void disproveSuggetionBasic(){
		ComputerPlayer testPlayer = new ComputerPlayer("Test","BLACK",21,7);
		testPlayer.addCard(new Card("Knife",CardType.WEAPON));
		testPlayer.addCard(new Card("Bat",CardType.WEAPON));
		testPlayer.addCard(new Card("Ballroom",CardType.ROOM));
		testPlayer.addCard(new Card("Billiard Room",CardType.ROOM));
		testPlayer.addCard(new Card("Miss Scarlet",CardType.PERSON));
		testPlayer.addCard(new Card("Professor Plum",CardType.PERSON));
		// Test a weapon, room and person
		Card testCard = testPlayer.disproveSuggestion("Mr. Green", "Kitchen", "Knife");
		Assert.assertTrue(testCard.equals(new Card("Knife",CardType.WEAPON)));
		testCard = testPlayer.disproveSuggestion("Mr. Green", "Ballroom", "Lead Pipe");
		Assert.assertTrue(testCard.equals(new Card("Ballroom",CardType.ROOM)));
		testCard = testPlayer.disproveSuggestion("Professor Plum", "Kitchen", "Lead Pipe");
		Assert.assertTrue(testCard.equals(new Card("Professor Plum",CardType.PERSON)));
		// Make sure if they don't have the card, it's null
		testCard = testPlayer.disproveSuggestion("Mr.Green", "Kitchen", "Lead Pipe");
		Assert.assertNull(testCard);
		
	}
	@Test
	public void randomDisproveSelecation(){
		ComputerPlayer testPlayer = new ComputerPlayer("Test","BLACK",21,7);
		testPlayer.addCard(new Card("Knife",CardType.WEAPON));
		testPlayer.addCard(new Card("Mr. Green",CardType.PERSON));
		testPlayer.addCard(new Card("Kitchen",CardType.ROOM));
		int card0 = 0;
		int card1 = 0;
		int card2 = 0;
		Card testCard;
		for(int i = 0; i < 50; i++){
			testCard = testPlayer.disproveSuggestion("Mr. Green", "Kitchen", "Knife");
			if(testCard.equals(new Card("Knife",CardType.WEAPON)))
				card0++;
			else if(testCard.equals(new Card("Mr. Green",CardType.PERSON)))
				card1++;
			else if(testCard.equals(new Card("Kitchen",CardType.ROOM)))
				card2++;
			else
				fail("Didn't show a card");
		}
		Assert.assertTrue(card0 > 8);
		Assert.assertTrue(card1 > 8);
		Assert.assertTrue(card2 > 8);
	}
	@Test
	public void testOnlyOneQuery(){
		// Order should be Plum then Mustard, give them both cards that satisfy the suggestion, make sure Plum's card is returned
		players.get(1).addCard(new Card("Knife",CardType.WEAPON));
		players.get(2).addCard(new Card("Kitchen",CardType.ROOM));
		Card answer = game.handleSuggestion("Mr. Green", "Kitchen", "Knife", players.get(0));
		Assert.assertTrue(answer.equals(new Card("Knife",CardType.WEAPON)));
	}
	@Test
	public void testHumanDisprovesSuggetion(){
		//Give the human and a couple others cards, test that the human can disprove
		//This should also test that all players are queried in order
		players.get(0).addCard(new Card("Ballroom",CardType.ROOM));
		players.get(1).addCard(new Card("Knife",CardType.WEAPON));
		players.get(2).addCard(new Card("Kitchen",CardType.ROOM));
		players.get(3).addCard(new Card("Bat",CardType.WEAPON));
		Card answer = game.handleSuggestion("Miss Scarlet", "Ballroom", "Shuriken", players.get(1));
		Assert.assertTrue(answer.equals(new Card("Ballroom",CardType.ROOM)));
	}
	@Test
	public void testNullIfAccuserHasCard(){
		//This test should return null, because the accusing player has the card
		players.get(0).addCard(new Card("Ballroom",CardType.ROOM));
		players.get(1).addCard(new Card("Knife",CardType.WEAPON));
		players.get(2).addCard(new Card("Kitchen",CardType.ROOM));
		players.get(3).addCard(new Card("Bat",CardType.WEAPON));
		Card answer = game.handleSuggestion("Miss Scarlet", "Ballroom", "Shuriken", players.get(0));
		Assert.assertNull(answer);
	}
}
