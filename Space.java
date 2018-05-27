import java.awt.Color;
import java.awt.Graphics;

public class Space extends Button {

	private Game game;
	private int location;
	private SuperGraphics.Pattern pattern;

	public Space(Game g,int loc,Location...points) {
		super(points);
		game = g;
		location = loc;
	}

	public void click() {
		game.nextTurn();
	}
	
	public int getIndex() { return location; }

	public void setPattern(SuperGraphics.Pattern p) {
		pattern = p;
	}
	public SuperGraphics.Pattern getPattern() {
		return pattern;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		if(pattern != null) new SuperGraphics(g).drawPattern(g,this);
	}

}
