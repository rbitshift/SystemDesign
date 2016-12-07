
public class Box {
	private Symbol symbol;
	
	public Box() {
		this.symbol = Symbol.NONE;
	}
	
	public boolean isOccupied() {
		return this.symbol != Symbol.NONE;
	}
	
	public void setOccupied(Symbol symbol) {
		this.symbol = symbol;
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}	
}