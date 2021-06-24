package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.Controller;
import models.Board;
import models.Point;
import models.HumanPlayer;

public class BoardView extends JPanel {
	public Board board;
	Controller controller;
	static Image x;
	static Image o;
	boolean isPlayWithComputer;
	boolean isStarted;


	public BoardView() {
		init();

		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (isStarted)
					oneTurn(arg0);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (isStarted) {
			for (int i = 0; i < board.getBoard().length; i++) {
				for (int j = 0; j < board.getBoard()[i].length; j++) {
					if (board.getBoard()[i][j] == controller.player1.getId())
						g.drawImage(o, i*24, j*24, null);
					else if (this.board.getBoard()[i][j] == controller.player2.getId())
						g.drawImage(x, i*24, j*24, null);
					else {
						g.setColor(Color.gray);
						g.drawRect(i * 24, j * 24, 24, 24);
					}
				}
			}
		} else {
			for (int i = 0; i < board.getBoard().length; i++) {
				for (int j = 0; j < board.getBoard()[i].length; j++) {
					g.setColor(Color.gray);
					g.drawRect(i * 24, j * 24, 24, 24);
				}
			}
		}
	}

	
	public void oneTurn(MouseEvent e) {
		Point point = getPoint(e);
		controller.turnHuman(point);
		if (isPlayWithComputer && controller.currentTurn != controller.player1)
			controller.turnComputer();
		
	}
	
	
	public Point getPoint(MouseEvent e) {
		return new Point(e.getX()/24, e.getY()/24, controller.currentTurn.getId());
	}
	
	public void playWithHuman() {
		controller.player2 = new HumanPlayer();
		controller.currentTurn = controller.player1;
		isPlayWithComputer = false;
		isStarted = true;
	}
	
	public void playWithComputer() {
		controller.playWithComputer();
		isPlayWithComputer = true;
		isStarted = true;
	}

	public void gameOver() {
		String msg;
		if (controller.winner == null)
			msg = "Draw!\nPlay again?";
		else
			msg = controller.winner.getClass().getSimpleName()+ controller.winner.getId() + " won!\nPlay again?";
		
		int intOption = JOptionPane.showConfirmDialog(null, msg, "Game over!", 1);
		if (intOption == JOptionPane.YES_OPTION) {
			resetGame();
		} else
			System.exit(0);
	}

	public void loadImage() {
		try {
			x = ImageIO.read(new File("cross.png"));
			o = ImageIO.read(new File("nought.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		board = new Board();
		controller = new Controller(this);
		isPlayWithComputer = false;
		isStarted = false;
		loadImage();
	}
	
	public void resetGame() {
		controller.reset();
	}
}
