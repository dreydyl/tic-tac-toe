import java.awt.Color;
import java.awt.Graphics;

public class SuperGraphics {

	Graphics sg;
	public enum Pattern {
		X,O,S,Plus
	}

	public static void main(String...args) {
		Color og = new Color(255,255,255);
		System.out.println(og+", "+og.brighter()+", "+og.darker());
	}

	public SuperGraphics(Graphics g) {
		sg = g;
	}
	public SuperGraphics() { }

	public void drawForm1(Graphics g,Shape shape) {
		sg = g;
		for(Line s:shape.getSides()) {
			
			Location mid = s.getMidpoint();
			double midX = mid.getX();
			double midY = mid.getY();
			boolean U = shape.contains(midX,midY-.01);
			boolean D = shape.contains(midX,midY+.01);
			boolean L = shape.contains(midX-.01,midY);
			boolean R = shape.contains(midX+.01,midY);
			
			if(s.slope() >= 0.9 && s.slope() <= Math.pow(.9,-1)) {
				sg.setColor(slightlyBrighter());
			} else if(D && L && !(U && R)) {
				sg.setColor(sg.getColor().brighter());
			} else if(s.slope() >= 0) {
				if((s.slope() < 1 && D&&R)
						|| (s.slope() > 1 && U&&L)) {
					sg.setColor(sg.getColor().brighter());
				} else {
					sg.setColor(sg.getColor().darker());
				}
			} else {
				sg.setColor(sg.getColor().darker());
			}
			sg.drawLine(s.getA().getDrawX(),
					s.getA().getDrawY(),
					s.getB().getDrawX(),
					s.getB().getDrawY());
		}
	}
	
	public void drawPattern(Graphics g,int x,int y,int w,int h,Pattern pattern) {
		sg = g;
		if(pattern.equals(Pattern.X)) {
			sg.drawLine(x,y,x+w,y+h);
			sg.drawLine(x,y+h,x+w,y);
		}
		if(pattern.equals(Pattern.O)) {
			sg.drawOval(x,y,w,h);
		}
	}
	public void drawPattern(Graphics g,Space space) {
		int patternWidth = space.getDrawWidth()*8/10;
		int patternHeight = space.getDrawHeight()*8/10;
		drawPattern(g,space.getDrawX()+space.getDrawWidth()/10,
				space.getDrawY()+space.getDrawHeight()/10,
				patternWidth,
				patternHeight,
				space.getPattern());
	}
	
	public Color slightlyBrighter() {
		Color og = sg.getColor();
		if(og.equals(Color.BLACK)) {
			return new Color(3,3,3);
		}
		return new Color(
				(int) (og.getRed() > 204 ? 255 : og.getRed()/.8),
				(int) (og.getGreen() > 204 ? 255 : og.getGreen()/.8),
				(int) (og.getBlue() > 204 ? 255 : og.getBlue()/.8));
	}
	public Color slightlyDarker() {
		Color og = sg.getColor();
		return new Color(
				(int) (og.getRed() > 204 ? 255 : og.getRed()*.8),
				(int) (og.getGreen() > 204 ? 255 : og.getGreen()*.8),
				(int) (og.getBlue() > 204 ? 255 : og.getBlue()*.8));
	}

}
