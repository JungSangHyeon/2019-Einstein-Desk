package shape_Stuff;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import tool_Stuff.ATool;

public abstract class AShape {
	public abstract Shape newShape(Vector<Point2D.Float> points);
	public abstract ATool getDrawer();
}
