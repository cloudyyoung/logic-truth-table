package connective;

import java.util.ArrayList;
import java.util.Arrays;

public class Biconditional extends BinaryConnective {

	public Biconditional() {
		super('\u2194', new ArrayList<String>(Arrays.asList("<->", "<>")));
	}


	@Override
	public boolean getValue(boolean left, boolean right) {
		return (left == right);
	}
}
