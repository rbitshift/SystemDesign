
public class Game {
	private Dice dice;
	private Board board;
	private Player[] players;
	private int currentPlayerIndex;
	
	Game() {
		this.dice = new Dice();
		this.board = new Board();
		this.players = new Player[]{new Player("A"), new Player("B")};
		this.currentPlayerIndex = -1;
	}
	
	public void play() {
		while(!isOver()) {
			Player currentPlayer = getNextPlayer();
			int lastPos = currentPlayer.getPosition();
			int num = currentPlayer.move(dice);
			
			//REBOUND_RULE
			int reboundPos = getReboundedPosition(currentPlayer.getPosition(), num, board.getLastPosition());
			
			//SNAKE_N_LADDER_RULE
			int jumpPos = getConnectedPosition(reboundPos);
			
			//STRIKE_OPPONENT_RULE
			Square currentSquare = board.getSquareAt(jumpPos);
			if(currentSquare.isOccupied()) {
				strikeOpponent(currentSquare.getOccupant(), currentPlayer, jumpPos);
			}
			
			board.getSquareAt(currentPlayer.getPosition()).leave();
			currentSquare.enter(currentPlayer);
			currentPlayer.setPosition(jumpPos);
			
			System.out.println(String.format("Player %s rolls %d and moves from %d to %d position \n%s", 
					currentPlayer.getName(), num, lastPos+1, currentPlayer.getPosition()+1, board));
		}
		
		Player winner = getWinnerPlayer();
		System.out.println(String.format("!!! Player %s wins !!!", winner.getName()));
	}
	
	private int getReboundedPosition(int currentPos, int distance, int lastPos) {
		int reboundPos = currentPos + distance;
		if(reboundPos > lastPos) {
			reboundPos = currentPos - (reboundPos - lastPos);
		}
		return reboundPos;
	}
	
	private int getConnectedPosition(int position) {
		int connection = board.getSquareAt(position).connectsTo();
		return connection;
	}
	
	private void strikeOpponent(Player opponent, Player self, int position) {
		Square currentSquare = board.getSquareAt(position);
		currentSquare.leave();
		
		board.getSquareAt(board.getFirstPosition()).enter(opponent);
		opponent.setPosition(board.getFirstPosition());
		System.out.println(String.format("Player %s kicked %s at position %d", self.getName(), opponent.getName(), position));
	}
	
	public boolean isOver() {
		return board.isPositionOccupied(board.getLastPosition());
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
	
	public Player getWinnerPlayer() {
		Player winner = null;
		for(int i = 0; i < players.length; i++) {
			if(players[i].getPosition() == board.getLastPosition()) {
				winner = players[i];
				break;
			}
		}
		
		return winner;
	}
}