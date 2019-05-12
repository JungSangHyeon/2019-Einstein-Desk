package shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import shape_Stuff.APDRShape;

public class TriangleShape extends APDRShape{
	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return makePath(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	public static GeneralPath makePath(float x, float y, float w, float h) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(x + w / 2, y);
		path.lineTo(x, y + h);
		path.lineTo(x + w, y + h);
		path.closePath();
		return path;
	}
}
