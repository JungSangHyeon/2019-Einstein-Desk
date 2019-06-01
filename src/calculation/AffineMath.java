package calculation;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import zStuff_GraphicComponent.GraphicComponent;

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
		AffineTransform at = AffineMath.getRotateAffineTransform(angle, center);
		AffineMath.applyAffineTransformToGC(at, target);
		target.addAngle(angle);
	}

	public static AffineTransform getRotateAffineTransform(double angle, Point2D center) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle), center.getX(), center.getY());
		return at;
	}

	public static AffineTransform getMoveAffineTransform(Point2D dragStart, Point2D nowPoint) {
		AffineTransform at = new AffineTransform();
		at.translate(nowPoint.getX()-dragStart.getX(), nowPoint.getY()-dragStart.getY());
		return at;
	}

	public static void applyAffineTransformToGC(AffineTransform at, GraphicComponent gc) {
		gc.setCenter(AffineMath.transformPoint(at, gc.getCenter()));
		gc.setShape(at.createTransformedShape(gc.getShape()));
		for(Point2D.Float point : gc.getPoints()) {
			Point2D.Float cpoint = AffineMath.transformPoint(at,point);
			point.setLocation(cpoint.x, cpoint.y);
		}
	}
	
	public static Vector<Point2D.Float> getBeforeRotatePoints(GraphicComponent gc) {
		Vector<Point2D.Float> pointBeforeRotate = new Vector<Point2D.Float>();
		AffineTransform at = AffineMath.getRotateAffineTransform(-Math.toRadians(gc.getAngle()), gc.getCenter());
		for (Point2D.Float p : gc.getPoints()) {pointBeforeRotate.add(AffineMath.transformPoint(at, p));}
		return pointBeforeRotate;
	}
	
}
