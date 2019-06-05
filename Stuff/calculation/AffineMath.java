package calculation;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import canvas.CanvasGC;
import slide.SlideManager;
import zStuff_GraphicComponent.GCStorage_Normal;
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
	
	public static void rotateGC(double angle, Point2D center, GraphicComponent target) {
		AffineTransform at = AffineMath.getRotateAffineTransform(angle, center);
		AffineMath.applyAffineTransformToGC(at, target);
		target.addAngle(angle);
	}
	
	public static Vector<Point2D.Float> getBeforeRotatePoints(GraphicComponent gc) {
		Vector<Point2D.Float> pointBeforeRotate = new Vector<Point2D.Float>();
		AffineTransform at = AffineMath.getRotateAffineTransform(-Math.toRadians(gc.getAngle()), gc.getCenter());
		for (Point2D.Float p : gc.getPoints()) {pointBeforeRotate.add(AffineMath.transformPoint(at, p));}
		return pointBeforeRotate;
	}
	
	public static void applyAffineToAllGC(AffineTransform at) {
//		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
//			AffineMath.applyAffineTransformToGC(at, gc);
//			gc.setborderThick(gc.getBorderThick()*(float)(at.getScaleX()));
//			for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
//				AffineMath.applyAffineTransformToGC(at, aggreGC);
//				aggreGC.setborderThick(aggreGC.getBorderThick()*(float)(at.getScaleX()));
//			}
//		}
		AffineMath.applyAffineTransformToGC(at, CanvasGC.getCanvas());
		CanvasGC.getCanvas().setborderThick(CanvasGC.getCanvas().getBorderThick()*(float)(at.getScaleX()));
		
		//add all slide
		for(Vector<GraphicComponent> slide : SlideManager.getSlideVector()) {
			for(GraphicComponent gc : slide) {
				AffineMath.applyAffineTransformToGC(at, gc);
				gc.setborderThick(gc.getBorderThick()*(float)(at.getScaleX()));
				for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
					AffineMath.applyAffineTransformToGC(at, aggreGC);
					aggreGC.setborderThick(aggreGC.getBorderThick()*(float)(at.getScaleX()));
				}
			}
//		if(!SlideManager.isNowSlide(slide)) {
//			
//		}	
		}
	}
	
	public static void applyAffineToThisGCVector(Vector<GraphicComponent>gcVector, AffineTransform at) {
		for(GraphicComponent gc : gcVector) {
			AffineMath.applyAffineTransformToGC(at, gc);
			gc.setborderThick(gc.getBorderThick()*(float)(at.getScaleX()));
			for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
				AffineMath.applyAffineTransformToGC(at, aggreGC);
				aggreGC.setborderThick(aggreGC.getBorderThick()*(float)(at.getScaleX()));
			}
		}
		AffineMath.applyAffineTransformToGC(at, CanvasGC.getCanvas());
		CanvasGC.getCanvas().setborderThick(CanvasGC.getCanvas().getBorderThick()*(float)(at.getScaleX()));
	}
}

