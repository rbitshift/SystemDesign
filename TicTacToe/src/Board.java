
public class Board {
	private static int SIZE = 3;
	private Box[] grid;
	
	public Board() {
		grid = new Box[SIZE * SIZE];
		for(int i = 0; i < grid.length; i++) {
			grid[i] = new Box();
		}
	}
	
	public boolean isFull() {
		boolean result = true;
		for(int i = 0; i < grid.length; i++) {
			if(!grid[i].isOccupied()) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public boolean isOccupied(int position) {
		return grid[position].isOccupied();
	}

	public boolean isOccupied(int position, Symbol symbol) {
		return grid[position].getSymbol() == symbol;
	}
	
	public boolean isOccupied(Streak streak, Symbol symbol) {
		boolean result = false;
		if(streak.equals(Streak.FULL_ROW)) {
			for(int i = 0; i < SIZE; i++) {
				if(grid[(i)*SIZE].getSymbol() == symbol && grid[(i*SIZE)+1].getSymbol() == symbol && grid[(i*SIZE)+2].getSymbol() == symbol) {
					result = true;
					break;
				}
			}
		} else if(streak.equals(Streak.FULL_COLUMN)) {
			for(int i = 0; i < SIZE; i++) {
				if(grid[i].getSymbol() == symbol && grid[i+SIZE].getSymbol() == symbol && grid[i+(SIZE*2)].getSymbol() == symbol) {
					result = true;
					break;
				}
			}
		} else if(streak.equals(Streak.FULL_DIAGONAL)) {
			if((grid[0].getSymbol() == symbol && grid[4].getSymbol() == symbol && grid[8].getSymbol() == symbol) ||
					(grid[2].getSymbol() == symbol && grid[4].getSymbol() == symbol && grid[6].getSymbol() == symbol)) {
				result = true;
			}
		}
		return result;
	}
	
	public void setOccupied(int position, Symbol symbol) {
		grid[position].setOccupied(symbol);
	}

	public Box getBoxAt(int position) {
		return grid[position];
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				buffer.append(grid[i*SIZE+j].getSymbol() + " ");
			}
			buffer.append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
	}
}