package clueGame;

public class RoomCell extends BoardCell {
    public RoomCell(int row, int col, String aString) {
	super(row, col, aString);
    }

    public enum DoorDirection {
	UP, DOWN, LEFT, RIGHT, NONE
    };

//    private DoorDirection doorDirection;
//    private char roomInitial;

    public boolean isRoom() {
	return true;
    }

    public void draw() {

    }

    public char getInitial() {
	return cellName.charAt(0);
    }

    public DoorDirection getDoorDirection() {
	return null;
    }
}
