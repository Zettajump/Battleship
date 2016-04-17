package grid;

import exception.SetShipBoxeFullException;
import exception.SetShipDirectionOverflowException;
import exception.SetShipPositionOverflowException;
import ship.*;

public class Grid {
	private final int colsSize = 10;
	private final int rowsSize = 10;
	private Box[][] boxes;
	
	public Grid() {
		super();
		boxes = new Box[colsSize][rowsSize];
		
		for(int i=0; i < colsSize; i++)
		{
			for(int y=0; y < rowsSize; y++)
			{
				boxes[i][y] = new Box(i, y);
			}
		}
	}
	
	public void displayPrimaryGrid() {
		String res = "    ";

		System.out.println("      ----- Primary Grid ----- \n");
		
		for(int i=0; i < rowsSize; i++) res += " "+(char) (i+65)+" ";
		res += "\n";
		
		for(int y=0; y < rowsSize; y++) {
			res += " " +(y+1)+ " ";
			if (y+1 < 10) res += " ";
			
			for(int i=0; i < colsSize; i++) 
			{
				res += " " +boxes[i][y].getStringStatus()+ " ";
			}
			res += "\n";
		}
		
		System.out.println(res);
	}
	
	public void displayTrackingGrid() {
		String res = "\t\t\t\t    ";
		String status;
		

		System.out.println("\t\t\t\t\t-------------- Tracking Grid --------------\n");
		
		for(int i=0; i < rowsSize; i++) res += "    "+(char) (i+65);
		res += "\n\n\t\t\t\t";
		
		for(int y=0; y < rowsSize; y++) {
			res += "  " +(y+1)+ "  ";
			if (y+1 < 10) res += " ";
			
			for(int i=0; i < colsSize; i++) 
			{
				status = boxes[i][y].getStringStatus();
				if(status == "B") status = "-";
				res += "  " +status+ "  ";
			}
			res += "\n\n\t\t\t\t";
		}
		
		System.out.println(res);
	}
	
	public boolean setShip(Ship ship, int col, int row, Direction direction){
		if(this.setShipCheckPosition(ship, col, row, direction) == false)
			return false;
		
		for(int i=0; i < ship.getSize(); i++) {
			boxes[col][row].setShipPart(ship.getShipParts(i));
			
			if(direction == Direction.Top) row--;
			else if(direction == Direction.Bottom) row++;
			else if(direction == Direction.Left) col--;
			else col++;
		}
		
		return true;
	}
	
	private boolean setShipCheckPosition(Ship ship, int col, int row, Direction direction) {
		
		try {
			if(col < 0 || col >= colsSize)
				throw new SetShipPositionOverflowException();
			if(row < 0 || row >= rowsSize)
				throw new SetShipPositionOverflowException();
		} catch(SetShipPositionOverflowException e) {
			return false;
		}
		
		try {
			if(direction == Direction.Top)
			{
				if(row+1-ship.getSize() < 0) {
					System.out.println("Test 1");
					throw new SetShipDirectionOverflowException();
				}
			}
			else if(direction == Direction.Bottom)
			{
				if(row+ship.getSize() > rowsSize) {
					System.out.println("Test 2");
					throw new SetShipDirectionOverflowException();
				}
			}
			else if(direction == Direction.Left)
			{
				if(col+1-ship.getSize() < 0) {
					System.out.println("Test 3");
					throw new SetShipDirectionOverflowException();
				}
			}
			else
			{
				if(col+ship.getSize() > colsSize) {
					System.out.println("Test 4");
					throw new SetShipDirectionOverflowException();
				}
			}
		} catch(SetShipDirectionOverflowException e) {
			return false;
		}
		
		try {
			for(int i=0; i < ship.getSize(); i++) {
				if(boxes[col][row].isFull())
					throw new SetShipBoxeFullException();
				
				if(direction == Direction.Top) row--;
				else if(direction == Direction.Bottom) row++;
				else if(direction == Direction.Left) col--;
				else col++;
			}
		} catch(SetShipBoxeFullException e) {
			return false;
		}
		return true;
	}
	
	public char[] getColEntriesPossible() {
		char[] res = new char[this.colsSize];
		for(int i=0; i<this.colsSize; i++)
			res[i] = (char) (i+65);
		return res;
	}
	
	public int[] getRowEntriesPossible() {
		int[] res = new int[this.rowsSize];
		for(int i=0; i<this.rowsSize; i++)
			res[i] = i+1;
		return res;
	}
	
	public int getColsSize() {
		return colsSize;
	}
	public int getRowsSize() {
		return rowsSize;
	}
	public Box getBox(int col, int row) {
		return boxes[col][row];
	}
}
