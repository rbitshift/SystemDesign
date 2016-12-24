
public class Board {
	private static int size = 10;
	private static int[][] snakes = { {98, 28}, {95, 24}, {92, 51}, {83, 19}, {73, 2}, {64, 36}, {69, 33}, {59, 17}, {55, 7}, {52, 11}, {44, 22}, {46, 5}, {48, 9 } };;
	private static int[][] ladders = { {8, 26} , {21 ,82}, {43, 77}, {50, 91}, {54, 93}, {62, 96}, {66, 87}, {80, 99} };
	
	private Square[] grid;
	
	Board() {
		assert size > 1;
		
		grid = new Square[size * size];
		grid[0] = new FirstSquare(0);
		grid[grid.length-1] = new LastSquare(grid.length-1);
		
		for(int i = 0; i < grid.length-1; i++) {
			grid[i] = new NormalSquare(i);
		}
		
		addSnakes();
		addLadders();
	}
	
	private void addSnakes() {
		for(int i = 0; i < snakes.length; i++) {
			assert snakes[i].length == 2;
			
			int[] snake = snakes[i];
			int head = snake[0] - 1;
			int tail = snake[1] - 1;
			int length = head - tail;
			
			assert length > 0;
			assert getFirstPosition() < head && head < getLastPosition();
			assert getFirstPosition() <= tail && tail < getLastPosition(); 

			grid[head] = new SnakeSquare(head, length);
//			System.out.println("Snake: " + snake[0] + " to " + snake[1] + " length: " + length);
		}
	}
	
	private void addLadders() {
		for(int i = 0; i < ladders.length; i++) {
			assert ladders[i].length == 2;
			
			int[] ladder = ladders[i];
			int start = ladder[0] - 1;
			int end = ladder[1] - 1;
			int length = end - start;
			
			assert length > 0;
			assert getFirstPosition() < start && start < getLastPosition();
			assert getFirstPosition() < end && end < getLastPosition();
			
			grid[start] = new LadderSquare(start, length);
//			System.out.println("Ladder: " + ladder[0] + " to " + ladder[1] + " length: " + length);
		}
	}
	
	public int getFirstPosition() {
		return grid[0].getPosition();
	}
	
	public int getLastPosition() {
		return grid[grid.length-1].getPosition();
	}
	
	public Square getSquareAt(int pos) {
		return grid[pos];
	}
	
	public int getConnectedPositionAt(int pos) {
		return getSquareAt(pos).connectsTo();
	}
	
	public boolean isPositionOccupied(int pos) {
		return getSquareAt(pos).isOccupied();
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		boolean direction = false;
		for(int i = size-1; i >= 0; i--) {
			direction = !direction;
			if(direction) {
				for(int j = size-1; j >= 0; j-- ) {
					append(buffer, i, j);
				}
			} else {
				for(int j = 0; j < size; j++ ) {
					append(buffer, i, j);
				}
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	private void append(StringBuffer buffer, int i, int j) {
		int position = i * size + j;
		if(i == 0 && j == 0) {
			buffer.append(" SS " );
		} else if( i == size-1 && j == size - 1) {
			buffer.append( " EE " );
		} else{
			Square square = getSquareAt(position);
			int connectedPos = square.connectsTo();
			if(square.isOccupied()) {
				buffer.append( " " + square.getOccupant().getName() + " " );
			} else {
				if(connectedPos < square.getPosition()) {
					buffer.append(" !! ");
				} else if (connectedPos > square.getPosition()) {
					buffer.append(" ^^ ");
				} else {
					buffer.append(" " + String.format("%2d", position+1) + " ");
				}
			}
		}
	}
}