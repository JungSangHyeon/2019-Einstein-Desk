package fGCDataModify;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import zStuff_Function.AFunction;

public class FMove_Item extends AFunction implements Serializable {
	private static final long serialVersionUID = 2509847208800494236L;

	Point2D.Float dragStart;

	public void mousePressed(MouseEvent e) {
		dragStart = new Point2D.Float(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		Point2D.Float nowPoint = new Point2D.Float(e.getX(), e.getY());

		AffineTransform at = new AffineTransform();
		at.translate(nowPoint.x - dragStart.x, nowPoint.y - dragStart.y);
		
		master.setShape(at.createTransformedShape(master.getShape()));

		for (Point2D.Float point : master.getPoints()) {//어파인보다 더 빠를 느낌?
			point.setLocation(point.x + nowPoint.x - dragStart.x, point.y + nowPoint.y - dragStart.y);
		}
		dragStart = nowPoint;
	}
}
