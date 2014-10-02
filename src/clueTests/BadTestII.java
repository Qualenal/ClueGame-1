package clueTests;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;

public class BadTestII {
    private static Board board;

    @BeforeClass
    public static void setUp() {
	ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
	game.loadConfigFiles();
	board = game.getBoard();
	board.calcAdjacencies();
    }

    @Test
    public void testAdjacency() {
	// Locations with only walkways as adjacent locations
	LinkedList<BoardCell> testList = board.getAdjList(8, 13);
	Assert.assertEquals(2, testList.size());

	// Locations that are at each edge of the board
	testList = board.getAdjList(11, 0);
	Assert.assertEquals(2, testList.size());
	testList = board.getAdjList(22, 7);
	Assert.assertEquals(2, testList.size());
	testList = board.getAdjList(16, 13);
	Assert.assertEquals(1, testList.size());
	testList = board.getAdjList(0, 0);
	Assert.assertEquals(3, testList.size());

	// Locations that are beside a room cell that is not a doorway
	testList = board.getAdjList(11, 0);
	Assert.assertEquals(2, testList.size());
	testList = board.getAdjList(22, 7);
	Assert.assertEquals(2, testList.size());
	testList = board.getAdjList(16, 13);
	Assert.assertEquals(1, testList.size());
	// Locations that are adjacent to a doorway with needed direction
	testList = board.getAdjList(7, 0);
	Assert.assertEquals(2, testList.size());
	testList = board.getAdjList(4, 9);
	Assert.assertEquals(4, testList.size());
	testList = board.getAdjList(16, 10);
	Assert.assertEquals(4, testList.size());
	testList = board.getAdjList(21, 7);
	Assert.assertEquals(3, testList.size());
	// Locations that are doorways
	testList = board.getAdjList(14, 2);
	Assert.assertEquals(1, testList.size());
	testList = board.getAdjList(2, 2);
	Assert.assertEquals(1, testList.size());
    }

    @Test
    public void testTargets() {
	// Targets along walkways, at various distances
	board.calcTargets(9, 4, 1);
	Set<BoardCell> targets = board.getTargets();
	Assert.assertEquals(3, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(10, 4)));
	Assert.assertTrue(targets.contains(board.getCellAt(8, 4)));
	Assert.assertTrue(targets.contains(board.getCellAt(9, 3)));

	board.calcTargets(9, 4, 2);
	targets = board.getTargets();
	Assert.assertEquals(4, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(7, 4)));
	Assert.assertTrue(targets.contains(board.getCellAt(11, 4)));
	Assert.assertTrue(targets.contains(board.getCellAt(8, 3)));
	Assert.assertTrue(targets.contains(board.getCellAt(10, 3)));

	board.calcTargets(11, 9, 3);
	targets = board.getTargets();
	Assert.assertEquals(5, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(8, 9)));
	Assert.assertTrue(targets.contains(board.getCellAt(9, 10)));
	Assert.assertTrue(targets.contains(board.getCellAt(11, 10)));
	Assert.assertTrue(targets.contains(board.getCellAt(14, 9)));
	Assert.assertTrue(targets.contains(board.getCellAt(13, 10)));

	board.calcTargets(11, 9, 2);
	targets = board.getTargets();
	Assert.assertEquals(4, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(9, 9)));
	Assert.assertTrue(targets.contains(board.getCellAt(13, 9)));
	Assert.assertTrue(targets.contains(board.getCellAt(12, 10)));
	Assert.assertTrue(targets.contains(board.getCellAt(10, 10)));

	// Targets that allow the user to enter a room
	board.calcTargets(3, 3, 2);
	targets = board.getTargets();
	Assert.assertTrue(targets.contains(board.getCellAt(2, 2)));

	board.calcTargets(2, 11, 2);
	targets = board.getTargets();
	Assert.assertTrue(targets.contains(board.getCellAt(1, 12)));
	Assert.assertTrue(targets.contains(board.getCellAt(1, 11)));

	// Targets calculated when leaving a room board.calcTargets(8, 0, 2);
	targets = board.getTargets();
	Assert.assertEquals(1, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(6, 0)));

	board.calcTargets(16, 11, 2);
	targets = board.getTargets();
	Assert.assertEquals(2, targets.size());
	Assert.assertTrue(targets.contains(board.getCellAt(17, 12)));
	Assert.assertTrue(targets.contains(board.getCellAt(17, 10)));
    }
}
