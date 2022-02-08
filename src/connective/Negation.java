package connective;

import java.util.ArrayList;
import java.util.Arrays;

public class Negation extends UnaryConnective {

	public Negation() {
		super('\u00AC', new ArrayList<String>(Arrays.asList("-", "~")));
	}

	
	@Override
	public boolean getValue(boolean value) {
		return !value;
	}

}
