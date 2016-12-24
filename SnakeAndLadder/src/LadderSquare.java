
public class LadderSquare extends Square{
	private int length;
	
	LadderSquare(int position) {
		super(position);
	}
	
	LadderSquare(int position, int length) {
		super(position);
		this.length = length;
	}
	
	@Override
	public int connectsTo() {
		return this.getPosition() + getLadderLength();
	}
	
	private int getLadderLength() {
		return this.length;
	}
}