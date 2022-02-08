package model;

import java.util.ArrayList;

import connective.UnaryConnective;

public class UnaryStatement extends Statement {

	private Statement substatement;
	private UnaryConnective connective;

	/**
	 * Unary Statement constructor.
	 * 
	 * @param connective the unary connective in the statement
	 * @param substatement the substatement
	 */
	public UnaryStatement(UnaryConnective connective, Statement substatement) {
		this.setSubstatement(substatement);
		this.setConnective(connective);
	}

	/**
	 * Unary statement copy constructor.
	 * 
	 * @param copy
	 */
	public UnaryStatement(UnaryStatement copy) {
		this(copy.getConnective(), copy.getSubstatement());
	}

	
	/** 
	 * Returns the substatement.
	 * 
	 * @return the substatement
	 */
	public Statement getSubstatement() {
		return this.substatement.clone();
	}

	
	/** 
	 * Sets the substatement.
	 * 
	 * @param the substatement
	 */
	public void setSubstatement(Statement substatement) {
		this.substatement = substatement.clone();
	}

	
	/** 
	 * Returns the unary connective of the statement
	 * 
	 * @return the unary connective of the statement
	 */
	public UnaryConnective getConnective() {
		return this.connective;
	}

	
	/** 
	 * Sets the unary connective of the statement
	 * 
	 * @param the unary connective of the statement
	 */
	public void setConnective(UnaryConnective connective) {
		this.connective = connective;
	}

	
	@Override
	public UnaryStatement clone() {
		return new UnaryStatement(this);
	}

	
	@Override
	public boolean getValue() {
		if (this.connective != null) {
			return this.connective.getValue(this.substatement.getValue());
		} else {
			return this.substatement.getValue();
		}
	}

	
	@Override
	public ArrayList<Variable> getVariables() {
		return this.substatement.getVariables();
	}

	
	@Override
	public void setVariable(Variable variable) {
		this.substatement.setVariable(variable);
	}

	
	@Override
	public ArrayList<String> getStringArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("(");
		array.add(this.connective.getDisplaySymbol() + "");
		array.addAll(this.substatement.getStringArray());
		array.add(")");
		return array;
	}

	
	@Override
	public ArrayList<Boolean> getTruthValues() {
		ArrayList<Boolean> array = new ArrayList<Boolean>();
		array.add(null);
		array.add(this.getValue());
		array.addAll(this.substatement.getTruthValues());
		array.add(null);
		return array;
	}

	
	@Override
	public String toString() {
		return (this.connective != null) ? "" + this.connective + this.substatement : "" + this.substatement;
	}

}
