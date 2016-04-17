package exception;

import main.Partie;

public class SetShipBoxeFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetShipBoxeFullException() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("ERROR: One or several boxes are already full.");
		Partie.waitForEntry();
	}

}
