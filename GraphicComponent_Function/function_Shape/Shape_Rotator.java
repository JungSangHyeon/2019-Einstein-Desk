package function_Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;
import zFunction_Stuff.AFunction;

public class Shape_Rotator extends AFunction implements Serializable{
	private static final long serialVersionUID = 3215043540942308860L;
	
	Color anchorColor = new Color(150, 150, 150);
	float anchorSize = 20, anchorDistance =30, lineThick = 2, gap = 2;
	Color insideAnchorColor = Color.WHITE;
	
	Point2D.Float dragStart;
	Shape anchor;
	boolean rotateOn = false;
	
	public Shape_Rotator() {//���߿� ���̺��,...
		topPaint = true;
	}
	
	public void mousePressed(MouseEvent e) {//�Ʒ� if���� ����.
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());}//�г��� �̰�.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer�� �̰�.
		if(anchor!=null&&anchor.contains(dragStart)) {rotateOn = true;}
	}

	public void paintComponent(Graphics2D g, Shape shape) {
		if(master.isSelected()) {
			master.removeTopFunctionShape(anchor);
			master.removeFunctionShape(anchor);
			Rectangle2D beforeRotateBorder = getBeforeRotateBorder();
			AffineTransform at = new AffineTransform();
			
			double angle = master.getAngle();
			if(master.getUpsideDown()) {angle+=180;}
			at.setToRotation(Math.toRadians(angle), beforeRotateBorder.getCenterX(), beforeRotateBorder.getCenterY());
			
			float scale = DrawingPanelMoveAndZoom.getScale();
			float scaleLineThick = lineThick/scale;
			float scaleAnchorDistance = anchorDistance/scale;
			float scaleAnchorSize = anchorSize/scale;
			float scaleGap = gap/scale;
			float scaleMasterBorderThick = master.getBorderThick()*2;
			
			//HAHAHAHAH
			Rectangle2D.Double beforeRotateBar = new Rectangle2D.Double(beforeRotateBorder.getCenterX()-scaleLineThick/2, beforeRotateBorder.getY()-scaleAnchorDistance-scaleMasterBorderThick/2, scaleLineThick, scaleAnchorDistance);
			Ellipse2D.Double beforeRotateAnchor = new Ellipse2D.Double(beforeRotateBorder.getCenterX()-scaleAnchorSize/2, beforeRotateBorder.getY()-scaleAnchorSize-scaleAnchorDistance-scaleMasterBorderThick/2,scaleAnchorSize,scaleAnchorSize);
			Rectangle2D rect = beforeRotateAnchor.getBounds2D();
			Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleGap, rect.getY()+scaleGap, rect.getWidth()-scaleGap*2, rect.getHeight()-scaleGap*2);
			anchor = at.createTransformedShape(beforeRotateAnchor);
			master.addTopFunctionShape(anchor);
			master.addFunctionShape(anchor);
			
			g.setColor(anchorColor);
			
			Stroke temp = g.getStroke();
			g.setStroke(new BasicStroke(lineThick/DrawingPanelMoveAndZoom.getScale(), BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			
			if(AnchorPaint.isOn()) {
			g.fill(at.createTransformedShape(beforeRotateBar));//rotate Bar
			g.fill(anchor);
			}
			
			g.setColor(insideAnchorColor);
			if(AnchorPaint.isOn()) {
			g.fill(at.createTransformedShape(beforeRotateInsideAnchor));//rotate Bar
			}
			g.setStroke(temp);
		}
	}
	
	public void mouseDragged(MouseEvent e) {//�ϳ��� �ٰ��� �ϴ°� �̰� ����ƽ���� ����� �� �� �ְڴ�.
		if(AnchorPaint.isOn()) {
			AnchorPaint.off();
		}
		if(rotateOn) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//get Angle
			Point2D.Float center = master.getCenter();//���� ���� �� �ְڴ�. ������ ���� || �ϳ��� ����.
			double rotationAngle = computeRotationAngle(center, dragStart, nowPoint);
			
			for(GraphicComponent gc : GCStorage.getSelectedGCVector()) {
				AffineTransform at = new AffineTransform();//get AT
				at.setToRotation(Math.toRadians(rotationAngle), gc.getCenter().getX(), gc.getCenter().getY());
				gc.setShape(at.createTransformedShape(gc.getShape()));//Rotate Shape
				for(Point2D.Float point : gc.getPoints()) {//Rotate Points
					Point2D.Float cpoint = transformPoint(at,point);
					point.setLocation(cpoint.x, cpoint.y);
				}
				gc.addAngle(rotationAngle);//add Angle
			}
			dragStart = nowPoint;
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()&&anchor.contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {//mouse On this
			((JPanel) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public double computeRotationAngle(Point2D center, Point2D previous, Point2D current) {	//Giggle Giggle	
		double startAngle = Math.toDegrees(Math.atan2(center.getX()-previous.getX(), center.getY()-previous.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX()-current.getX(), center.getY()-current.getY()));
		double rotationAngle = startAngle-endAngle;
		if (rotationAngle < 0) {rotationAngle += 360;}
		return rotationAngle;
	}
	
	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	public void mouseReleased(MouseEvent e) {
		rotateOn = false;
		AnchorPaint.on();}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
