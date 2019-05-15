package function;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import data.GCStorage;
import function_Stuff.AFunction;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Mover extends AFunction implements Serializable{
	private static final long serialVersionUID = 2509847208800494236L;
	
	Point2D.Float dragStart;
	
	public void mousePressed(MouseEvent e) {
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
	}

	public void mouseDragged(MouseEvent e) {
		move(e);
	}

	private void move(MouseEvent e) {
		Point2D.Float nowPoint;
		if(GCStorage.have(master)) {nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getXOnScreen(), e.getYOnScreen()));}
		else {nowPoint = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}
		
		AffineTransform at = new AffineTransform();
		at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
		master.setShape(at.createTransformedShape(master.getShape()));
		
		
		for(Point2D.Float point : master.getPoints()) {
			point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
		}
		
		dragStart = nowPoint;
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void paintComponent(Graphics g, Shape shape) {}
	
}
