package connective;

import java.util.ArrayList;

public abstract class BinaryConnective extends Connective {

	public BinaryConnective(char displaySymbol, String inputSymbol) {
		super(displaySymbol, inputSymbol);
	}

	public BinaryConnective(char displaySymbol, ArrayList<String> inputSymbols) {
		super(displaySymbol, inputSymbols);
	}

	/**
	 * Returns the truth value of the connective by the given left and right truth values.
	 * 
	 * @param left the left truth value
	 * @param right the right truth value
	 * @return the truth value of the connective
	 */
	public abstract boolean getValue(boolean left, boolean right);

}
