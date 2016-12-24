
public abstract class Square {
	private int position;
	private Player player;
	
	public Square(int position) {
		this.position = position;
		this.player = null;
	}
	
	public void enter(Player player) {
		this.player = player;
	}
	
	public void leave() {
		this.player = null;
	}
	
	public boolean isFirst() {
		return false;
	}
	
	public boolean isLast() {
		return false;
	}
	
	public boolean isOccupied() {
		return player != null;
	}
	
	public int connectsTo() {
		return position;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public Player getOccupant() {
		return this.player;
	}
}