
public class SnakeSquare extends Square{
	private int length;
	
	SnakeSquare(int position) {
		super(position);
	}
	
	SnakeSquare(int position, int length) {
		super(position);
		this.length = length;
	}
	
	@Override
	public int connectsTo() {
		return this.getPosition() - getSnakeLength();
	}
	
	private int getSnakeLength() {
		return this.length;
	}
}