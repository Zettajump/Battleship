package ship;

public abstract class Ship {
	private String type;
	private int size;
	private ShipPart[] shipParts;
	
	public Ship(String type, int size) {
		super();
		this.type = type;
		this.size = size;
		this.shipParts = new ShipPart[size];
		for(int i=0; i<size; i++)
			this.shipParts[i] = new ShipPart(this);
	}
	
	public boolean isDestroyed() {
		boolean destroyed = true;
		for(ShipPart p : shipParts) {
			if(p.isHit() == false)
				destroyed = false;
		}
		return destroyed;
	}
	
	
	public String getType() {
		return type;
	}

	public int getSize() {
		return size;
	}

	public ShipPart getShipParts(int i) {
		if(i < 0 || i > size)
			return null;
		else
			return shipParts[i];
	}
	
	public String toString() {
		return type+", size = "+size;
	}
}
