package shape;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import shape_Stuff.APDRShape;

public class StraightLineShape extends APDRShape{
	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return new Line2D.Float(p1.x,p1.y,p2.x,p2.y);
	}
}
