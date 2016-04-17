package ship;

import grid.Box;

public class ShipPart {
	private Ship ship;
	private Box box;
	
	public ShipPart(Ship ship) {
		super();
		this.ship = ship;
		box = null;
	}

	public Ship getShip() {
		return ship;
	}

	public boolean isHit() {
		if(box != null)
			return box.isHit();
		else
			return false;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
}
