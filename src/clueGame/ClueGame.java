package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
    Map<Character, String> rooms;
    Board clueBoard;
    public static String csv;
    private String txt;

    public ClueGame(String csv, String txt) {
	ClueGame.csv = csv;
	this.txt = txt;
	clueBoard = new Board();
	rooms = new LinkedHashMap<Character, String>();
    }

    public void loadConfigFiles() {
	
	try {
	    loadRoomConfig();
	    clueBoard.loadBoardConfig();
	} catch (BadConfigFormatException e) {
	    e.printStackTrace();
	}

    }

    public Board getBoard() {
	return clueBoard;
    }

    public void loadRoomConfig() throws BadConfigFormatException {
	Scanner foo = null;
	try {
	    foo = new Scanner(new File(txt));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	while (foo.hasNextLine()) {
	    String line = foo.nextLine();
	    char lineChar;
	    String[] lineArr = line.split(", ");
	    if (lineArr.length  > 2) {
		throw new BadConfigFormatException();
	    }
	    lineChar = lineArr[0].charAt(0);
	    rooms.put(lineChar, lineArr[1]);
	}
	foo.close();
	clueBoard.setRooms(rooms);
    }
}