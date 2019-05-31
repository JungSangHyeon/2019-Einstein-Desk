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
import data.GCStorage_Selected;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;
import zFunction_Stuff.AFunction;

public class Shape_Mover extends AFunction implements Serializable{//셀렉트 된애의 것만 실행하고, 여기서 셀렉트된 애들 전부를 바꾸는 것로
	private static final long serialVersionUID = 2509847208800494236L;
	
	Color rectColor = new Color(150, 150, 150);
	int rectBorderThick =2;
	
	Point2D.Float dragStart;
	boolean moveOn = false;
	
	public Shape_Mover() {
		topPaint = true;
	}
	
	public void mousePressed(MouseEvent e) {//if 제거?
		dragStart = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//패널은 이거.
		
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
				gc.setMyCenter(new Point2D.Float(gc.getCenter().x+nowPoint.x-dragStart.x, gc.getCenter().y+nowPoint.y-dragStart.y));//이거 대체 방법 없냥.
				
				for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
					aggreGC.setShape(at.createTransformedShape(aggreGC.getShape()));
					for(Point2D.Float point : aggreGC.getPoints()) {
						point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
					}
					aggreGC.setMyCenter(new Point2D.Float(aggreGC.getCenter().x+nowPoint.x-dragStart.x, aggreGC.getCenter().y+nowPoint.y-dragStart.y));//이거 대체 방법 없냥.
				}
			}
			dragStart = nowPoint;
		}
	}
	
	public void realPaint(Graphics2D g) {
		if(master.isSelected()) {
			Rectangle2D masterBorder = getBeforeRotateBorder();
			float factor = master.getBorderThick();
			AffineTransform at = new AffineTransform();
//			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			at.setToRotation(Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
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
}
