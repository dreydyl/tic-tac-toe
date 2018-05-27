import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public abstract class Button extends Shape implements GameObject {

	protected JFrame frame;

	protected ColorScheme scheme;
	protected String name;
	
	protected boolean highlighted;
	protected boolean pressed;

	public enum Style { Flat, Key, Outline };

	public Button(String name,ColorScheme cs,Location...points) {
		super(points);
		this.name = name;
		scheme = cs;
		
		highlighted = false;
		pressed = false;
	}
	public Button(String name,Location...points) {
		this(name,new ColorScheme(),points);
	}
	public Button(ColorScheme cs,Location...points) {
		this("",cs,points);
	}
	public Button(Location...points) {
		this("",new ColorScheme(),points);
	}
	
	public abstract void click();
	
	public void highlight() { highlighted = true; }
	public void unhighlight() { highlighted = false; }
	
	public void press() { pressed = true; }
	public void release() { pressed = false; }

	public String getName() { return name; }
	public ColorScheme getColors() {
		return scheme;
	}
	
	public void setFrame(JFrame parent) { frame = parent; }
	public JFrame getFrame() { return frame; }

	public String toString() {
		return scheme.toString()+points;
	}

	public void draw(Graphics g) {
		g.setColor(scheme.get(0));
		g.fillPolygon(this);
	}

}
