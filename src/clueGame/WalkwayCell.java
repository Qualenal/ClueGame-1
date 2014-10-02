package clueGame;

public class WalkwayCell extends BoardCell {

    public WalkwayCell(int row, int col) {
	super(row, col, "W");
    }

    public boolean isWalkway() {
	return true;
    }

    public void draw() {

    }
}
