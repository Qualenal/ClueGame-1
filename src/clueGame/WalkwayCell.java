package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	public static int SQUARE_LENGTH = 30;
	
    public WalkwayCell(int row, int col) {
	super(row, col, "W");
    }

    public boolean isWalkway() {
	return true;
    }

    public void draw(Graphics g, Board b) {
    	g.setColor(Color.YELLOW);
    	g.fillRect(col*SQUARE_LENGTH, row*SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH);
    	
    	g.setColor(Color.BLACK);
    	g.drawRect(col*SQUARE_LENGTH, row*SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH);

    	
    }
}
