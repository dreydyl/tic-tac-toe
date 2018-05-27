import javax.swing.JPanel;

public abstract class Game extends JPanel {

	protected Player p1,p2,active;
	protected boolean inGame;
	
	public void nextTurn() {
		if(active == p1) { active = p2; }
		else active = p1;
	}

	protected abstract void checkBoard();
	protected void endGame(Player winner) {
		inGame = false;
		System.out.println("WINNER: "+winner.getPattern().name());
	}

}
