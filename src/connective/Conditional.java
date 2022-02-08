package connective;

import java.util.ArrayList;
import java.util.Arrays;

public class Conditional extends BinaryConnective {

	public Conditional() {
		super('\u2192', new ArrayList<String>(Arrays.asList("->", ">")));
	}

	
	@Override
	public boolean getValue(boolean left, boolean right) {
		return !(left && !right);
	}

}
