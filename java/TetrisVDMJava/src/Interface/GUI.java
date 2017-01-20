package Interface;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TetrisVDM.Piece.Coordinate;
import TetrisVDM.Tetris;

public class GUI extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	private Tetris game;
	private int columns, rows;
	private JPanel mainPanel, gamePanel, nextPanel;
	private JLabel[][] board, nextBoard;
	private JLabel linesLabel;
	private Timer gameTimer;
	private ImageIcon IBlockIcon = new ImageIcon("images/IBlock.png");
	private ImageIcon LBlockIcon = new ImageIcon("images/LBlock.png");
	private ImageIcon JBlockIcon = new ImageIcon("images/JBlock.png");
	private ImageIcon SBlockIcon = new ImageIcon("images/SBlock.png");
	private ImageIcon ZBlockIcon = new ImageIcon("images/ZBlock.png");
	private ImageIcon TBlockIcon = new ImageIcon("images/TBlock.png");
	private ImageIcon OBlockIcon = new ImageIcon("images/OBlock.png");
	private ImageIcon EmptyBlockIcon = new ImageIcon("images/EmptyBlock.png");

	public static void main(String[] args) {
		GUI gui = new GUI(22,10);
	}
	
	public GUI(int r, int c) {
		super("Tetris");
		
		columns = c;
		rows = r;
		game = new Tetris(r, c);
		game.init();
		gameTimer = new Timer();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		gamePanel = new JPanel();
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gamePanel.setLayout(new GridLayout(rows, columns));
		board = new JLabel[rows][columns];
		for (int y = 1; y <= rows; y++) {
			for (int x = 1; x <= columns; x++) {
				JLabel tile = new JLabel();
				tile.setIcon(getIcon(game.getTile(x, y)));
				gamePanel.add(tile);
				board[y-1][x-1] = tile;
			}
		}
		gamePanel.setSize(25*columns, 25*rows);
		
		JPanel containNextPanel = new JPanel();
		nextPanel = new JPanel();
		nextPanel.setLayout(new GridLayout(5, 5));
		nextBoard = new JLabel[5][5];
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				JLabel tile = new JLabel();
				nextPanel.add(tile);
				nextBoard[y][x] = tile;
			}
		}
		for (Object tile: game.getNextPiece().getOriginalSprite()) {
			Coordinate coord = (Coordinate) tile;
			nextBoard[coord.y.intValue() + 2][coord.x.intValue() + 2].setIcon(getIcon(game.getNextPiece().getSymbol()));
		}
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		linesLabel = new JLabel("Lines cleared: "+ game.getLinesCleared());
		linesLabel.setHorizontalAlignment(JLabel.LEFT);
		linesLabel.setBackground(Color.BLACK);
		
		sidePanel.add(new JLabel("Next Piece"));
		containNextPanel.add(nextPanel);
		sidePanel.add(containNextPanel);
		sidePanel.add(linesLabel);
		
		mainPanel.add(gamePanel);
		mainPanel.add(sidePanel);
		add(mainPanel);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable(false);
		pack();
		addKeyListener(this);
		setVisible(true);
		setLocationRelativeTo(null);
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(!game.isGameFinished()) {
					game.moveCurrentPieceDown();
					paintBoard();
				}
			}
		},500,500);
	}
	
	private ImageIcon getIcon(char symbol) {
		switch (symbol) {
		case 'I':
			return IBlockIcon;
		case 'L':
			return LBlockIcon;
		case 'J':
			return JBlockIcon;
		case 'S':
			return SBlockIcon;
		case 'Z':
			return ZBlockIcon;
		case 'T':
			return TBlockIcon;
		case 'O':
			return OBlockIcon;
		default:
			return EmptyBlockIcon;
		}
	}
	
	private void paintBoard() {
		for (int y = 1; y <= rows; y++) {
			for (int x = 1; x <= columns; x++) {
				board[y-1][x-1].setIcon(getIcon(game.getTile(x, y)));
			}
		}
		for (int y = 1; y <= 5; y++) {
			for (int x = 1; x <= 5; x++) {
				nextBoard[y - 1][x - 1].setIcon(null);
			}
		}
		for (Object tile: game.getNextPiece().getOriginalSprite()) {
			Coordinate coord = (Coordinate) tile;
			nextBoard[coord.y.intValue() + 2][coord.x.intValue() + 2].setIcon(getIcon(game.getNextPiece().getSymbol()));
		}
		linesLabel.setText("Lines cleared: " + game.getLinesCleared());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!game.isGameFinished()) {
			if (key == KeyEvent.VK_LEFT) {
		    	game.moveCurrentPieceLeft();
		        paintBoard();
		    }

		    if (key == KeyEvent.VK_RIGHT) {
		    	game.moveCurrentPieceRight();
		        paintBoard();
		    }

		    if (key == KeyEvent.VK_UP) {
		    	game.rotate();
		        paintBoard();
		    }

		    if (key == KeyEvent.VK_DOWN) {
		        game.moveCurrentPieceDown();
		        paintBoard();
		    }
		    
		    if (key == KeyEvent.VK_ENTER) {
		        game.drop();
		        game.next();
		        paintBoard();
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
