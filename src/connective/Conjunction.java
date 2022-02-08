package connective;

import java.util.ArrayList;
import java.util.Arrays;

public class Conjunction extends BinaryConnective {

	public Conjunction() {
		super('\u2227', new ArrayList<String>(Arrays.asList("/\\", "&")));
	}

	
	@Override
	public boolean getValue(boolean left, boolean right) {
		return left && right;
	}

}
