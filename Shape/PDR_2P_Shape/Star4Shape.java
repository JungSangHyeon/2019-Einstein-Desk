package PDR_2P_Shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import zStuff_Shape.APDRShape;

public class Star4Shape extends APDRShape{
	private static final long serialVersionUID = 8804449637687739570L;

	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
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
