package model;

import java.util.ArrayList;

public class Variable extends Statement {

	private char letter;
	private boolean value;

	/**
	 * Variable constructor
	 * 
	 * @param letter The letter of the variable
	 */
	public Variable(char letter) {
		this(letter, false);
	}

	/**
	 * Variable constructor
	 * 
	 * @param letter The letter of the variable
	 * @param value The truth value of the variable
	 */
	public Variable(char letter, boolean value) {
		this.setLetter(letter);
		this.setValue(value);
	}

	/**
	 * Variable copy constructor
	 * 
	 * @param copy The variable object to copy
	 */
	public Variable(Variable copy) {
		this(copy.getLetter(), copy.getValue());
	}

	
	/** 
	 * Returns the letter of the variable
	 * 
	 * @return The letter character
	 */
	public char getLetter() {
		return this.letter;
	}

	
	/** 
	 * Sets the letter of the variable
	 * 
	 * @param letter The letter character
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	
	/** 
	 * Sets the truth value of the variable
	 * 
	 * @param value the boolean truth value
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	
	/** 
	 * Returns the clone of the variable
	 * 
	 * @return Variable The clone of the variable
	 */
	@Override
	public Variable clone() {
		return new Variable(this);
	}

	
	/** 
	 * Returns the truth value of the variable
	 * 
	 * @return The boolean truth value of the variable
	 */
	@Override
	public boolean getValue() {
		return this.value;
	}

	
	@Override
	public ArrayList<Variable> getVariables() {
		ArrayList<Variable> array = new ArrayList<Variable>();
		array.add(this.clone());
		return array;
	}

	
	@Override
	public void setVariable(Variable variable) {
		if (variable.getLetter() == this.letter) {
			this.value = variable.getValue();
		}
	}

	
	@Override
	public ArrayList<String> getStringArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add(this.letter + "");
		return array;
	}

	
	@Override
	public ArrayList<Boolean> getTruthValues() {
		ArrayList<Boolean> array = new ArrayList<Boolean>();
		array.add(this.getValue());
		return array;
	}

	
	@Override
	public String toString() {
		return "" + this.letter;
	}

}
