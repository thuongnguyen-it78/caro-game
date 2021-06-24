package models;

public class Point {
	private int x, y, playerId;

	public Point() {
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, int playerId) {
		super();
		this.x = x;
		this.y = y;
		this.playerId = playerId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String toString() {
		String msg = String.format("X: %d\n Y: %d\n PlayerId: %d\n", this.x, this.y, this.playerId);
		return msg;
	}
}
