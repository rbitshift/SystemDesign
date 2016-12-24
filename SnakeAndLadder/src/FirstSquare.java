
public class FirstSquare extends Square{
	FirstSquare(int position) {
		super(position);
	}
	
	@Override
	public boolean isFirst() {
		return true;
	}
	
	@Override
	public boolean isOccupied() {
		return false;
	}
}