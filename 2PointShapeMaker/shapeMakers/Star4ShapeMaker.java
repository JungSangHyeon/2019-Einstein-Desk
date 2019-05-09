package shapeMakers;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import twoPointShapeStuff.A2PShapeMaker;

public class Star4ShapeMaker extends A2PShapeMaker{
	public Shape newShape(Point2D.Float p1, Point2D.Float p2) {
		return makePath(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	private static GeneralPath makePath(float x, float y, float w, float h) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(x + w / 2, y);
		path.lineTo(x + w * 3 / 8, y + h * 3 / 8);
		path.lineTo(x, y + h / 2);
		path.lineTo(x + w * 3 / 8, y + h * 5 / 8);
		path.lineTo(x + w / 2, y + h);
		path.lineTo(x + w * 5 / 8, y + h * 5 / 8);
		path.lineTo(x + w, y + h / 2);
		path.lineTo(x + w * 5 / 8, y + h * 3 / 8);
		path.closePath();
		return path;
	}
}
