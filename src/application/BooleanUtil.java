package application;

public class BooleanUtil {

	
	/** 
	 * Returns character T or F depending on the given boolean truth value.
	 * 
	 * @param bool the boolean truth value
	 * @return the truth value character T or F
	 */
	public static char getBooleanCap(boolean bool) {
		return (bool) ? 'T' : 'F';
	}

}
