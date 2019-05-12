package shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import shape_Stuff.APDRShape;

public class EllipseShape extends APDRShape{
	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return new Ellipse2D.Float(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
}
