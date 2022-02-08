package model;

import java.util.ArrayList;

import application.BooleanUtil;

public class Table {

	private Statement root;

	/**
	 * Table constructor.
	 * 
	 * @param root the root statement of the table
	 */
	public Table(Statement root) {
		this.setRoot(root);
	}

	/**
	 * Table copy constructor.
	 * 
	 * @param copy the table to copy
	 */
	public Table(Table copy) {
		this(copy.getRoot());
	}

	
	/** 
	 * Returns the root statement of the table.
	 * 
	 * @return the root statement
	 */
	public Statement getRoot() {
		return this.root.clone();
	}

	
	/** 
	 * Sets the root statement of the table.
	 * 
	 * @param root the root statement
	 */
	public void setRoot(Statement root) {
		this.root = root.clone();
	}

	
	/** 
	 * Returns all the rows in the table.
	 * 
	 * @return an array list contains all the rows
	 */
	public ArrayList<Row> getRows() {
		return this.getCompleteTable(this.root.getVariables(), 0, new Row(this.root));
	}

	
	/** 
	 * Returns all the variables appearing in the root statement.
	 * 
	 * @return an array list contains all the variables
	 */
	public ArrayList<Variable> getVariables() {
		return this.root.getVariables();
	}

	
	/** 
	 * Returns the complete table 
	 * 
	 * @param variables the list of variables
	 * @param variableIndex the current variable index in the list of variables
	 * @param row the current row
	 * @return an array list of all the rows for the table
	 */
	private ArrayList<Row> getCompleteTable(ArrayList<Variable> variables, int variableIndex, Row row) {
		ArrayList<Row> rows = new ArrayList<Row>();

		if (variableIndex >= variables.size()) {
			rows.add(row);
			return rows;
		}

		Variable letter = variables.get(variableIndex);

		Row trueRow = new Row(row);
		letter.setValue(true);
		trueRow.getStatement().setVariable(letter);
		rows.addAll(this.getCompleteTable(variables, variableIndex + 1, trueRow));

		Row falseRow = new Row(row);
		letter.setValue(false);
		falseRow.getStatement().setVariable(letter);
		rows.addAll(this.getCompleteTable(variables, variableIndex + 1, falseRow));

		return rows;
	}

	
	@Override
	public String toString() {
		String string = "";

		for (Variable variable : this.getVariables()) {
			string += variable + " ";
		}

		string += this.root + "\n";

		for (Row row : this.getRows()) {
			string += row + " " + BooleanUtil.getBooleanCap(row.getStatement().getValue()) + "\n";
		}

		return string;
	}

}
