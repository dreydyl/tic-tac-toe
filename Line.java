
public class Line {

	Location[] points;
	
	public Line(Location point1,Location point2) {
		points = new Location[2];
		points[0] = point1;
		points[1] = point2;
	}
	
	public Location getA() {
		return points[0];
	}
	public Location getB() {
		return points[1];
	}
	public double slope() {
		if(points[1].getX()-points[0].getX() == 0) {
			return 0;
		}
		return (points[1].getY()-points[0].getY())
				/ (points[1].getX()-points[0].getX());
	}
	public double length() {
		return Math.sqrt(
				Math.pow((points[1].getY()-points[0].getY()),2)
				+ Math.pow((points[1].getX()-points[0].getX()),2));
	}
	public Location getMidpoint() {
		return new Location(
				(points[0].getX()+points[1].getX())/2,
				(points[0].getY()+points[1].getY())/2);
	}

}
