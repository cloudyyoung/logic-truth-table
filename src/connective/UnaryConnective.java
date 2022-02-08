package connective;

import java.util.ArrayList;

public abstract class UnaryConnective extends Connective {

	public UnaryConnective(char displaySymbol, String inputSymbol) {
		super(displaySymbol, inputSymbol);
	}

	public UnaryConnective(char displaySymbol, ArrayList<String> inputSymbols) {
		super(displaySymbol, inputSymbols);
	}

	/**
	 * Returns the truth value of the connective by the given truth value.
	 * 
	 * @param value the truth value
	 * @return the truth value of the connective
	 */
	public abstract boolean getValue(boolean value);
}
