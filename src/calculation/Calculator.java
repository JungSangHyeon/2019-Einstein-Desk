package calculation;

import java.awt.geom.Point2D;

public class Calculator {
	public static double distanceLineNPoint(Point2D sp, Point2D ep, Point2D p) {
		double xDelta = ep.getX() - sp.getX();
		double yDelta = ep.getY() - sp.getY();
		double u = ((p.getX() - sp.getX()) * xDelta + (p.getY() - sp.getY()) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
		Point2D.Double closestPoint;
		if (u < 0) {closestPoint = new Point2D.Double(sp.getX(), sp.getY());}
		else if (u > 1) {closestPoint = new Point2D.Double(ep.getX(), ep.getY());}
		else {closestPoint = new Point2D.Double(Math.round(sp.getX() + u * xDelta), Math.round(sp.getY() + u * yDelta));}
		return Point2D.distance(closestPoint.x, closestPoint.y, p.getX(), p.getY());
	}
}
