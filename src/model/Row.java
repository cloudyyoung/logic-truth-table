package model;

import application.BooleanUtil;

public class Row {
	private Statement statement;

	/**
	 * Row constructor.
	 * 
	 * @param statement the statement attaching the row
	 */
	public Row(Statement statement) {
		this.setStatement(statement);
	}
	
	/**
	 * Row copy constructor.
	 * 
	 * @param copy the row object to copy
	 */
	public Row(Row copy) {
		this.setStatement(copy.getStatement());
	}

	
	/** 
	 * Sets the statement attaching to the row.
	 * 
	 * @param statement the statement attaching to the row
	 */
	public void setStatement(Statement statement) {
		this.statement = statement.clone();
	}

	
	/** 
	 * Gets the statement attaching to the row.
	 * 
	 * @return the statement attaching to the row
	 */
	public Statement getStatement() {
		return this.statement.clone();
	}


	@Override
	public String toString() {
		String string = "";

		for (Variable letter : this.statement.getVariables()) {
			string += BooleanUtil.getBooleanCap(letter.getValue()) + " ";
		}

		for (Boolean bool : this.statement.getTruthValues()) {
			if (bool != null) {
				string += BooleanUtil.getBooleanCap(bool) + " ";
			} else {
				string += " ";
			}
		}

		return string;
	}
}
