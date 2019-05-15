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
import shape_Stuff.ACMCShape;

public class PolygonShape extends ACMCShape{
	private static final long serialVersionUID = 4276418385726487576L;

	public Shape newShape(Vector<Float> points) {
		return makeClosedLine(points);
	}
	
	private static GeneralPath makeClosedLine(Vector<Float> points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points.get(0).x, points.get(0).y);
		for(Point2D.Float p : points){path.lineTo(p.x, p.y);}
		path.closePath();
		return path;
	}
	
	@Override
	public boolean isSelected(GraphicComponent gc, Point point) {
		if(gc.getShape().contains(DrawingPanelMoveAndZoom.transformPoint(point))) {return true;}
		if(gc.getPoints().get(0).distance(DrawingPanelMoveAndZoom.transformPoint(point))<gc.getBorderThick()/2) {return true;}
		if(Calculator.distanceLineNPoint(gc.getPoints().get(gc.getPoints().size()-1), gc.getPoints().get(0), DrawingPanelMoveAndZoom.transformPoint(point))<gc.getBorderThick()/2) {return true;}
		for(int i=0; i<gc.getPoints().size()-1; i++) {
			if(Calculator.distanceLineNPoint(gc.getPoints().get(i), gc.getPoints().get(i+1), DrawingPanelMoveAndZoom.transformPoint(point))<gc.getBorderThick()/2) {return true;}
		}
		return false;
	}
	
}
