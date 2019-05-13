package shape;


import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

import shape_Stuff.APDRShape;

public class FreeLineShape extends APDRShape{
	private static final long serialVersionUID = -1035475723319493551L;

	@Override
	public Shape newShape(Vector<Float> points) {return makeLine(points);}
	
	private static GeneralPath makeLine(Vector<Float> points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points.get(0).x, points.get(0).y);
		for(Point2D.Float p : points){path.lineTo(p.x, p.y);}
		return path;
	}

	public Shape newTwoPointShape(Float p1, Float p2) {return null;}//¾È¾¸.

	@Override
	public boolean needFill() {return false;}
}
