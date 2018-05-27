import java.awt.Polygon;
import java.util.*;

public class Shape extends Polygon {

	protected ArrayList<Location> points = new ArrayList<Location>();
	protected ArrayList<Line> sides = new ArrayList<Line>();

	public Shape(Location...points) {
		for(Location p:points) {
			addPoint(p.getDrawX(),p.getDrawY());
		}
	}

	public ArrayList<Location> getPoints() {
		return points;
	}
	public ArrayList<Line> getSides(){
		return sides;
	}

	//Dimension getters
	public Location getLocation() { return new Location(getX(),getY()); }
	public double getX() { return getBounds().getX(); }
	public double getY() { return getBounds().getY(); }
	public double getCenterX() { return getBounds().getCenterX(); }
	public double getCenterY() { return getBounds().getCenterY(); }
	public double getWidth() { return getBounds().getWidth(); }
	public double getHeight() { return getBounds().getHeight(); }

	public int getDrawX() { return (int) getBounds().getX(); }
	public int getDrawY() { return (int) getBounds().getY(); }
	public int getDrawWidth() { return (int) getBounds().getWidth(); }
	public int getDrawHeight() { return (int) getBounds().getHeight(); }

	public void addPoint(int x,int y) {
		points.add(new Location(x,y));
		if(points.size() > 1) {
			sides.add(new Line(points.get(points.size()-2),points.get(points.size()-1)));
		}
		super.addPoint(x,y);
	}

}
