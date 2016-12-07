
public class Game {
	private Board board;
	private Player[] players;
	private Streak[] winningStreaks;
	private int currentPlayerIndex;
	
	public Game() {
		this.board = new Board();
		this.players = new Player[]{new Player("A", Symbol.CROSS), new Player("B", Symbol.CIRCLE)};
		this.winningStreaks = new Streak[]{Streak.FULL_ROW, Streak.FULL_COLUMN, Streak.FULL_DIAGONAL};
		this.currentPlayerIndex = -1;
	}
	
	public void run() {
		while(!isOver()) {
			Player currentPlayer = getNextPlayer();
			int nextPosition = currentPlayer.doMove(board);
			board.setOccupied(nextPosition, currentPlayer.getSymbol());
			System.out.print(board);
		}
		
		Player winner = getWinner();
		if(winner == null) {
			System.out.println("Game Draw");
		} else {
			System.out.println("Winner is Player :" + winner.getName());
		}
	}
	
	public boolean isOver() {
		boolean result = false;
		
		
		if(board.isFull()) {
			result = true;
		} else {
			if(getCurrentPlayer() != null) {
				for(int i = 0; i < winningStreaks.length; i++) {
					if (board.isOccupied(winningStreaks[i], getCurrentPlayer().getSymbol())) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
	
	public Player getWinner() {
		Player winner = null;
		for(int i = 0; i < players.length; i++) {
			for(int j = 0; j < winningStreaks.length; j++) {
				if(board.isOccupied(winningStreaks[j], players[i].getSymbol())) {
					winner = players[i];
					break;
				}
			}
		}
		return winner;
	}
	
	public Player getCurrentPlayer() {
		Player currentPlayer = null;
		if( -1 < currentPlayerIndex && currentPlayerIndex < players.length) {
			currentPlayer = players[currentPlayerIndex];
		}
		return currentPlayer;
	}
	
	public Player getNextPlayer() {
		currentPlayerIndex = ((currentPlayerIndex + 1) % players.length);
		return getCurrentPlayer();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}