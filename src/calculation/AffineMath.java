package calculation;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import component_Stuff.GraphicComponent;

public class AffineMath {

	public static Shape getRotateShape(Shape targetShape, double angle, Point2D center) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());
		return at.createTransformedShape(targetShape);
	}
	
	public static Point2D.Float transformPoint(AffineTransform at, Point2D.Float targetPoint)  {
		Point2D.Float resultPoint = new Point2D.Float();
		try {at.transform(targetPoint, resultPoint);}catch (Exception e) {e.printStackTrace();}
		return resultPoint;
	}

	public static void rotateGC(double angle, Point2D center, GraphicComponent target) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());//make AT
		target.setShape(at.createTransformedShape(target.getShape()));//Rotate Shape
		target.setMyCenter(AffineMath.transformPoint(at, target.getCenter()));//Rotate Center
		for(Point2D.Float point : target.getPoints()) {//Rotate Points
			Point2D.Float cpoint = AffineMath.transformPoint(at,point);
			point.setLocation(cpoint.x, cpoint.y);
		}
		target.addAngle(angle);//add Angle
	}
	
}
