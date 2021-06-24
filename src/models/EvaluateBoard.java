package models;


import java.util.Random;

public class EvaluateBoard {

	public static Point evaluate(int[][] board) {
		Random random = new Random();
		int x = random.nextInt(24) + 0;
		int y = random.nextInt(24) + 0;
		Point point = new Point(x, y);
		return point;
	}


}
