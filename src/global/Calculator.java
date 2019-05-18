package global;

import java.awt.geom.Point2D;

public class Calculator {

	public static double distanceLineNPoint(Point2D.Float sp, Point2D.Float ep, Point2D.Float p) {
		double xDelta = ep.x - sp.x;
		double yDelta = ep.y - sp.y;
		double u = ((p.x - sp.x) * xDelta + (p.y - sp.y) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
		Point2D.Float closestPoint;
		if (u < 0) {closestPoint = new Point2D.Float(sp.x, sp.y);}
		else if (u > 1) {closestPoint = new Point2D.Float(ep.x, ep.y);}
		else {closestPoint = new Point2D.Float(Math.round(sp.x + u * xDelta), Math.round(sp.y + u * yDelta));}
		return Point2D.distance(closestPoint.x, closestPoint.y, p.x, p.y);
	}


	
}
