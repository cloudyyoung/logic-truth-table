package connective;

import java.util.ArrayList;

public abstract class Connective {

	/* Official symbol in the field of logic to display */
	private char displaySymbol;
	
	/* Equivalent input symbol to recognize as the connective from user input */
	private ArrayList<String> inputSymbols;

	public Connective(char displaySymbol, String inputSymbol) {
		this.displaySymbol = displaySymbol;
		this.inputSymbols = new ArrayList<String>();
		this.inputSymbols.add(inputSymbol);
	}

	public Connective(char displaySymbol, ArrayList<String> inputSymbols) {
		this.displaySymbol = displaySymbol;
		this.inputSymbols = inputSymbols;
	}

	
	/** 
	 * Returns the official logical symbol for display.
	 * 
	 * @return the display symbol
	 */
	public char getDisplaySymbol() {
		return this.displaySymbol;
	}

	
	/** 
	 * Returns the default (first) input symbol of all input symbols of the connective.
	 * 
	 * @return the first symbol
	 */
	public String getInputSymbol() {
		return this.inputSymbols.get(0);
	}

	
	/** 
	 * Returns all the input symbols of the connective.
	 * 
	 * @return the array list contains all the input symbols
	 */
	public ArrayList<String> getInputSymbols() {
		ArrayList<String> inputSymbols = new ArrayList<String>();
		for (String inputSymbol : this.inputSymbols) {
			inputSymbols.add(inputSymbol);
		}
		return inputSymbols;
	}


	@Override
	public String toString() {
		return "" + this.displaySymbol;
	}

	
	/** 
	 * Returns an array list which contains a single instance of each connective.
	 * 
	 * @return an array list of all connectives
	 */
	public static ArrayList<Connective> getAllConnectives() {
		ArrayList<Connective> connectives = new ArrayList<Connective>();
		connectives.add(new Biconditional());
		connectives.add(new Conditional());
		connectives.add(new Conjunction());
		connectives.add(new Disjunction());
		connectives.add(new Negation());
		return connectives;
	}

	
	/** 
	 * Returns the matching connective instance by the given input symbol.
	 * 
	 * @param the input symbol
	 * @return the connective instance
	 */
	public static Connective getConnective(String inputSymbol) {
		for (Connective connective : getAllConnectives()) {
			System.out.println(connective);
			if (connective.getInputSymbols().contains(inputSymbol)) {
				return connective;
			}
		}
		return null;
	}

	
	/** 
	 * Returns the matching connective instance by the given display symbol.
	 * 
	 * @param displaySymbol the display symbol
	 * @return the connective instance
	 */
	public static Connective getConnective(char displaySymbol) {
		for (Connective connective : getAllConnectives()) {
			if (connective.getDisplaySymbol() == displaySymbol) {
				return connective;
			}
		}
		return null;
	}

}
