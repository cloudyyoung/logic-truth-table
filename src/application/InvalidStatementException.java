package application;

public class InvalidStatementException extends Exception {
	private static final long serialVersionUID = 8625772753224638130L;

	/**
	 * Invalid statement exception constructor.
	 * 
	 * @param message the exception message
	 */
	public InvalidStatementException(String message) {
        super(message);
    }
}
