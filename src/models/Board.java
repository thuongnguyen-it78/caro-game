/**
 *  Lop luu lai trang thai cua ban co
 */
package models;


import java.util.Arrays;

public class Board {
	private int[][] board;
	final int WIDTH = 24;
	final int HEIGHT = 24;
	private int count;
//	private Point lastMove = null;

	public Board() {
		this.board = new int[WIDTH][HEIGHT];
		this.count = 0;

	}

	public void resetBoard() {
		count = 0;
		board = new int[WIDTH][HEIGHT];

	} 
	
	// kiem tra thang cua mot player
	public boolean checkEndForOnePlayer(IPlayer player, Point point) {
//		for(int i = 0; i < board.length; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
//
//		int x = point.getX();
//		int y = point.getY();
//		int count = 0;
//		System.out.println(String.format("%d\t%d\t%d", x, y, player.getId()));
//
//		// ngang
//		for(int col = 0; col < HEIGHT; col++){
//			if (board[y][col] == player.getId()) {
//				count++;
//				if(count == 5){
//					System.out.println("Ngang");
//					return true;
//				}
//			}else {
//				count = 0;
//			}
//		}
//
//		// doc
//		count = 0;
//		for(int row = 0; row < WIDTH; row++){
//			if (board[row][x] == player.getId()) {
//				count++;
//				if(count == 5){
//					System.out.println("Dọc");
//					return true;
//				}
//			}else {
//				count = 0;
//			}
//		}
//
//		// cheo trai
//		int min = Math.min(x, y);
//		int topI = y - min;
//		int topJ = x - min;
//		count = 0;
//
//		for(;topI < WIDTH && topJ < HEIGHT; topI++, topJ++){
//			if (board[topI][topJ] == player.getId()) {
//				count++;
//				if(count == 5){
//					System.out.println("Chéo trái");
//					return true;
//				}
//			}else {
//				count = 0;
//			}
//		}
//
//		//Chéo phải
//		min = Math.min(x, y);
//		topI = y - min;
//		topJ = x + min;
//		count = 0;
//
//		if(topJ >= WIDTH){
//			int du = topJ - (WIDTH - 1);
//			topI = topI + du;
//			topJ = WIDTH - 1;
//		}
//
//		for(;topI < WIDTH && topJ >= 0; topI++, topJ--){
//			if (board[topI][topJ] == player.getId()) {
//				count++;
//				if(count == 5){
//					System.out.println("Chéo phải");
//					return true;
//				}
//			}else {
//				count = 0;
//			}
//		}
//
//		return false;
		boolean isWin;
		int row = point.getX();
		int col = point.getY();

		int rowStart = (row - 5) >= 0 ? row - 5 : 0;
		int rowEnd = (row + 5) < HEIGHT ? row + 5 : HEIGHT;
		int colStart = (col - 5) >= 0 ? col - 5 : 0;
		int colEnd = (col + 5) < WIDTH ? col + 5 : WIDTH;

		// kiem tra hang doc
		for (int r = rowStart; r < rowEnd - 4; r++) {
			for (int c = colStart; c < colEnd; c++) {
				if (board[r][c] == 0) {
					continue;
				}
				isWin = true;
				for (int i = 0; i < 5; i++) {
					if (board[r + i][c] != point.getPlayerId()) {
						isWin = false;
						break;
					}
				}
				if (isWin)
					return true;
			}
		}

		// kiem tra hang ngang
		for (int r = rowStart; r < rowEnd; r++) {
			for (int c = colStart; c < colEnd - 4; c++) {
				if (board[r][c] == 0) {
					continue;
				}
				isWin = true;
				for (int i = 0; i < 5; i++) {
					if (board[r][c + i] != point.getPlayerId()) {
						isWin = false;
						break;
					}
				}
				if (isWin)
					return true;
			}
		}

		// kiem tra hang cheo xuong
		for (int r = rowStart; r < rowEnd - 4; r++) {
			for (int c = colStart; c < colEnd - 4; c++) {
				if (board[r][c] == 0) {
					continue;
				}
				isWin = true;
				for (int i = 0; i < 5; i++) {
					if (board[r + i][c + i] != point.getPlayerId()) {
						isWin = false;
						break;
					}
				}
				if (isWin)
					return true;
			}
		}
		// kiem tra hang cheo len
		for (int r = rowEnd-1; r > rowStart + 3; r--) {
			for (int c = colStart; c < colEnd - 4; c++) {
				if (board[r][c] == 0) {
					continue;
				}
				isWin = true;
				for (int i = 0; i < 5; i++) {
					if (board[r - i][c + i] != point.getPlayerId()) {
						isWin = false;
						break;
					}
				}
				if (isWin)
					return true;
			}
		}
		return false;
	}

	// kiem tra het ban co
	public boolean isOver() {
		return count == WIDTH * HEIGHT;
	}

	// set trang thai cho 1 quan co xac dinh
	public void setPosition(Point point) {
		System.out.println(point);
		board[point.getX()][point.getY()] = point.getPlayerId();
	}

	// Nuoc co hop le?
	public boolean isValid(Point point) {
		if (point.getY() >= WIDTH || point.getX() >= HEIGHT) {
			return false;
		}
		return board[point.getX()][point.getY()] == 0;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[][] getBoard() {
		return board;
	}

}
