package shape;


import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

import shape_Stuff.ACMCShape;

public class FreeLineShape extends ACMCShape{
	public Shape newShape(Vector<Float> points) {
		return makeLine(points);
	}
	
	private static GeneralPath makeLine(Vector<Float> points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points.get(0).x, points.get(0).y);
		for(Point2D.Float p : points){path.lineTo(p.x, p.y);}
		return path;
	}
}
