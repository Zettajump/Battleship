package grid;

import ship.*;

public class Box {
	private boolean hit;
	private ShipPart shipPart;
	private int colPosition;
	private int rowPosition;
	
	public Box(int colPosition, int rowPosition) {
		super();
		this.colPosition = colPosition;
		this.rowPosition = rowPosition;
		hit = false;
		shipPart = null;
	}

	public BoxeStatus getStatus() {
		if(shipPart == null)
		{
			if(hit == false)
				return BoxeStatus.EMPTY;
			else
				return BoxeStatus.MISSED;
		}
		else
		{
			if(hit == false)
				return BoxeStatus.BOAT;
			else
			{
				if(shipPart.getShip().isDestroyed())
					return BoxeStatus.DESTROYED;
				else
					return BoxeStatus.HIT;
			}
		}
	}	
	
	public String getStringStatus() {
		String res = "";
		BoxeStatus status = getStatus();
		if(status == BoxeStatus.EMPTY)
			res = "-";
		else if(status == BoxeStatus.MISSED)
			res = "O";
		else if(status == BoxeStatus.BOAT)
			res = "B";
		else if(status == BoxeStatus.DESTROYED)
			res = "X";
		else if(status == BoxeStatus.HIT)
			res = "*";
		
		return res;
	}
	
	public boolean isFull() {
		return shipPart != null;
	}
	
	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public ShipPart getShipPart() {
		return shipPart;
	}

	public void setShipPart(ShipPart shipPart) {
		this.shipPart = shipPart;
		shipPart.setBox(this);
		
	}

	public int getColPosition() {
		return colPosition;
	}

	public int getRowPosition() {
		return rowPosition;
	}
}
