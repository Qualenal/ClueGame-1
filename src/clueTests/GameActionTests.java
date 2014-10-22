package clueTests;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.Solution;

public class GameActionTests {
	private Board board;
	private ClueGame game;
	@Before
	public void setUp() {
		game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt","ClueCards.txt","CluePlayers.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
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
			//Assert the total locations picked is 100
			Assert.assertEquals(100, loc0+loc1+loc2+loc3);
			//Assert that each location was picked more than once
			Assert.assertTrue(loc0 > 5);
			Assert.assertTrue(loc1 > 5);
			Assert.assertTrue(loc2 > 5);
			Assert.assertTrue(loc3 > 5);
		}
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
	public void disproveSuggestionTests(){
		//Set the game solution
		Solution gameSolution = new Solution("Kitchen","Knife","Mr. Green");
		game.setSolution(gameSolution);
		
		Solution testSolution = new Solution("Kitchen","Knife","Mr. Green");
		Assert.assertTrue(game.checkAccusation(testSolution));
		
	}
}
