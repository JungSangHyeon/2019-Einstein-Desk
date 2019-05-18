package function;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

import data.GCStorage;
import function_Stuff.AFunction;
import global.Calculator;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Rotator extends AFunction implements Serializable{
	private static final long serialVersionUID = 3215043540942308860L;
	
	Color anchorColor = new Color(150, 150, 150);
	float anchorSize = 20, anchorDistance =30, lineThick = 2;
	
	Point2D.Float dragStart;
	Shape anchor;
	boolean rotateOn = false;
	
	public void mousePressed(MouseEvent e) {//아래 if제거 생각.
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
		if(anchor!=null&&anchor.contains(dragStart)) {rotateOn = true;}
	}

	
	public void mouseDragged(MouseEvent e) {//하나로 다같이 하는건 이걸 스태틱으로 만들면 할 수 있겠다.
		if(rotateOn) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));
			Rectangle2D rect = master.getShape().getBounds2D();
			Point2D.Double center = new Point2D.Double(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
			double rotationAngle = computeRotationAngle(center, dragStart, nowPoint);
			master.addAngle(rotationAngle);
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(rotationAngle), center.getX(), center.getY());
			master.setShape(at.createTransformedShape(master.getShape()));
			dragStart = nowPoint;
			
			for(Point2D.Float point : master.getPoints()) {
				Point2D.Float cpoint = transformPoint(at,point);
				point.setLocation(cpoint.x, cpoint.y);
			}
		}
	}

	public double computeRotationAngle(Point2D center, Point2D previous, Point2D current) {	//Giggle Giggle	
		double startAngle = Math.toDegrees(Math.atan2(center.getX()-previous.getX(), center.getY()-previous.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX()-current.getX(), center.getY()-current.getY()));
		double rotationAngle = startAngle-endAngle;
		if (rotationAngle < 0) {rotationAngle += 360;}
		return rotationAngle;
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		master.removeFunctionShape(anchor);
		if(master.isSelected()) {
			Rectangle2D masterBorder = getBeforeRotateBorder();
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			g.setColor(anchorColor);
			g.setStroke(new BasicStroke(lineThick, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			anchor = at.createTransformedShape(new Ellipse2D.Double(masterBorder.getX()+masterBorder.getWidth()/2-anchorSize/2, masterBorder.getY()-anchorSize-anchorDistance, anchorSize,anchorSize));
			master.addFunctionShape(anchor);
			g.fill(anchor);
		}
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		Vector<Point2D.Float> pointBeforeRotate =  new Vector<Point2D.Float>();
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), rect.getCenterX(), rect.getCenterY());
		for(Point2D.Float p : master.getPoints()) {pointBeforeRotate.add(transformPoint(at,p));}
		return master.getAShape().newShape(pointBeforeRotate).getBounds();
	}
	
	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	public void mouseReleased(MouseEvent e) {rotateOn = false;}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
