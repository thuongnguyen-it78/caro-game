package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Viewer extends JFrame{

	private static final long serialVersionUID = 1L;
	private BoardView boardView;
	
	public Viewer() {
		super("AI - caro");
		boardView = new BoardView();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menu = new JMenuBar();
		JMenu mode = new JMenu("Mode");
		mode.setMnemonic(KeyEvent.VK_M);
		
		JMenuItem onePlayer = new JMenuItem("1 player");
		onePlayer.setMnemonic(KeyEvent.VK_1);
		JMenuItem twoPlayer = new JMenuItem("2 player");
		twoPlayer.setMnemonic(KeyEvent.VK_2);
		
		onePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardView.resetGame();
				boardView.playWithComputer();
			}
		});
		
		twoPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardView.playWithHuman();
				boardView.resetGame();
			}
		});
		
		mode.add(onePlayer);
		mode.add(twoPlayer);
		menu.add(mode);
		add(menu, BorderLayout.NORTH);
		add(boardView);
		pack();
		setSize(594, 640);
		setLocationRelativeTo(null);
//		setResizable(false);
		setVisible(true);
	}
}
