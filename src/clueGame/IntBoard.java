package clueGame;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
    private Map<BoardCell, LinkedList<BoardCell>> adjMtx = new LinkedHashMap<BoardCell, LinkedList<BoardCell>>(); // =
														  // new
    private LinkedList<BoardCell> targets = new LinkedList<BoardCell>();
    private LinkedList<BoardCell> visited = new LinkedList<BoardCell>();
    public BoardCell[][] grid;
    private static final int ROW = 4;
    private static final int COL = 4;

    public IntBoard() {
	grid = new BoardCell[ROW][COL];
	/*for (int i = 0; i < ROW; i++) {
	    for (int j = 0; j < COL; j++) {
		grid[i][j] = new BoardCell(i, j);
	    }
	}*/
	calcAdjacencies();
    }

    public BoardCell getCell(int row, int col) {
	return grid[row][col];
    }

    public void calcAdjacencies() {
	for (int row = 0; row < ROW; row++) {
	    for (int col = 0; col < COL; col++) {
		LinkedList<BoardCell> tempBoard = new LinkedList<BoardCell>();
		switch (row) {
		case 0:
		    tempBoard.add(getCell(row + 1, col));
		    break;
		case ROW - 1:
		    tempBoard.add(getCell(row - 1, col));
		    break;
		default:
		    tempBoard.add(getCell(row + 1, col));
		    tempBoard.add(getCell(row - 1, col));
		    break;
		}
		switch (col) {
		case 0:
		    tempBoard.add(getCell(row, col + 1));
		    break;
		case COL - 1:
		    tempBoard.add(getCell(row, col - 1));
		    break;
		default:
		    tempBoard.add(getCell(row, col + 1));
		    tempBoard.add(getCell(row, col - 1));
		    break;
		}
		adjMtx.put(getCell(row, col), tempBoard);
	    }
	}
    }

    public void findAllTargets(BoardCell thisCell, int numSteps) {
	LinkedList<BoardCell> adjacentCells = getAdjList(thisCell);
	for (int i = 0; i < adjacentCells.size(); i++) {
	    BoardCell adjCell = adjacentCells.get(i);
	    if (numSteps == 1) {
		targets.add(adjCell);
	    } else {
		calcTargets(adjCell, numSteps - 1);
	    }
	    visited.remove(adjCell);
	}
    }

    public void calcTargets(BoardCell cell, int diceRoll) {
	visited.add(getCell(cell.row, cell.col));
	findAllTargets(cell, diceRoll);
    }

    public Set<BoardCell> getTargets() {
	HashSet<BoardCell> targets = new HashSet<BoardCell>(this.targets);
	return targets;
    }

    public LinkedList<BoardCell> getAdjList(BoardCell cell) {
	return adjMtx.get(cell);
    }
}
