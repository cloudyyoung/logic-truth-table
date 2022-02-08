package connective;

public class Disjunction extends BinaryConnective {

	public Disjunction() {
		super('\u2228', "\\/");
	}

	
	@Override
	public boolean getValue(boolean left, boolean right) {
		return (left || right);
	}

}
