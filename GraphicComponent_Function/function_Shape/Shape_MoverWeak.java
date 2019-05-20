package function_Shape;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import zFunction_Stuff.AFunction;

public class Shape_MoverWeak extends AFunction implements Serializable{//����Ʈ �Ⱦ��� �͸� �����ϰ�, ���⼭ ����Ʈ�� �ֵ� ���θ� �ٲٴ� �ͷ�
	private static final long serialVersionUID = 2509847208800494236L;
	
	Point2D.Float dragStart;
	boolean moveOn = false;
	
	public void mousePressed(MouseEvent e) {//if ����?
		if(master.getAShape().isSelected(master, new Point2D.Float(e.getX(), e.getY()))) {
			dragStart = new Point2D.Float(e.getX(), e.getY());
			moveOn = true;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(moveOn) {
			Point2D.Float nowPoint = new Point2D.Float(e.getX(), e.getY());
			
			AffineTransform at = new AffineTransform();
			at.translate(nowPoint.x-dragStart.x, nowPoint.y-dragStart.y);
			master.setShape(at.createTransformedShape(master.getShape()));
			
			for(Point2D.Float point : master.getPoints()) {
				point.setLocation(point.x+nowPoint.x-dragStart.x, point.y+nowPoint.y-dragStart.y);
			}
			dragStart = nowPoint;
		}
	}
	
	public void mouseReleased(MouseEvent e) {moveOn = false;}
	
	public void paintComponent(Graphics2D g, Shape shape) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
