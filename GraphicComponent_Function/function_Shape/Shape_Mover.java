package function_Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;
import zFunction_Stuff.AFunction;

public class Shape_Mover extends AFunction implements Serializable{//����Ʈ �Ⱦ��� �͸� �����ϰ�, ���⼭ ����Ʈ�� �ֵ� ���θ� �ٲٴ� �ͷ�
	private static final long serialVersionUID = 2509847208800494236L;
	
	Color rectColor = new Color(150, 150, 150);
	int rectBorderThick =2;
	
	Point2D.Float dragStart;
	boolean moveOn = false;
	
	public Shape_Mover() {
		topPaint = true;
	}
	
	public void mousePressed(MouseEvent e) {//if ����?
		if(GCStorage_Normal.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());}//�г��� �̰�.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer�� �̰�.
		
		if(master.getAShape().thisGCIsSelected(master, dragStart)) {//&&master.getShape().contains(dragStart)
			moveOn = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(dragStart)) {moveOn = false;}
			}
		}else {
			moveOn = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(AnchorPaint.isOn()) {
			AnchorPaint.off();
		}
		if(moveOn) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());
			AffineTransform at = new AffineTransform();
			at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				gc.setShape(at.createTransformedShape(gc.getShape()));
				for(Point2D.Float point : gc.getPoints()) {
					point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
				}
				gc.setCenter(new Point2D.Float(gc.getCenter().x+nowPoint.x-dragStart.x, gc.getCenter().y+nowPoint.y-dragStart.y));//�̰� ��ü ��� ����.
			}
			dragStart = nowPoint;
		}
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if(master.isSelected()) {
			Rectangle2D masterBorder = getBeforeRotateBorder();
			float factor = master.getBorderThick();
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			Rectangle2D.Double beforeRotateRect = new Rectangle2D.Double(masterBorder.getX()-factor/2, masterBorder.getY()-factor/2, masterBorder.getWidth()+factor, masterBorder.getHeight()+factor);
			
			float scaleRectBorderThick = rectBorderThick/DrawingPanelMoveAndZoom.getScale();
//			float scaleRectBorderThick = rectBorderThick;
//			if(DrawingPanelMoveAndZoom.getScale()>1) {scaleRectBorderThick = rectBorderThick/DrawingPanelMoveAndZoom.getScale();}
			
			g.setColor(rectColor);
			Stroke temp = g.getStroke();
			g.setStroke(new BasicStroke(scaleRectBorderThick, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			if(AnchorPaint.isOn()) {
				g.draw(at.createTransformedShape(beforeRotateRect));
			}
			g.setStroke(temp);
		}
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	public void mouseReleased(MouseEvent e) {moveOn = false;AnchorPaint.on();}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()&&master.getShape().contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {//mouse On this
			boolean onlyOnShape = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {onlyOnShape = false;}
			}
			if(onlyOnShape) {
				((JPanel) e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void processSelectEvent(boolean selected) {}
}
