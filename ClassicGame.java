import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClassicGame extends Game {

	private final int W = 450;
	private final int H = 450;
	private Space[][] board;
	private Space[] numberedBoard;
	private int[][] winningArrangements;

	private Timer t;
	private ArrayList<ActionListener> actions = new ArrayList<ActionListener>();
	private MouseListener mouse;
	private MouseMotionListener mouseMotion;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ClassicGame());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public ClassicGame() {
		this.setPreferredSize(new Dimension(W,H));
		createPlayers();
		createBoard();
		setWinningArrangements();
		createInteractions();
		inGame = true;
	}
	private void createPlayers() {
		active = p1 = new Player(SuperGraphics.Pattern.X);
		p2 = new Player(SuperGraphics.Pattern.O);
	}
	private void setWinningArrangements() {
		winningArrangements = new int[board.length+board[0].length+2][3];
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[i].length;j++) {
				winningArrangements[i][j] = 3*i+j;
				winningArrangements[j+board.length][i] = 3*i+j;
			}
		}
		for(int i = 0;i < (board.length < board[0].length ? board.length : board[0].length);i++) {
			winningArrangements[6][i] = 3*i+i;
			winningArrangements[7][i] = 3*(2-i)+i;
		}
		
		for(int i = 0;i < winningArrangements.length;i++) {
			System.out.print("{ ");
			for(int j = 0;j < winningArrangements[i].length;j++) {
				System.out.print(winningArrangements[i][j]+", ");
			}
			System.out.println("}");
		}
	}
	private void createBoard() {
		board = new Space[3][3];
		for(int r = 0;r < 3;r++) {
			for(int c = 0;c < 3;c++) {
				int w = W/3, h = H/3;
				board[r][c] = new Space(this,3*r+c,
						new Location(w*c,h*r),
						new Location(w*c,h*(r+1)),
						new Location(w*(c+1),h*(r+1)),
						new Location(w*(c+1),h*r));
			}
		}
	}
	private void createInteractions() {
		t = new Timer(10,null);
		ActionListener animator = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		};
		actions.add(animator);
		t.addActionListener(animator);
		t.start();
	
		mouse = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked "+e);
				activatedSpace(e.getX(),e.getY()).setPattern(active.getPattern());
				active.add(activatedSpace(e.getX(),e.getY()));
				activatedSpace(e.getX(),e.getY()).click();
				checkBoard();
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }

		};
		this.addMouseListener(mouse);

		mouseMotion = new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) { }
			public void mouseMoved(MouseEvent e) {
				//System.out.println("Moved "+e);
				activatedSpace(e.getX(),e.getY()).highlight();
				for(Space sp:inactiveSpaces(e.getX(),e.getY())) {
					sp.unhighlight();
				}
			}

		};
		this.addMouseMotionListener(mouseMotion);
	}

	protected void checkBoard() {
		setNumberedBoard();
		if(p1.check(winningArrangements)) endGame(p1);
		else if(p2.check(winningArrangements)) endGame(p2);
	}
	private void setNumberedBoard() {
		numberedBoard = new Space[board.length*board[0].length];
		int i = 0;
		for(Space[] row:board) {
			for(Space space:row) {
				numberedBoard[i] = space;
			}
		}
	}

	private Space activatedSpace(int x,int y) {
		for(Space[] row:board) {
			for(Space sp:row) {
				if(sp.contains(x,y)) {
					return sp;
				}
			}
		}
		return null;
	}
	private ArrayList<Space> inactiveSpaces(int x,int y) {
		ArrayList<Space> spaces = new ArrayList<Space>();
		for(Space[] row:board) {
			for(Space sp:row) {
				if(!sp.contains(x,y)) {
					spaces.add(sp);
				}
			}
		}
		return spaces;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		int w = W/3;
		int h = H/3;
		for(int n = 0;n < 2;n++) {
			g.fillRect(0,h*(n+1)-1,W,3);
			g.fillRect(w*(n+1),0,3,H);
		}
		for(Space[] row:board) {
			for(Space sp:row) {
				sp.draw(g);
			}
		}
	}

}
