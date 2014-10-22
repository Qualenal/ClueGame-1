package clueTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.ClueGame;
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
	public void disproveSuggestionTests(){
		//Set the game solution
		Solution gameSolution = new Solution("Kitchen","Knife","Mr. Green");
		game.setSolution(gameSolution);
		
		Solution testSolution = new Solution("Kitchen","Knife","Mr. Green");
		Assert.assertTrue(game.checkAccusation(testSolution));
		
	}
}
