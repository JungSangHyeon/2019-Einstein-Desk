package CMC_NP_Shape;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

import calculation.Calculator;
import moveAndZoom.DrawingPanelMoveAndZoom;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.ACMCShape;

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
	public boolean thisGCIsSelected(GraphicComponent gc, Point2D.Float point) {
		if(gc.getShape().contains(point)) {return true;}
		if(gc.getPoints().get(0).distance(point)<gc.getBorderThick()/2) {return true;}
		if(Calculator.distanceLineNPoint(gc.getPoints().get(gc.getPoints().size()-1), gc.getPoints().get(0), point)<gc.getBorderThick()/2) {return true;}
		for(int i=0; i<gc.getPoints().size()-1; i++) {
			if(Calculator.distanceLineNPoint(gc.getPoints().get(i), gc.getPoints().get(i+1), point)<gc.getBorderThick()/2) {return true;}
		}
		for(Shape s : gc.getFunctionShape()) {
			if(s.contains(point)) {
				return true;
			}
		}
		return false;
	}
	
}
