package moveAndZoom;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class PrivateMoveAndZoom {

	int maxZoomLevel = 10, minZoomLevel = -20;//알어서 바꿔 쓰시오
	float zoomFactor = 1.2f;
	
	protected AffineTransform coordTransform = new AffineTransform();
	private Point dragStartPoint, dragEndPoint;
	private int zoomLevel = 0;
	
	public void zoomCamera(MouseWheelEvent e) {
		Point2D p1 = transformPoint(e.getPoint());
		if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {zoomLevel++;coordTransform.scale(1 / zoomFactor, 1 / zoomFactor);}
		else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){zoomLevel--;coordTransform.scale(zoomFactor, zoomFactor);}
		Point2D p2 = transformPoint(e.getPoint());
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public void zoom(Point p, float xScale, float yScale) {
		Point2D p1 = transformPoint(p);
		coordTransform.scale(xScale, yScale);
		Point2D p2 = transformPoint(p);
		coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	
	public void moveCamera(MouseEvent e) {
		dragEndPoint = e.getPoint();
		Point2D.Float dragStart = transformPoint(dragStartPoint), dragEnd = transformPoint(dragEndPoint);
		coordTransform.translate(dragEnd.getX() - dragStart.getX(), dragEnd.getY() - dragStart.getY());
		dragStartPoint = dragEndPoint;
	}

	public Point2D.Float transformPoint(Point p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {coordTransform.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}

	public void setDragStartPoint(Point p) {dragStartPoint = p;}
	public AffineTransform getAT() {return coordTransform;}
}