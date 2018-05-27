import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Selector extends ArrayList<Option> implements GameObject {

	private JFrame frame;

	public Selector(Option...opts) {
		for(Option opt:opts) {
			add(opt);
		}
	}

	public void select(Option option) {
		option.select();
		for(Option opt:this) {
			opt.deselect();
		}
	}
	public Option getSelected() {
		for(Option opt:this) {
			if(opt.isSelected()) return opt;
		}
		return null;
	}

	public void draw(Graphics g) {
		
	}

	public void setFrame(JFrame parent) {
		frame = parent;
	}

}
