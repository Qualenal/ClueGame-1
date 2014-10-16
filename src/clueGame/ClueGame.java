package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import clueGame.Card.CardType;

public class ClueGame {
	private Map<Character, String> rooms;
	private Board clueBoard;
	private String mapFile;
	private String roomFile;
	private String cardFile;
	private String playerFile;
	private ArrayList<Card> deck;


	public ClueGame(String mapFile, String roomFile, String cardFile, String playerFile) {
		this.mapFile = mapFile;
		this.roomFile = roomFile;
		this.cardFile = cardFile;
		this.playerFile = playerFile;
		
		clueBoard = new Board();
		rooms = new LinkedHashMap<Character, String>();
		deck = new ArrayList<Card>();
	}

	public void loadConfigFiles() {

		try {
			loadCardConfig();
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
		Scanner in = null;
		try {
			in = new Scanner(new File(roomFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			char lineChar;
			String[] lineArr = line.split(", ");
			if (lineArr.length  > 2) {
				throw new BadConfigFormatException();
			}
			lineChar = lineArr[0].charAt(0);
			rooms.put(lineChar, lineArr[1]);
		}
		in.close();
		clueBoard.setRooms(rooms);
	}
	public void loadCardConfig() throws BadConfigFormatException{
		Scanner in = null;
		try{
		FileReader fileIn = new FileReader(cardFile);
		in = new Scanner(fileIn);
		} catch(FileNotFoundException e){
			System.err.println(e.getLocalizedMessage());
			System.exit(0);
		}
		while(in.hasNextLine()){
			String nextLine = in.nextLine();
			if(nextLine.equals("PEOPLE")){
				//While loops for people and rooms
				while(true){
					nextLine = in.nextLine();
					if(nextLine.equals("ROOMS"))
						break;
					Card newCard = new Card(nextLine,CardType.PERSON);
					deck.add(newCard);
				}
				while(true){
					nextLine = in.nextLine();
					if(nextLine.equals("WEAPONS")){
						//Advance past WEAPONS for the final iteration
						nextLine = in.nextLine();
						break;
					}
					Card newCard = new Card(nextLine,CardType.ROOM);
					deck.add(newCard);
				}
			}
			//Only thing left is the weapons
			Card newCard = new Card(nextLine,CardType.WEAPON);
			deck.add(newCard);
		}
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
}