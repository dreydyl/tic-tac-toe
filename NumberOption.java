import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NumberOption extends Button {

	private double number;
	
	public NumberOption(JFrame parent,String hint) {
		frame = parent;
		name = hint;
	}

	public double getNumber() { return number; }

	public void click() {
		
	}
	
	public void draw(Graphics g) {
		
	}

}
