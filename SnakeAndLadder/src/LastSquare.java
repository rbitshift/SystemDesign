
public class LastSquare extends Square{
	LastSquare(int position) {
		super(position);
	}
	
	@Override
	public boolean isLast() {
		return true;
	}
}