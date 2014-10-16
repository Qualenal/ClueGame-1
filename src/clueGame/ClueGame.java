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
	private ArrayList<Player> players;
	private HumanPlayer human;


	public ClueGame(String mapFile, String roomFile, String cardFile, String playerFile) {
		this.mapFile = mapFile;
		this.roomFile = roomFile;
		this.cardFile = cardFile;
		this.playerFile = playerFile;
		
		clueBoard = new Board();
		rooms = new LinkedHashMap<Character, String>();
		deck = new ArrayList<Card>();
		players = new ArrayList<Player>();
	}

	public void loadConfigFiles() {

		try {
			loadCardConfig();
			loadRoomConfig();
			clueBoard.loadBoardConfig(mapFile);
			loadPlayerConfig();
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
	
	public void loadPlayerConfig() throws BadConfigFormatException{
		Scanner in = null;
		try{
			FileReader fileIn = new FileReader(playerFile);
			in = new Scanner(fileIn);
		} catch(FileNotFoundException e){
			System.err.println(e.getLocalizedMessage());
		}
		//The first player must be a human player
		String[] parts = in.nextLine().split(",");
		if(parts.length != 4)
			throw new BadConfigFormatException();
		//Use Integers to parse the x and y values
		int x = 0,y = 0;
		try{
			x = Integer.parseInt(parts[1]);
			y = Integer.parseInt(parts[2]);
		} catch(NumberFormatException e){
			System.err.println(e.getLocalizedMessage());
			System.exit(0);
		}
		human = new HumanPlayer(parts[0],parts[3],x,y);
		players.add(0, human);
		while(in.hasNextLine()){
			//Now add all of the computer players
			parts = in.nextLine().split(",");
			if(parts.length != 4)
				throw new BadConfigFormatException();
			//Use Integers to parse the x and y values
			try{
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
			} catch(NumberFormatException e){
				System.err.println(e.getLocalizedMessage());
				System.exit(0);
			}
			ComputerPlayer npc = new ComputerPlayer(parts[0],parts[3],x,y);
			players.add(npc);
		}
	}
	public void deal(){
		
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public HumanPlayer getHuman(){
		return human;
	}
	
}