package canvasMoveAndZoom;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import calculation.AffineMath;
import canvas.CanvasGC;

public class DrawingPanelMoveAndZoom {

	static int maxZoomLevel = 10, minZoomLevel = -150;//알어서 바꿔 쓰시오
	static float zoomFactor = 1.2f;
//	public static float nowZoom = 1;
	static AffineTransform nowApplied = new AffineTransform();
	
	public static void clear() {
		zoomLevel = 0;
		nowApplied = new AffineTransform();
	}
	
	private static int zoomLevel = 0;
	private static Point dragStartPoint;
	
	public static AffineTransform getNowApplied() {
		return nowApplied;//x=y니께
	}
	
	public static void allZero() {
		AffineMath.applyAffineToAllGC(DrawingPanelMoveAndZoom.getBackToNormal());
		zoomLevel = 0;
		nowApplied = new AffineTransform();
		dragStartPoint = new Point();
	}
	
	public static AffineTransform getBackToNormal() {
		AffineTransform back = null;
		try {
			back = nowApplied.createInverse();
		} catch (NoninvertibleTransformException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(-CanvasGC.getX(), -CanvasGC.getY());//0604 //7:56 됨
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(- nowApplied.getTranslateX(), -nowApplied.getTranslateY());//이미지가 됨.
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
////		AffineMath.applyAffineTransformToGC(back, CanvasGC.getCanvas());
//		back.translate(300-CanvasGC.getX(), 300-CanvasGC.getY());//0604 //7:56 됨
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(1920/2-CanvasGC.getWidth()/nowApplied.getScaleX()/2-CanvasGC.getX(), 1080/2-CanvasGC.getHeight()/nowApplied.getScaleY()/2-CanvasGC.getY());
		
		return back;//x=y니께
	}
	
	public static float getZoom() {
		return (float)nowApplied.getScaleX();//x=y니께
	}
	
	public static void zoomCamera(MouseWheelEvent e) {//그래픽에다하면, 마우스 이벤트의 일반화가 안됨.
		AffineTransform coordTransform = new AffineTransform();
		Point2D p1 = e.getPoint();
		
		float zoom = 1;
		if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++;zoom = 1 / zoomFactor;}
		else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;zoom =zoomFactor;}
		coordTransform.scale(zoom, zoom); 
		nowApplied.scale(zoom, zoom); 
		
		Point2D.Float p2 = new Point2D.Float();
		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception ex) {ex.printStackTrace();}
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		nowApplied.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		
		AffineMath.applyAffineToAllGC(coordTransform);
	}

	public static void setDragStartPoint(Point p) {dragStartPoint = p;}
	public static void moveCamera(MouseEvent e) {
		AffineTransform coordTransform = new AffineTransform();
		Point dragEndPoint = e.getPoint();
		coordTransform.translate(dragEndPoint.getX() - dragStartPoint.getX(), dragEndPoint.getY() - dragStartPoint.getY());
		nowApplied.translate(dragEndPoint.getX() - dragStartPoint.getX(), dragEndPoint.getY() - dragStartPoint.getY());
		
		AffineMath.applyAffineToAllGC(coordTransform);
		dragStartPoint = dragEndPoint;
	}
	
}