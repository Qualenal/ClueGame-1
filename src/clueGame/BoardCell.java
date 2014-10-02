package clueGame;

public abstract class BoardCell {
    public String cellName;
    public int row;
    public int col;

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

    public void draw() {

    }

}
