
package models;

public class HumanPlayer implements IPlayer {
	int id;
	public static int countId = 0;

	public HumanPlayer() {
		super();
		this.id = ++countId;
	}

	@Override
	public boolean move(Board board, Point chessman) {
		if (!board.isValid(chessman)) {
			return false;
		}
		board.setPosition(chessman);
		board.setCount(board.getCount() + 1);
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Point getProfitableMove(Board board, int idCompetitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
