package zStuff_Shape;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import zStuff_Tool.ATool;
import zStuff_Tool.eTool;

public abstract class ACMCShape extends AShape{
	private static final long serialVersionUID = 1525189896296024481L;

	public abstract Shape newShape(Vector<Point2D.Float> points);
	
	public ATool getDrawer() {return eTool.eCMCShapeDrawTool.getATool();}
}
