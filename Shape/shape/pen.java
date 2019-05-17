package shape;


import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import global.Calculator;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape_Stuff.APDRShape;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class pen extends APDRShape{
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
	public ATool getDrawer() {return eTool.ePenDrawTool.getTool();}
	
	@Override
	public boolean isSelected(GraphicComponent gc, Point2D.Float point) {
		if(gc.getPoints().get(0).distance(point)<gc.getBorderThick()/2) {return true;}//Á¡®G
		for(int i=0; i<gc.getPoints().size()-1; i++) {
			if(Calculator.distanceLineNPoint(gc.getPoints().get(i), gc.getPoints().get(i+1), point)<gc.getBorderThick()/2) {return true;}
		}
		for(Shape s : gc.getAggreShape()) {
			if(s.contains(point)) {
				return true;
			}
		}
		return false;
	}
	
}
