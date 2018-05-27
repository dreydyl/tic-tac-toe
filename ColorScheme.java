import java.awt.Color;
import java.util.ArrayList;

public class ColorScheme extends ArrayList<Color> {

	private String name;
	private boolean defaultName;

	public ColorScheme(Color color) {
		this("CS",color);
		defaultName = true;
	}
	public ColorScheme(Color...colors) {
		this("CS",colors);
		defaultName = true;
	}
	public ColorScheme(String name,Color color) {
		add(color);
		this.name = name;
		defaultName = false;
	}
	public ColorScheme(String name,Color...colors) {
		if(colors.length == 0) add(Color.BLACK);
		for(Color c:colors) {
			add(c);
		}
		this.name = name;
		defaultName = false;
	}
	
	public ColorScheme split(int index) {
		ColorScheme baby = this;
		for(int i = 0;i < index;i++) {
			baby.remove(0);
		}
		return baby;
	}

	public boolean add(Color c) {
		super.add(c);
		if(size() < 3) {
			super.add(Color.WHITE);
			super.add(Color.YELLOW);
		}
		return true;
	}

	public String getDefaultName() {
		String name = "";
		int dominant = 0;
		for(int i = 0;i < getPrimaries().length;i++) {
			if(getPrimaries()[i] > dominant) {
				dominant = getPrimaries()[i];
			}
		}
		int recessive = dominant/2;
		
		int[] dominants = getPrimaries();
		int[] recessives = getPrimaries();
		for(int i = 0;i < getPrimaries().length;i++) {
			if(dominants[i]/this.size() < dominant/this.size()-20) {
				dominants[i] = 0;
			}
			if(recessives[i]/this.size() < recessive/this.size()-10
					&& recessives[i]/this.size() > recessive/this.size()+10) {
				recessives[i] = 0;
			}
		}
		
		if(dominants[0] > 0) {
			name = "Red";
			if(dominants[1] > 0) {
				name = "Yellow";
				if(dominants[2] > 0) {
					name = "Balanced";
				}
			}
			else if(dominants[2] > 0) {
				name = "Purple";
			}
			else if(recessives[1] > 0) {
				name = "Orange";
			}
		}
		else if(dominants[1] > 0) {
			name = "Green";
			if(dominants[2] > 0) {
				name = "Cyan";
			}
		}
		else if(dominants[2] > 0) {
			name = "Blue";
		}
		
		return name+"-ish";
	}

	public int[] getPrimaries() {
		int[] primaries = { getRed(),getGreen(),getBlue() };
		return primaries;
	}
	public int getTotal() {
		return getRed()+getGreen()+getBlue();
	}
	public int getRed() {
		int red = 0;
		for(Color c:this) {
			red += c.getRed();
		}
		return red;
	}
	public int getGreen() {
		int green = 0;
		for(Color c:this) {
			green += c.getGreen();
		}
		return green;
	}
	public int getBlue() {
		int blue = 0;
		for(Color c:this) {
			blue += c.getBlue();
		}
		return blue;
	}
	public int getBrightness() {
		return getTotal()/this.size();
	}
	
	public String toString() {
		String name = this.name;
		if(defaultName) {
			name = getDefaultName();
		}
		return name+" : "+super.toString();
	}

}
