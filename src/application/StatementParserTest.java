package application;

import static org.junit.jupiter.api.Assertions.*;

import model.*;

import org.junit.jupiter.api.Test;

import connective.*;

class StatementParserTest {

	@Test
	void test1() {
		// Test root class
		Statement root_raw = null;
		try {
			root_raw = StatementParser.parse("(A /\\ B) -> C");
		} catch (Exception e) {
			fail("Exception thrown");
		}

		assertNotNull(root_raw);
		assertEquals(root_raw.getClass(), BinaryStatement.class);

		// Convert root class
		BinaryStatement root = (BinaryStatement) root_raw;

		// Test connective
		Connective root_connective = root.getConnective();
		assertEquals(root_connective.getClass(), Conditional.class);

		// Test left and right children class
		Statement root_left_raw = root.getLeft();
		Statement root_right_raw = root.getRight();
		assertNotNull(root_left_raw);
		assertNotNull(root_right_raw);
		assertEquals(root_left_raw.getClass(), BinaryStatement.class);
		assertEquals(root_right_raw.getClass(), Variable.class);

		// Convert left and right children class
		BinaryStatement root_left = (BinaryStatement) root_left_raw;
		Variable root_right = (Variable) root_right_raw;
		assertEquals(root_left.getClass(), BinaryStatement.class);
		assertEquals(root_right.getLetter(), 'C');

		// Test left child connective
		Connective root_left_connective = root_left.getConnective();
		assertEquals(root_left_connective.getClass(), Conjunction.class);

		// Test left child left and right children class
		Statement root_left_left_raw = root_left.getLeft();
		Statement root_left_right_raw = root_left.getRight();
		assertNotNull(root_left_left_raw);
		assertNotNull(root_left_right_raw);
		assertEquals(root_left_left_raw.getClass(), Variable.class);
		assertEquals(root_left_right_raw.getClass(), Variable.class);

		// Convert left child left and right children class
		Variable root_left_left = (Variable) root_left_left_raw;
		Variable root_left_right = (Variable) root_left_right_raw;
		assertEquals(root_left_left.getLetter(), 'A');
		assertEquals(root_left_right.getLetter(), 'B');
	}

	@Test
	void test2() {
		// Test root class
		try {
			Statement s = StatementParser.parse("-B");
			UnaryStatement s2 = (UnaryStatement) s;
			assertEquals(s2.getConnective().getClass(), Negation.class);

			Statement a = s2.getSubstatement();
			Variable av = (Variable) a;
			assertEquals(av.getLetter(), 'B');
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void test3() {
		// Test root class
		try {
			StatementParser.parse("A ? B");
		} catch (Exception e) {
			return;
		}

		fail("Should throw exception");
	}

	@Test
	void test4() {
		// Test root class
		try {
			Statement s = StatementParser.parse("(A /\\ B) -> (B /\\ C)");
			if (s instanceof BinaryStatement) {
				BinaryStatement ds = (BinaryStatement) s;

				Connective b = ds.getConnective();
				assertEquals(b.getInputSymbol(), "->");
			} else {
				fail("Statement root should be binary");
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	void test5() {
		// Test root class
		try {
			StatementParser.parse("(A /\\ B) -> (C \\/ DDD)");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		fail("Should throw exception");
	}

	@Test
	void test6() {
		// Test root class
		try {
			StatementParser.parse("(A /\\ B)");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
