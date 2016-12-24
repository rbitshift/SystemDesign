import java.util.Random;


public class Dice {
	private static int FACES = 6;
	private Random diceObject = new Random();
	
	public int roll() {
		return diceObject.nextInt(FACES) + 1;
	}
}