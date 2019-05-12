package shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import shape_Stuff.APDRShape;

public class SpeechShape extends APDRShape{
	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return makePath(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	private static Shape makePath(float x, float y, float w, float h) {
		GeneralPath  path = new GeneralPath();
		path.moveTo(x, y);
		path.lineTo(x, y + h * 3 / 4);
		path.lineTo(x + w * 3 / 8, y + h * 3 / 4);
		path.lineTo(x + w * 3 / 8, y + h);
		path.lineTo(x + w * 5 / 8, y + h * 3 / 4);
		path.lineTo(x + w, y + h * 3 / 4);
		path.lineTo(x + w, y);
		path.closePath();
		return path;
	}
}
