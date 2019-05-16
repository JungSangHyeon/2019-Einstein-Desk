package function;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import data.GCStorage;
import function_Stuff.AFunction;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Rotator extends AFunction implements Serializable{
	private static final long serialVersionUID = 3215043540942308860L;
	
	Point2D.Float dragStart;
	
	public void mousePressed(MouseEvent e) {
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
	}

	public void mouseDragged(MouseEvent e) {//하나로 다같이 하는건 이걸 스태틱으로 만들면 할 수 있겠다.
		Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));
		Rectangle2D rect = master.getShape().getBounds2D();
		Point2D.Double center = new Point2D.Double(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
		double rotationAngle = computeRotationAngle(center, dragStart, nowPoint);
		
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(rotationAngle), center.getX(), center.getY());
		master.setShape(at.createTransformedShape(master.getShape()));
		dragStart = nowPoint;
		//point도 돌려줘.
		
		
		for(Point2D.Float point : master.getPoints()) {
			Point2D.Float cpoint = transformPoint(at,point);
			point.setLocation(cpoint.x, cpoint.y);
		}
		
		for(Point2D.Float point : master.getPoints()) {
			point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
		}
	}

	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	private double computeRotationAngle(Point2D center, Point2D previous, Point2D current) {	//Giggle Giggle	
		double startAngle = Math.toDegrees(Math.atan2(center.getX()-previous.getX(), center.getY()-previous.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX()-current.getX(), center.getY()-current.getY()));
		double rotationAngle = startAngle-endAngle;
		if (rotationAngle < 0) {rotationAngle += 360;}
		return rotationAngle;
	}
		
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
	
}
