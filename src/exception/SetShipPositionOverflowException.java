package exception;

import main.Partie;

public class SetShipPositionOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetShipPositionOverflowException() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("The position you choosed overflow the grid.");
		Partie.waitForEntry();
	}

}
