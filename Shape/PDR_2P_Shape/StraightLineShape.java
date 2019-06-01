package PDR_2P_Shape;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import calculation.Calculator;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.APDRShape;

public class StraightLineShape extends APDRShape{
	private static final long serialVersionUID = 8622361566770203159L;

	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return new Line2D.Float(p1.x,p1.y,p2.x,p2.y);
	}
	
	@Override
	public boolean thisGCIsSelected(GraphicComponent gc, Point2D.Float p) {
		if(gc.getPoints().get(0).distance(p)<gc.getBorderThick()/2) {return true;}
		for(int i=0; i<gc.getPoints().size()-1; i++) {
			if(Calculator.distanceLineNPoint(gc.getPoints().get(i), gc.getPoints().get(i+1), p)<gc.getBorderThick()/2) {return true;}
		}
		for(Shape s : gc.getFunctionShape()) {
			if(s.contains(p)) {
				return true;
			}
		}
		return false;
	}
	
}
