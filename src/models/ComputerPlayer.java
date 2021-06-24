package models;

public class ComputerPlayer implements IPlayer {
	private int id = 2;

	public ComputerPlayer() {
		super();
	}
	
	@Override
	public boolean move(Board board, Point chessman) {
		board.setPosition(chessman);
		board.setCount(board.getCount() + 1);
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	
	public Point getProfitableMove(Board board, int idHumanPlayer) {

		Point point = EvaluateBoard.evaluate(board.getBoard());
		point.setPlayerId(id);
		return point;
	}

}
