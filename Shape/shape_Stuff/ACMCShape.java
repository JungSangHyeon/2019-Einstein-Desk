package shape_Stuff;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import tool_Stuff.ATool;
import tool_Stuff.eTool;

public abstract class ACMCShape extends AShape{
	public abstract Shape newShape(Vector<Point2D.Float> points);
	
	public ATool getDrawer() {return eTool.eCMCShapeDrawTool.getTool();}
}
