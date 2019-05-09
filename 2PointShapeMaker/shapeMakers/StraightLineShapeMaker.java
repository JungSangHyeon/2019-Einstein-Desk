package shapeMakers;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import twoPointShapeStuff.A2PShapeMaker;

public class StraightLineShapeMaker extends A2PShapeMaker{
	public Shape newShape(Point2D.Float p1, Point2D.Float p2) {
		return new Line2D.Float(p1.x,p1.y,p2.x,p2.y);
	}
}
