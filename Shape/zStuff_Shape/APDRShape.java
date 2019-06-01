package zStuff_Shape;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Vector;

import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Tool.ATool;
import zStuff_Tool.eTool;

public abstract class APDRShape extends AShape {
	private static final long serialVersionUID = 1706408955698326860L;

	public Shape newShape(Vector<Point2D.Float> points) {
		return newTwoPointShape(points.get(0), points.lastElement());
	}
	
	public abstract Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2);
	
	public ATool getDrawer() {return eTool.ePDRShapeDrawTool.getATool();}
	
	public boolean thisGCIsSelected(GraphicComponent gc, Point2D.Float point) {
		if(gc.getShape().contains(point)) {return true;}
		
		for(Shape s : gc.getFunctionShape()) {
			if(s.contains(point)) {
				return true;
			}
		}
		return false;
	}
	
}
