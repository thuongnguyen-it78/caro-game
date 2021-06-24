package controllers;

import models.Board;
import models.Point;
import models.ComputerPlayer;
import models.HumanPlayer;
import models.IPlayer;
import views.BoardView;

public class Controller {
	public BoardView view;
	public Board board;
	public IPlayer player1;
	public IPlayer player2;
	public IPlayer currentTurn;
	public IPlayer winner = null;
	public static boolean isEnd = false;

	public Controller(BoardView view) {
		this.view = view;
		this.board = view.board;
		this.player1 = new HumanPlayer();
		currentTurn = player1;
	}

	public void turnHuman(Point c) {
		if (move(c)) {
			if (checkEndForPlayer(currentTurn, c)) 
				winner = currentTurn;
			changeTurn();
			updateView();
			if (isOver()) 
				showWinner();
			
		}
	}

	public void turnComputer() {
		if (board.getCount() == 0) {
			player2.move(board, new Point(9, 9, player2.getId()));
			changeTurn();
			return;
		}
		Point point = player2.getProfitableMove(board, player1.getId());
		if(!player2.move(board, point)) turnComputer();
		if (checkEndForPlayer(currentTurn, point))
			winner = currentTurn;
		changeTurn();
		updateView();
		if (isOver()) 
			showWinner();
	}

	public boolean checkEndForPlayer(IPlayer player, Point chessman) {
		return board.checkEndForOnePlayer(player, chessman);
	}

	public void changeTurn() {
		if (currentTurn.getId() == player1.getId())
			currentTurn = player2;
		else
			currentTurn = player1;
	}

	public void updateView() {
		view.repaint();
	}

	public void showWinner() {
		view.gameOver();
	}

	public boolean move(Point point) {
		return currentTurn.move(board, point);
	}

	public boolean isOver() {
		return board.isOver() || winner != null;
	}
	
	public void reset() {
		board.resetBoard();
		if (winner instanceof ComputerPlayer)
			player2.move(board, new Point(9, 9, player2.getId()));
		winner = null;
		updateView();
	}
	
	public void playWithComputer() {
		player2 = new ComputerPlayer();
		player2.move(board, new Point(9, 9, player2.getId()));
		currentTurn = player1;
	}
	 


}
