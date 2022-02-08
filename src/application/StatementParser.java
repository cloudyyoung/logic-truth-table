package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connective.*;
import model.*;

public class StatementParser {

	/**
	 * Returns the parsed statement object by the given statement string.
	 * 
	 * @param statement the statement string
	 * @return the statement object
	 * @throws InvalidStatementException
	 */
	public static Statement parse(String statement) throws InvalidStatementException {
		// Trim statement
		statement = statement.trim();
		
		// Returns null if statement is blank
		if (statement.isBlank()) {
			throw new InvalidStatementException("Expecting a statement, but a blank statement is given");
		}
		
		// Replace input connective to display connective
		ArrayList<Connective> connectives = Connective.getAllConnectives();
		for (Connective connective : connectives) {
			for (String inputSymbol : connective.getInputSymbols()) {
				statement = statement.replaceAll(Pattern.quote(inputSymbol), "" + connective.getDisplaySymbol());
			}
		}
		
		System.out.println("Statement: " + statement);
		
		// Patterns
		// Binary Brackets 1: (\(.*\))([()∧¬↔∨→])(\(.*\))$
		// Binary Brackets 2: (\(.*\))([()∧¬↔∨→])([A-Za-z])$
		// Binary Brackets 3: ([A-Za-z])([()∧¬↔∨→])(\(.*\))$
		// Binary: ([A-Za-z])([()∧¬↔∨→])([A-Za-z])$
		// Unary Brackets: ([()∧¬↔∨→])(\(.*\))$
		// Unary: ([()∧¬↔∨→])([A-Za-z])$
		// Brackets: ^(\(.*\))$
		// Vriable: ^([A-Za-z])$
		

		ArrayList<Pattern> regex = new ArrayList<Pattern>();
		regex.add(Pattern.compile("^(\\(.*\\))[ ]*([∧¬↔∨→])[ ]*(\\(.*\\))$")); // BB1
		regex.add(Pattern.compile("^(\\(.*\\))[ ]*([∧¬↔∨→])[ ]*([A-Za-z])$")); // BB2
		regex.add(Pattern.compile("^([A-Za-z])[ ]*([∧¬↔∨→])[ ]*(\\(.*\\))$")); // BB3
		regex.add(Pattern.compile("^([A-Za-z])[ ]*([∧¬↔∨→])[ ]*([A-Za-z])$")); // B
		regex.add(Pattern.compile("^([¬])[ ]*(\\(.*\\))$")); // UB
		regex.add(Pattern.compile("^([¬])[ ]*([A-Za-z])$")); // U
		regex.add(Pattern.compile("^(\\(.*\\))$")); // B
		regex.add(Pattern.compile("^([A-Za-z])$")); // V

		// Iterate through each regular expression pattern
		for (Pattern rg : regex) {
			Matcher m = rg.matcher(statement);
			
			// If current pattern matches
			if (m.find()) {
				System.out.println("Regex Pattern: " + rg);

				ArrayList<Statement> statements = new ArrayList<Statement>();
				Connective connective = null;
				
				// For each matching group
				for (int t = 1; t <= m.groupCount(); t++) {
					String group = m.group(t);

					// If this group is a variable
					if (group.matches("[A-Za-z]")) {
						statements.add(new Variable(group.charAt(0)));
					}

					// If this group is a connective
					else if (group.matches("[∧¬↔∨→]")) {
						connective = Connective.getConnective(group.charAt(0));
					}

					// If contains parenthesis
					else if (group.matches("\\(.*\\)")) {
						Statement subStatement = parse(group.substring(1, group.length() - 1));
						statements.add(subStatement);
					}

					// Else
					else {
						throw new InvalidStatementException("Invalid expression, near: " + group);
					}
				}

				// If statement is bracket wrapped
				if (m.groupCount() == 1 && m.group(0).matches("\\(.*\\)") && statements.size() == 1) {
					return statements.get(0);
				}
				
				// If statement is a single variable
				if(m.groupCount() == 1 && statements.size() == 1 && statements.get(0) instanceof Variable) {
					return statements.get(0);
				}

				// If there is no connective
				if (connective == null) {
					throw new InvalidStatementException("Missing connective, near: " + statement);
				}

				// If this is a binary statement
				if (statements.size() == 2) {
					if (connective instanceof BinaryConnective) {
						return new BinaryStatement(statements.get(0), (BinaryConnective) connective, statements.get(1));
					} else {
						throw new InvalidStatementException(
								"Expecting binary connective, near: " + statement);
					}
				}

				// If this is a unary statement
				else if (statements.size() == 1) {
					if (connective instanceof UnaryConnective) {
						return new UnaryStatement((UnaryConnective) connective, statements.get(0));
					} else {
						System.out.println("connective: " + connective);
						System.out.println("true: " + (connective instanceof UnaryConnective));
						throw new InvalidStatementException(
								"Expecting unary connective, near: " + statement);
					}
				}
			}
		}
		
		// Matching no regular expression pattern, invalid statement
		throw new InvalidStatementException("Invalid statement, near: " + statement);
	}
}
