package model;

import java.util.ArrayList;

public abstract class Statement {

	/** 
	 * Returns a clone of the statement.
	 * 
	 * @return the clone of the statement
	 */
	abstract public Statement clone();

	/** 
	 * Returns the truth value of the statement..
	 * 
	 * @return the boolean truth value of the statement
	 */
	abstract public boolean getValue();

	/** 
	 * Returns an array list of all the variables in the scope of the statement and its substatement. For a variable, it returns an array list which only contains itself.
	 * 
	 * @return the array list of all the variables
	 */
	abstract public ArrayList<Variable> getVariables();

	/** 
	 * Sets the variable which is in the scope of the statement.
	 * 
	 * @param variable the variable to set
	 */
	abstract public void setVariable(Variable variable);

	/** 
	 * Returns the array list of string where each element represents a character in the statement, for display.
	 * 
	 * @return the array list of string
	 */
	abstract public ArrayList<String> getStringArray();

	/** 
	 * Returns the array list of boolean for display.
	 * 
	 * @return the array list of boolean
	 */
	abstract public ArrayList<Boolean> getTruthValues();

	@Override
	abstract public String toString();
}
