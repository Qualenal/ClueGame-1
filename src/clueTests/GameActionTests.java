package clueTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.ClueGame;

public class GameActionTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt","ClueCards.txt","CluePlayers.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
