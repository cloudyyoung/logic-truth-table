package model;

import java.util.ArrayList;

import connective.BinaryConnective;

public class BinaryStatement extends Statement {

	private Statement left;
	private Statement right;
	private BinaryConnective connective;

	/**
	 * Binary statement constructor.
	 * 
	 * @param left the left substatement
	 * @param connective binary connective
	 * @param right the right substatement
	 */
	public BinaryStatement(Statement left, BinaryConnective connective, Statement right) {
		this.setLeft(left);
		this.setRight(right);
		this.setConnective(connective);
	}

	/**
	 * Binary Statement copy constructor.
	 * 
	 * @param copy the Binary Statement object to copy
	 */
	public BinaryStatement(BinaryStatement copy) {
		this(copy.getLeft(), copy.getConnective(), copy.getRight());
	}

	
	/** 
	 * Returns the left substatement.
	 * 
	 * @return Statement
	 */
	public Statement getLeft() {
		return this.left.clone();
	}

	
	/** 
	 * Sets the left substatement.
	 * 
	 * @param left the left substatement
	 */
	public void setLeft(Statement left) {
		this.left = left.clone();
	}

	
	/** 
	 * Returns the right substatement.
	 * 
	 * @return the right substatement
	 */
	public Statement getRight() {
		return this.right.clone();
	}

	
	/** 
	 * Sets the right substatement.
	 * 
	 * @param right the right substatement
	 */
	public void setRight(Statement right) {
		this.right = right.clone();
	}

	
	/** 
	 * Returns the binary connective of the statement.
	 * 
	 * @return the binary connective
	 */
	public BinaryConnective getConnective() {
		return connective;
	}

	
	/** 
	 * Sets the binary connective of the statement.
	 * 
	 * @param connective the binary connective
	 */
	public void setConnective(BinaryConnective connective) {
		this.connective = connective;
	}

	@Override
	public BinaryStatement clone() {
		return new BinaryStatement(this);
	}

	@Override
	public boolean getValue() {
		return this.connective.getValue(this.left.getValue(), this.right.getValue());
	}

	@Override
	public ArrayList<Variable> getVariables() {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		variables.addAll(this.left.getVariables());
		variables.addAll(this.right.getVariables());
		return variables;
	}

	@Override
	public void setVariable(Variable variable) {
		this.left.setVariable(variable);
		this.right.setVariable(variable);
	}

	@Override
	public ArrayList<String> getStringArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("(");
		array.addAll(this.left.getStringArray());
		array.add(this.connective.getDisplaySymbol() + "");
		array.addAll(this.right.getStringArray());
		array.add(")");
		return array;
	}

	@Override
	public ArrayList<Boolean> getTruthValues() {
		ArrayList<Boolean> array = new ArrayList<Boolean>();
		array.add(null);
		array.addAll(this.left.getTruthValues());
		array.add(this.getValue());
		array.addAll(this.right.getTruthValues());
		array.add(null);
		return array;
	}

	@Override
	public String toString() {
		return "(" + this.left + " " + this.connective + " " + this.right + ")";
	}
}
