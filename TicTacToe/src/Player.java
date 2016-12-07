import java.util.Random;


public class Player {
	private String name;
	private Symbol symbol;
	
	public Player(String name, Symbol symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public int doMove(Board board) {
		int pos = 0;
		while(board.isOccupied(pos)) {
			pos = new Random().nextInt(9); //TODO: replace with user input
		}
//		System.out.println("Move " + getName() + " " + pos);
		return pos;
	}
}