package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import connective.BinaryConnective;
import connective.Conditional;
import connective.Conjunction;
import connective.Disjunction;

import java.util.ArrayList;

class TableTest {

	private static Statement root;

	
	/** 
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Statement a = new Variable('A');
		Statement b = new Variable('B');
		BinaryConnective and = new Conjunction();
		Statement c = new BinaryStatement(a, and, b);

		Statement d = new Variable('D');
		BinaryConnective conditional = new Conditional();
		Statement e = new BinaryStatement(c, conditional, d);

		Statement g = new Variable('G');
		BinaryConnective or = new Disjunction();
		Statement f = new BinaryStatement(e, or, g);

		root = f;
	}

	@Test
	void test() {
		Table t = new Table(root);
		ArrayList<Variable> letters = t.getVariables();
		assertEquals("4 letters", 4, letters.size());

		ArrayList<Row> rows = t.getRows();
		int i = 0;
		for (Row row : rows) {
			if (i == 3) {
				assertEquals("row 3 should be false", false, row.getStatement().getValue());
			} else {
				assertEquals("rows should be true", true, row.getStatement().getValue());
			}

			i++;
		}

		System.out.println(t);
	}

}
