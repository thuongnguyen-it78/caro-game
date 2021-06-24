package models;

public interface IPlayer {
	boolean move(Board board, Point chessman);
	int getId();
	Point getProfitableMove(Board board, int idCompetitor);
}
