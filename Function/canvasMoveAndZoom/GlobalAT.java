package canvasMoveAndZoom;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GlobalAT {

	static int maxZoomLevel = 10, minZoomLevel = -50;//알어서 바꿔 쓰시오
	static float zoomFactor = 1.2f;
	
	private static int zoomLevel = 0;
	private static Point dragStartPoint;
	static AffineTransform nowAT = new AffineTransform();
	
	public static void clear() {zoomLevel = 0; nowAT = new AffineTransform();}
	public static AffineTransform getNowAT() {return nowAT;}
	public static float getZoom() {return (float) nowAT.getScaleX();}
	
	public static void zoomCamera(MouseWheelEvent e) {
		if(switchOn) {
			Point2D p1 = transformPoint(e.getPoint());
			if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++; nowAT.scale(1 / zoomFactor, 1 / zoomFactor);}
			else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;nowAT.scale(zoomFactor, zoomFactor);}
			Point2D p2 = transformPoint(e.getPoint());
			nowAT.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		}
	}

	public static void setDragStartPoint(Point p) {dragStartPoint = p;}
	public static void moveCamera(MouseEvent e) {
		if(switchOn) {
			Point dragEndPoint = e.getPoint();
			Point2D.Float dragStart = transformPoint(dragStartPoint), dragEnd = transformPoint(dragEndPoint);
			nowAT.translate(dragEnd.getX() - dragStart.getX(), dragEnd.getY() - dragStart.getY());
			dragStartPoint = dragEndPoint;
		}
	}

	public static Point2D.Float transformPoint(Point p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {nowAT.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	public static void setAT(AffineTransform at) {nowAT = at;}
	
	static boolean switchOn = true;
	public static void off() {switchOn = false;}
	public static void on() {switchOn = true;}
}