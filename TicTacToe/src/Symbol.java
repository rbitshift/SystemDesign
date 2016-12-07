
public enum Symbol {
	NONE, 
	CROSS, 
	CIRCLE;
	
	@Override
	public String toString() {
	    switch(this) {
	    	case NONE: return ".";
	    	case CROSS: return "X";
	    	case CIRCLE: return "O";
	    	default: throw new IllegalArgumentException();
	    }
	}
}