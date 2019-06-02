package canvasMoveAndZoom;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import calculation.AffineMath;
import canvas.CanvasGC;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;

public class DrawingPanelMoveAndZoom {

	static int maxZoomLevel = 10, minZoomLevel = -20;//알어서 바꿔 쓰시오
	static float zoomFactor = 1.2f;
	
//	protected static AffineTransform coordTransform = new AffineTransform();
	private static Point dragStartPoint, dragEndPoint;
	private static int zoomLevel = 0;
	
	public static void zoomCamera(MouseWheelEvent e) {
		AffineTransform coordTransform = new AffineTransform();
		Point2D p1 = transformPoint(e.getPoint());
		System.out.println("p1 : "+p1);
		if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++;coordTransform.scale(1 / zoomFactor, 1 / zoomFactor);}
		else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;coordTransform.scale(zoomFactor, zoomFactor);}
		
		Point2D.Float p2 = new Point2D.Float();
		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception ex) {ex.printStackTrace();}
//		Point2D p2 = transformPoint(e.getPoint());
		
		System.out.println("p2 : "+p2);
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			AffineMath.applyAffineTransformToGC(coordTransform, gc);
		}
		AffineMath.applyAffineTransformToGC(coordTransform, CanvasGC.getCanvas());
	}

	public static void moveCamera(MouseEvent e) {
		AffineTransform coordTransform = new AffineTransform();
		dragEndPoint = e.getPoint();
		Point2D.Float dragStart = transformPoint(dragStartPoint), dragEnd = transformPoint(dragEndPoint);
		coordTransform.translate(dragEnd.getX() - dragStart.getX(), dragEnd.getY() - dragStart.getY());
		
		for(GraphicComponent gc : GCStorage_Normal.getGCVector()) {
			AffineMath.applyAffineTransformToGC(coordTransform, gc);
		}
		AffineMath.applyAffineTransformToGC(coordTransform, CanvasGC.getCanvas());
		dragStartPoint = dragEndPoint;
	}

	public static Point2D.Float transformPoint(Point p1)  {
//		Point2D.Float p2 = new Point2D.Float();
//		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
//		return p2;
		return new Point2D.Float(p1.x, p1.y);
	}
	
	public static void setDragStartPoint(Point p) {dragStartPoint = p;}
//	public static AffineTransform getAT() {return coordTransform;}
	public static float getScale() {return 1;}
//	public static float getScale() {return  (float)coordTransform.getScaleX();}
}