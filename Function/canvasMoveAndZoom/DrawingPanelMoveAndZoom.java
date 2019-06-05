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

	static int maxZoomLevel = 10, minZoomLevel = -150;//�˾ �ٲ� ���ÿ�
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
		return nowApplied;//x=y�ϲ�
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
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(-CanvasGC.getX(), -CanvasGC.getY());//0604 //7:56 ��
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(- nowApplied.getTranslateX(), -nowApplied.getTranslateY());//�̹����� ��.
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
////		AffineMath.applyAffineTransformToGC(back, CanvasGC.getCanvas());
//		back.translate(300-CanvasGC.getX(), 300-CanvasGC.getY());//0604 //7:56 ��
		
//		AffineTransform back = new AffineTransform();
//		back.scale(1/nowApplied.getScaleX(), 1/nowApplied.getScaleY());
//		back.translate(1920/2-CanvasGC.getWidth()/nowApplied.getScaleX()/2-CanvasGC.getX(), 1080/2-CanvasGC.getHeight()/nowApplied.getScaleY()/2-CanvasGC.getY());
		
		return back;//x=y�ϲ�
	}
	
	public static float getZoom() {
		return (float)nowApplied.getScaleX();//x=y�ϲ�
	}
	
	public static void zoomCamera(MouseWheelEvent e) {//�׷��ȿ����ϸ�, ���콺 �̺�Ʈ�� �Ϲ�ȭ�� �ȵ�.
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