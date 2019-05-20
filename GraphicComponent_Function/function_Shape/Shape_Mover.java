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

import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;
import zFunction_Stuff.AFunction;

public class Shape_Mover extends AFunction implements Serializable{//셀렉트 된애의 것만 실행하고, 여기서 셀렉트된 애들 전부를 바꾸는 것로
	private static final long serialVersionUID = 2509847208800494236L;
	
	Color rectColor = new Color(150, 150, 150);
	int rectBorderThick =2;
	
	Point2D.Float dragStart;
	boolean moveOn = false;
	
	public void mousePressed(MouseEvent e) {//if 제거?
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
		
		if(master.getAShape().isSelected(master, dragStart)) {
			moveOn = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(dragStart)) {moveOn = false;}
			}
		}else {
			moveOn = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(moveOn) {
			Point2D.Float nowPoint;
			if(GCStorage.have(master)) {nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());}
			else {nowPoint = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}
			
			AffineTransform at = new AffineTransform();
			at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
			master.setShape(at.createTransformedShape(master.getShape()));
			
			for(Point2D.Float point : master.getPoints()) {
				point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
			}
			
			master.setCenter(new Point2D.Float(master.getCenter().x+nowPoint.x-dragStart.x, master.getCenter().y+nowPoint.y-dragStart.y));//이거 대체 방법 없냥.
			dragStart = nowPoint;
		}
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if(master.isSelected()) {
			Rectangle2D masterBorder = getBeforeRotateBorder();
			float factor = master.getBorderThick()*2;
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			Rectangle2D.Double beforeRotateRect = new Rectangle2D.Double(masterBorder.getX()-factor/2, masterBorder.getY()-factor/2, masterBorder.getWidth()+factor, masterBorder.getHeight()+factor);
			
			float scaleRectBorderThick = rectBorderThick;
			if(DrawingPanelMoveAndZoom.getScale()>1) {scaleRectBorderThick = rectBorderThick/DrawingPanelMoveAndZoom.getScale();}
			
			g.setColor(rectColor);
			Stroke temp = g.getStroke();
			g.setStroke(new BasicStroke(scaleRectBorderThick, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			g.draw(at.createTransformedShape(beforeRotateRect));
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
	
	public void mouseReleased(MouseEvent e) {moveOn = false;}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()&&master.getShape().contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {//mouse On this
			boolean onlyOnShape = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {onlyOnShape = false;}
			}
			if(onlyOnShape) {
				((JPanel) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
