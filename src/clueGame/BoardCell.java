package clueGame;

import java.awt.Graphics;

public abstract class BoardCell {
    public String cellName;
    public int row;
    public int col;
    public static int SQUARE_LENGTH = 30;
    
    public BoardCell(int row, int col, String boardString) {
	this.row = row;
	this.col = col;
	this.cellName = boardString;
    }

    public boolean isWalkway() {
	return false;
    }

    public boolean isRoom() {
	return false;
    }

    public boolean isDoorway() {
	if (cellName.length() == 2
		&& (cellName.charAt(1) == 'D' || cellName.charAt(1) == 'L'
			|| cellName.charAt(1) == 'R' || cellName.charAt(1) == 'U'))
	    return true;
	else
	    return false;
    }

    @Override
    public String toString() {
	return "(" + row + ", " + col + ")" + " " + cellName;
    }

    public abstract void draw(Graphics g, Board b);

}
