package main;

import java.util.Scanner;

import grid.Box;
import grid.PrimaryGrid;
import ship.*;

public class Player {
	private String name;
	private PrimaryGrid primaryGrid;
	private int nbShips;
	private Ship[] ships;
	
	private Scanner sc = new Scanner(System.in);

	public Player(String name) {
		super();
		this.name = name;
		primaryGrid = new PrimaryGrid();
		nbShips = 5;
		
		ships = new Ship[nbShips];
		ships[0] = new AircraftCarrier();
		ships[1] = new Battleship();
		ships[2] = new Submarine();
		ships[3] = new Destroyer();
		ships[4] = new PatrolBoat();
	}
	
	public void setShips() {
		int col, row;
		Direction dir;
		
		System.out.println("Here is the list of your ships : ");
		for(Ship s : ships)
			System.out.println(s.toString());
		System.out.println();
		
		for(Ship s : ships) {
			do {
				this.displayPrimaryGrid();
				System.out.println(s.toString());
				
				col = this.selectColPosition();
				row = this.selectRowPosition();
				dir = this.selectDirection();

			} while(this.primaryGrid.setShip(s, col, row, dir) == false);
			
		}
		System.out.println(name+" Primary grid :\n");
		this.displayPrimaryGrid();
		Partie.waitForEntry();
		Partie.clearConsole();
	}
	
	public  int selectColPosition() {
		int colInt;
		char col;
		do {
			System.out.println(this.name+", enter X position (ex: A) : ");
			col = sc.next().charAt(0);
		} while(this.checkColEntry(col) == false);
		colInt = ((int) col) -65;
		return colInt;
	}
	
	public int selectRowPosition() {
		int row;
		do {
			System.out.println(this.name+", enter Y position (ex: 1) : ");
			row = sc.nextInt();
		} while(this.checkRowEntry(row) == false);
		row--;
		return row;
	}
	
	public Direction selectDirection() {
		Direction dir;
		int dirInt;
		do {
			System.out.println(this.name+", enter direction : ");
			System.out.println("\t1: Top");
			System.out.println("\t2: Right");
			System.out.println("\t3: Bottom");
			System.out.println("\t4: Left");
			dirInt = sc.nextInt();
		} while(this.checkDirectionEntry(dirInt) == false);
		
		if(dirInt == 1) dir = Direction.Top;
		else if(dirInt == 2) dir = Direction.Right;
		else if(dirInt == 3) dir = Direction.Bottom;
		else dir = Direction.Left;
		
		return dir;
	}
	
	private boolean checkColEntry(char col) {
		for(char c : this.primaryGrid.getColEntriesPossible()) {
			if(c == col) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRowEntry(int row) {
		for(int i : this.primaryGrid.getRowEntriesPossible()) {
			if(i == row) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkDirectionEntry(int direction) {
		return ((direction >= 1) && (direction <= 4));
	}
	
	public void displayPrimaryGrid() {
		this.primaryGrid.displayPrimaryGrid();
	}
	
	public void displayTrackingGrid() {
		this.primaryGrid.displayTrackingGrid();
	}
	
	public boolean hasAnyShipLeft() {
		boolean res = false;
		
		for(Ship s : ships) {
			if(s.isDestroyed() == false) {
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	public boolean shoot(Player target, int col, int row) {
		return target.getShot(col, row);
	}
	
	public boolean getShot(int col, int row) {
		Box box = this.primaryGrid.getBox(col, row);
		box.setHit(true);
		return box.isFull();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PrimaryGrid getPrimaryGrid() {
		return primaryGrid;
	}
	
	public Ship getShip(int i)
	{
		return ships[i];
	}

	public Ship[] getShips() {
		return ships;
	}
	
	
}
