package shape_Stuff;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import function_Stuff.ATool;
import function_Stuff.eTool;

public abstract class ACMCShape extends AShape{
	private static final long serialVersionUID = 1525189896296024481L;

	public abstract Shape newShape(Vector<Point2D.Float> points);
	
	public ATool getDrawer() {return eTool.eCMCShapeDrawTool.getATool();}
}
