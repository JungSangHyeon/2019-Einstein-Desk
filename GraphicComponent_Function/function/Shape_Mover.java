package function;

import java.awt.BasicStroke;
import java.awt.Color;
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

public class Shape_Mover extends AFunction implements Serializable{//셀렉트 된애의 것만 실행하고, 여기서 셀렉트된 애들 전부를 바꾸는 것로
	private static final long serialVersionUID = 2509847208800494236L;
	
	Point2D.Float dragStart;
	boolean moveOn = false;
	
	public void mousePressed(MouseEvent e) {
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
		
		moveOn = true;
		for(Shape s : master.getAggreShape()) {
			if(s.contains(dragStart)) {
				moveOn = false;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(moveOn) {
			move(e);
		}
	}

	private void move(MouseEvent e) {
		Point2D.Float nowPoint;
		if(GCStorage.have(master)) {nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));}
		else {nowPoint = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}
		
		AffineTransform at = new AffineTransform();
		at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
		master.setShape(at.createTransformedShape(master.getShape()));
		
		
		for(Point2D.Float point : master.getPoints()) {
			point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
		}
		
		dragStart = nowPoint;
	}
	
	public void mouseReleased(MouseEvent e) {moveOn = false;}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		
		if(master.isSelected()) {
			Rectangle2D masterBorder = shape.getBounds2D();
			float thick = master.getBorderThick();
			g.setColor(new Color(150, 150, 150));
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			float factor = thick*2;
			g.draw(new Rectangle2D.Double(masterBorder.getX()-factor/2, masterBorder.getY()-factor/2, masterBorder.getWidth()+factor, masterBorder.getHeight()+factor));
		}
		
		if(dragStart!=null) {
			g.fill(new Rectangle2D.Double(dragStart.x, dragStart.y, 10,10));
		}
	}
	
}
