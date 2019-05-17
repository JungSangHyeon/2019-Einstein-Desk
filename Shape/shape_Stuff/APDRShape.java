package shape_Stuff;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import moveAndZoom.DrawingPanelMoveAndZoom;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public abstract class APDRShape extends AShape {
	private static final long serialVersionUID = 1706408955698326860L;

	public Shape newShape(Vector<Point2D.Float> points) {
		return newTwoPointShape(points.get(0), points.lastElement());
	}
	
	public abstract Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2);
	
	public ATool getDrawer() {return eTool.ePDRShapeDrawTool.getTool();}
	
	public boolean isSelected(GraphicComponent gc, Point2D.Float point) {
		if(gc.getShape().contains(point)) {return true;}
		
		for(Shape s : gc.getAggreShape()) {
			if(s.contains(point)) {
				return true;
			}
		}
		return false;
	}
	
}
