package function_Shape;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import zFunction_Stuff.AFunction;

public class Shape_MoverWeak extends AFunction implements Serializable {// 셀렉트 된애의 것만 실행하고, 여기서 셀렉트된 애들 전부를 바꾸는 것로
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

		for (Point2D.Float point : master.getPoints()) {
			point.setLocation(point.x + nowPoint.x - dragStart.x, point.y + nowPoint.y - dragStart.y);
		}
		dragStart = nowPoint;
	}
}
