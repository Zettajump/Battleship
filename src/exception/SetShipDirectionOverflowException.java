package exception;

import main.Partie;

public class SetShipDirectionOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetShipDirectionOverflowException() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("ERROR: The direction you choosed make the ship overflow the grid.");
		Partie.waitForEntry();
	}

}
