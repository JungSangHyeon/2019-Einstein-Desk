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
import global.GConstants.EAnchors;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Resizer extends AFunction implements Serializable{
	private static final long serialVersionUID = 181931757073293064L;
	
	Point2D.Float dragStart;
	
	public void mousePressed(MouseEvent e) {
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
	}

	public void mouseDragged(MouseEvent e) {//하나로 다같이 하는건 이걸 스태틱으로 만들면 할 수 있겠다.
//		Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));
//		Rectangle2D rect = master.getShape().getBounds2D();
//		Point2D.Double center = new Point2D.Double(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
//		double rotationAngle = computeRotationAngle(center, dragStart, nowPoint);
//		
//		AffineTransform at = new AffineTransform();
//		at.setToRotation(Math.toRadians(rotationAngle), center.getX(), center.getY());
//		master.setShape(at.createTransformedShape(master.getShape()));
//		dragStart = nowPoint;
//		//point도 돌려줘.
//		
//		
//		for(Point2D.Float point : master.getPoints()) {
//			Point2D.Float cpoint = transformPoint(at,point);
//			point.setLocation(cpoint.x, cpoint.y);
//		}
//		
//		for(Point2D.Float point : master.getPoints()) {
//			point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
//		}
	}

	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	
	
	public Point2D computeResizeFactor(Point2D previous, Point2D current) {
		double px = previous.getX();
		double py = previous.getY();
		double cx = current.getX();
		double cy = current.getY();
		double width = this.selectedShape.getWidth();
		double height = this.selectedShape.getHeight();
		double deltaW = 0;
		double deltaH = 0;
		
		switch (this.selectedShape.getSelectedAnchor()) {
			case E:  deltaW =  cx-px; 	deltaH=  0; 	 break;
			case W:  deltaW =-(cx-px);	deltaH=  0; 	 break;
			case S:  deltaW =  0;		deltaH=  cy-py;  break;
			case N:  deltaW =  0;		deltaH=-(cy-py); break;
			case SE: deltaW =  cx-px; 	deltaH=  cy-py;	 break;
			case NE: deltaW =  cx-px; 	deltaH=-(cy-py); break;
			case SW: deltaW =-(cx-px);	deltaH=  cy-py;	 break;	
			case NW: deltaW =-(cx-px);	deltaH=-(cy-py); break;
			default: break;
		}
		// compute resize 
		double xFactor = 1.0;
		double yFactor = 1.0;
		if (width > 0.0)
			xFactor = deltaW / width + xFactor;
		if (height > 0.0)			
			yFactor = deltaH / height + yFactor;
		
		return new Point2D.Double(xFactor, yFactor);
	}
	
	@Override
	public void transform(Graphics2D g2D, int x, int y) {
		EAnchors eSelectedAnchor = this.selectedShape.getSelectedAnchor();
		Point2D resizeOrigin = this.selectedShape.getResizeOrigin(eSelectedAnchor);
		Point2D resizeFactor = this.computeResizeFactor(this.previous, new Point2D.Double(x, y));
		this.selectedShape.resize(resizeFactor, resizeOrigin, eSelectedAnchor);
	}
	
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void paintComponent(Graphics2D g, Shape shape) {}
	
}
