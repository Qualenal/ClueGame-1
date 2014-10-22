package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
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
	private Solution solution;


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
		//Need a random generator
		Random randomizer = new Random();
		//Need to give the solution 3 cards of each type
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> weaponList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		for(Card c : deck){
			if(c.getType() == CardType.PERSON)
				personList.add(c);
			if(c.getType() == CardType.ROOM)
				roomList.add(c);
			if(c.getType() == CardType.WEAPON)
				weaponList.add(c);
		}
		
		//Add one of each type to the solution and remove from the deck
		Card personSol = personList.get(randomizer.nextInt(personList.size()));
		String person = personSol.getName();
		deck.remove(personSol);
		Card roomSol = roomList.get(randomizer.nextInt(roomList.size()));
		String room = roomSol.getName();
		deck.remove(roomSol);
		Card weaponSol = weaponList.get(randomizer.nextInt(weaponList.size()));
		String weapon = weaponSol.getName();
		deck.remove(weaponSol);
		solution = new Solution(room,weapon,person);
		
		//Enclose the whole code in a while loop so that the deck will empty
		while(!deck.isEmpty()){
			//The plan is to iterate through the player list
			for(Player p : players){
				//We are done if the deck is empty
				if(deck.isEmpty())
					break;
				//Get a random card, add it, and then remove it from the deck
				Card randomCard = deck.get(randomizer.nextInt(deck.size()));
				p.addCard(randomCard);
				deck.remove(randomCard);
			}
		} 
	}
	public Card handleSuggestion(String person, String room, String weapon, Player accusingPlayer){
		return null;
	}
	public boolean checkAccusation(Solution solution){
		if (!this.solution.getPerson().equals(solution.getPerson())) 
			return false;
		
		if(!this.solution.getRoom().equals(solution.getRoom()))
			return false;
		
		if(!this.solution.getWeapon().equals(solution.getWeapon()))
			return false;
		
		return true;
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
	public void setSolution(Solution solution){
		//Testing only
		this.solution = solution;
	}
}