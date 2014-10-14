package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	private Map<Character, String> rooms;
	private Board clueBoard;
	private String mapFile;
	private String roomFile;
	private String deckFile;
	private ArrayList<Card> deck;


	public ClueGame(String mapFile, String roomFile) {
		this.mapFile = mapFile;
		this.roomFile = roomFile;
		clueBoard = new Board();
		rooms = new LinkedHashMap<Character, String>();
		deck = new ArrayList<Card>();
	}

	public void loadConfigFiles() {

		try {
			loadRoomConfig();
			clueBoard.loadBoardConfig(mapFile);
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
			foo = new Scanner(new File(roomFile));
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

	public ArrayList<Card> getDeck() {
		return deck;
	}
	
}