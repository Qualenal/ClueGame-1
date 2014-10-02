package clueTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.RoomCell;

public class FailingTests {

    private static Board board;
    public static final int NUM_ROOMS = 9;
    public static final int NUM_ROWS = 23;
    public static final int NUM_COLUMNS = 14;

    @BeforeClass
    public static void setUp() {
	ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
	game.loadConfigFiles();
	board = game.getBoard();
    }

    @Test
    public void testRooms() {
	Map<Character, String> rooms = board.getRooms();
	assertEquals(NUM_ROOMS, rooms.size());
	assertEquals("Conservatory", rooms.get('C'));
	assertEquals("Ballroom", rooms.get('B'));
	assertEquals("Billiard room", rooms.get('R'));
	assertEquals("Dining room", rooms.get('D'));
	assertEquals("Walkway", rooms.get('W'));
    }

    @Test
    public void testRowsCols() {
	assertEquals(NUM_ROWS, board.getNumRows());
	assertEquals(NUM_COLUMNS, board.getNumColumns());
    }

    @Test
    public void FourDoorDirections() {
	RoomCell room = board.getRoomCellAt(2, 2);
	assertTrue(room.isDoorway());
	assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
	room = board.getRoomCellAt(0, 5);
	assertTrue(room.isDoorway());
	assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
	room = board.getRoomCellAt(3, 9);
	assertTrue(room.isDoorway());
	assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
	room = board.getRoomCellAt(1, 10);
	assertTrue(room.isDoorway());
	assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
	room = board.getRoomCellAt(1, 13);
	assertFalse(room.isDoorway());
	BoardCell cell = board.getCellAt(0, 6);
	assertFalse(cell.isDoorway());
    }

    @Test
    public void testNumberOfDoorways() {
	int numDoors = 0;
	int totalCells = board.getNumColumns() * board.getNumRows();
	Assert.assertEquals(322, totalCells);
	for (int row = 0; row < board.getNumRows(); row++)
	    for (int col = 0; col < board.getNumColumns(); col++) {
		BoardCell cell = board.getCellAt(row, col);
		if (cell.isDoorway())
		    numDoors++;
	    }
	Assert.assertEquals(14, numDoors);
    }

    @Test
    public void testRoomInitials() {
	assertEquals('C', board.getRoomCellAt(0, 0).getInitial());
	assertEquals('S', board.getRoomCellAt(0, 12).getInitial());
	assertEquals('B', board.getRoomCellAt(9, 13).getInitial());
	assertEquals('O', board.getRoomCellAt(0, 6).getInitial());
	assertEquals('K', board.getRoomCellAt(16, 5).getInitial());
    }

    @Test(expected = BadConfigFormatException.class)
    public void testBadColumns() throws BadConfigFormatException,
	    FileNotFoundException {
	ClueGame game = new ClueGame("ClueLayoutBadColumns.csv",
		"ClueLegend.txt");
	game.loadConfigFiles();
//	game.getBoard().loadBoardConfig();
    }

    @Test(expected = BadConfigFormatException.class)
    public void testBadRoom() throws BadConfigFormatException,
	    FileNotFoundException {
	ClueGame game = new ClueGame("ClueLayoutBadRoom.csv", "ClueLegend.txt");
	game.loadConfigFiles();
//	game.getBoard().loadBoardConfig();
    }

    @Test(expected = BadConfigFormatException.class)
    public void testBadRoomFormat() throws BadConfigFormatException,
	    FileNotFoundException {
	ClueGame game = new ClueGame("ClueLayout.csv",
		"ClueLegendBadFormat.txt");
	game.loadConfigFiles();
//	game.getBoard().loadBoardConfig();
    }
}
