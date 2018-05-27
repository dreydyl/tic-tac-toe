
public class Location {

	private double x,y;
	
	public Location(double x,double y) {
		this.x = x;
		this.y = y;
	}
	public Location(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }

	public int getDrawX() { return (int) x; }
	public int getDrawY() { return (int) y; }
	
	public String toString() {
		return "("+x+", "+y+")";
	}

}
