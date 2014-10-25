package clueGame;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.*;

public class Board extends JPanel{
	public BoardCell[][] grid;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;

	public Map<BoardCell, LinkedList<BoardCell>> adjMtx = new LinkedHashMap<BoardCell, LinkedList<BoardCell>>(); // =
	private LinkedList<BoardCell> targets = new LinkedList<BoardCell>();
	private boolean[][] visited;

	// private BoardCell startingCell;

	public Board() {
		rooms = new LinkedHashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
	}

	public BoardCell getCellAt(int row, int col) {
		if (row == -1 || col == -1 || row + 1 > numRows || col + 1 > numColumns) {
			return null;
		}
		return grid[row][col];
	}

	public void calcAdjacencies() {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				LinkedList<BoardCell> tempList = new LinkedList<BoardCell>();

				if (getCellAt(row, col).isWalkway()
						|| getCellAt(row, col).isDoorway()) {
					if (getCellAt(row - 1, col) != null) {
						if (getCellAt(row - 1, col).isWalkway()
								|| (getCellAt(row - 1, col).cellName.length() == 2 && getCellAt(
										row - 1, col).cellName.endsWith("D"))) {
							tempList.add(getCellAt(row - 1, col));
						}
					}

					if (getCellAt(row + 1, col) != null) {
						if (getCellAt(row + 1, col).isWalkway()
								|| (getCellAt(row + 1, col).cellName.length() == 2 && getCellAt(
										row + 1, col).cellName.endsWith("U"))) {
							tempList.add(getCellAt(row + 1, col));
						}
					}

					if (getCellAt(row, col - 1) != null) {
						if (getCellAt(row, col - 1).isWalkway()
								|| (getCellAt(row, col - 1).cellName.length() == 2 && getCellAt(
										row, col - 1).cellName.endsWith("R"))) {
							tempList.add(getCellAt(row, col - 1));
						}
					}

					if (getCellAt(row, col + 1) != null) {
						if (getCellAt(row, col + 1).isWalkway()
								|| (getCellAt(row, col + 1).cellName.length() == 2 && getCellAt(
										row, col + 1).cellName.endsWith("L"))) {
							tempList.add(getCellAt(row, col + 1));
						}
					}
				}

				adjMtx.put(getCellAt(row, col), tempList);
			}
		}
	}

	private void findAllTargets(BoardCell thisCell, int numSteps) {
		LinkedList<BoardCell> adjacentCells = new LinkedList<BoardCell>();

		for (BoardCell c : getAdjList(thisCell.row, thisCell.col)) {
			if (visited[c.row][c.col] == false) {
				adjacentCells.add(c);
			}
		}

		for (BoardCell c : adjacentCells) {
			visited[c.row][c.col] = true;

			if (numSteps == 1) {
				targets.add(c);
			} else if (c.isDoorway()) {
				targets.add(c);
			} else {
				findAllTargets(getCellAt(c.row, c.col), numSteps - 1);
			}
			visited[c.row][c.col] = false;
		}
	}

	public void calcTargets(int row, int col, int diceRoll) {
		targets = new LinkedList<BoardCell>();
		visited = new boolean[numRows][numColumns];
		for (boolean[] r : visited) {
			Arrays.fill(r, false);
		}

		visited[row][col] = true;
		findAllTargets(getCellAt(row, col), diceRoll);
	}

	public Set<BoardCell> getTargets() {
		return new HashSet<BoardCell>(targets);
	}

	public LinkedList<BoardCell> getAdjList(int row, int col) {
		return adjMtx.get(getCellAt(row, col));
	}

	public void loadBoardConfig(String mapFile) throws BadConfigFormatException {
		Scanner foo = null;
		try {
			foo = new Scanner(new File(mapFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<BoardCell> list = new ArrayList<BoardCell>();
		Set<Integer> aSet = new LinkedHashSet<Integer>();
		int rows = 0;
		int cols = 0;
		while (foo.hasNextLine()) {
			String line = foo.nextLine();
			String[] bar = line.split(",");

			cols = bar.length;
			aSet.add(cols);
			for (int i = 0; i < cols; i++) {
				if (!rooms.containsKey(bar[i].charAt(0))) {
					throw new BadConfigFormatException();
				}
				if (bar[i].equals("W")) {
					list.add(new WalkwayCell(rows, i));
				} else {
					list.add(new RoomCell(rows, i, bar[i]));
				}
			}
			rows++;
		}
		foo.close();
		if (aSet.size() != 1) {
			throw new BadConfigFormatException();
		}
		numColumns = cols;
		numRows = rows;

		grid = new BoardCell[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = list.get((i * numColumns) + j);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++){
				grid[i][j].draw(g,this);
			}
		}
	}

	public RoomCell getRoomCellAt(int rows, int cols) {
		return (RoomCell) grid[rows][cols];
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Character, String> rooms) {
		this.rooms = rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
	
	

}
