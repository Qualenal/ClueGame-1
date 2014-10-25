package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RoomCell extends BoardCell {
	private DoorDirection doorDirection;
	private char roomInitial;
	public static int SQUARE_LENGTH = 30;
	public static int DOOR_THICKNESS = 5;
	public RoomCell(int row, int col, String aString) {
		super(row, col, aString);
		roomInitial = aString.charAt(0);
		if(aString.length() == 1)
			doorDirection = DoorDirection.NONE;
		if(aString.length() == 2){
			if(aString.charAt(1) == 'U')
				doorDirection = DoorDirection.UP;
			else if(aString.charAt(1) == 'D')
				doorDirection = DoorDirection.DOWN;
			else if(aString.charAt(1) == 'L')
				doorDirection = DoorDirection.LEFT;
			else if(aString.charAt(1) == 'R')
				doorDirection = DoorDirection.RIGHT;
			else
				doorDirection = DoorDirection.NONE;
		}
	}

	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE
	};



	public boolean isRoom() {
		return true;
	}

	public void draw(Graphics g, Board b) {
		//Draw Outside Lines
		g.setColor(Color.BLACK);
		if(row == 0)
			g.drawLine(col*SQUARE_LENGTH, 0, col*SQUARE_LENGTH + SQUARE_LENGTH, 0);
		if(row == b.getNumRows()-1)
			g.drawLine(col*SQUARE_LENGTH, SQUARE_LENGTH*b.getNumRows(), col*SQUARE_LENGTH + SQUARE_LENGTH, SQUARE_LENGTH*b.getNumRows());
		if(col == 0)
			g.drawLine(0, row*SQUARE_LENGTH, 0, row*SQUARE_LENGTH + SQUARE_LENGTH);
		if(col == b.getNumColumns()-1)
			g.drawLine(SQUARE_LENGTH*b.getNumColumns(), row*SQUARE_LENGTH, SQUARE_LENGTH*b.getNumColumns(), row*SQUARE_LENGTH + SQUARE_LENGTH);
		//Draw name
		if(cellName.length() == 2 && cellName.charAt(1) == 'N'){
			String s = b.getRooms().get(cellName.charAt(0));
			g.setFont(new Font(s,Font.PLAIN,10));
			g.drawString(s, col*SQUARE_LENGTH + SQUARE_LENGTH/2, row*SQUARE_LENGTH + SQUARE_LENGTH/2);
		}
		//Draw Door
		if(doorDirection != DoorDirection.NONE){
			g.setColor(Color.BLUE);
			if(doorDirection == DoorDirection.UP){
				g.fillRect(col*SQUARE_LENGTH, row*SQUARE_LENGTH, SQUARE_LENGTH, DOOR_THICKNESS);
			}
			if(doorDirection == DoorDirection.RIGHT){
				g.fillRect(col*SQUARE_LENGTH + SQUARE_LENGTH - DOOR_THICKNESS, row*SQUARE_LENGTH, DOOR_THICKNESS, SQUARE_LENGTH);
			}
			if(doorDirection == DoorDirection.LEFT){
				g.fillRect(col*SQUARE_LENGTH, row*SQUARE_LENGTH, DOOR_THICKNESS, SQUARE_LENGTH);
			}
			if(doorDirection == DoorDirection.DOWN){
				g.fillRect(col*SQUARE_LENGTH, row*SQUARE_LENGTH + SQUARE_LENGTH - DOOR_THICKNESS, SQUARE_LENGTH, DOOR_THICKNESS);
			}
		}
	}

	public char getInitial() {
		return roomInitial;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
}
