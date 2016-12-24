
public class Player {
	private String name;
	private int position;
	
	Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int move(Dice dice) {
		return dice.roll();
	}
}