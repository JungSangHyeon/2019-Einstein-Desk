package function;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Resizer_Temp extends AFunction implements Serializable {//히야 길다.
	private static final long serialVersionUID = 181931757073293064L;

	Color anchorColor = new Color(150, 150, 150);
	float anchorSize = 20;
	
	Point2D.Float dragStart;
	boolean resizeON = false;
	Vector<Shape> anchors = new Vector<Shape>();
	int n = 0;
	
	public void mousePressed(MouseEvent e) {
		if (GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));} // 패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());} // acontainer는 이거.
		n=0;
		resizeON = false;
		for(Shape s : anchors) {
			if(s.contains(dragStart)) {
				resizeON = true;
				break;
			}
			n++;
		}
	}

	
	public void mouseDragged(MouseEvent e) {// 하나로 다같이 하는건 이걸 스태틱으로 만들면 할 수 있겠다.
		//중심을 저장한다
		//기울어지기 전 도형을 얻어낸다.
		//현재 드래그 한것을 기울어지기 전에서 어땠을지 알아낸다.
		//위 두개 가지고 리사이즈 한다.
		//나온 것을 돌리기 전의 중심으로 돌려서 그린다.
		
		if (resizeON) {
			Rectangle2D rect = master.getShape().getBounds2D();
			Point2D.Double beforeCenter = new Point2D.Double(rect.getCenterX(), rect.getCenterY());//현재 중심저장.
			
//			Rectangle2D beforeRotateBorder = getBeforeRotateBorder();
			
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//드래그시작, 지금 점 돌림
			Point2D.Float normalDragStart = new Point2D.Float(nowPoint.x, nowPoint.y);
			nowPoint = getBeforeRotatePoint(nowPoint);
			dragStart = getBeforeRotatePoint(dragStart);
			
			AffineTransform at = new AffineTransform();
			Point2D resizeFactor = this.computeResizeFactor(this.dragStart, nowPoint);//돌린 것으로 리사이즈 팩터 만듬.
			
			Rectangle2D sr = getBeforeRotateAnchorBorder();//앵커를 돌리기 전으로 함
			
			at.setToTranslation(sr.getCenterX(), sr.getCenterY());// 축의 중심으로 만든다. //기준점? 을 바꿈.
			at.scale(resizeFactor.getX(), resizeFactor.getY());
			at.translate(-sr.getCenterX(), -sr.getCenterY());//원래 되는 AT완성?
			
			//돌리기전 포인트 들을 가져옴.
			//저거 적용 시킴. 이걸로 도형 만듬.
			//위의 포인트와 도형 둘다 돌림.
			Vector<Point2D.Float> beforePoint = getBeforeRotatePoints();
			for (Point2D.Float point : beforePoint) {
				Point2D.Float cpoint;
				cpoint = transformPoint(at, point);
				point.setLocation(cpoint.x, cpoint.y);
			}
			Shape beforeShape = master.getAShape().newShape(beforePoint);
			
			master.setPoints(beforePoint);
			master.setShape(beforeShape);
			
			AffineTransform at2 = new AffineTransform();
			at2.setToRotation(Math.toRadians(master.getAngle()), beforeCenter.getX(), beforeCenter.getY());
			master.setShape(at2.createTransformedShape(master.getShape()));
			
			for (Point2D.Float point : master.getPoints()) {
				Point2D.Float cpoint;
				cpoint = transformPoint(at2, point);
				point.setLocation(cpoint.x, cpoint.y);
			}
				
			
//			AffineTransform at2 = new AffineTransform();
//			at2.setToRotation(Math.toRadians(master.getAngle()), beforeCenter.getX(), beforeCenter.getY());
//			master.setShape(at2.createTransformedShape(beforeShape));
//			
//			for (Point2D.Float point : beforePoint) {
//				Point2D.Float cpoint;
//				cpoint = transformPoint(at2, point);
//				point.setLocation(cpoint.x, cpoint.y);
//			}
//			master.setPoints(beforePoint);
//			
			dragStart = normalDragStart;
		}
			
			
			
			
		
//		if (resizeON) {
//			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));
//
//			AffineTransform at = new AffineTransform();
//			Point2D resizeFactor = this.computeResizeFactor(this.dragStart, nowPoint);
//			Shape s = anchors.get(7-n);
//			Rectangle sr = s.getBounds();
//			
//			at.setToTranslation(sr.getCenterX(), sr.getCenterY());// 축의 중심으로 만든다.
//			at.scale(resizeFactor.getX(), resizeFactor.getY());
//			at.translate(-sr.getCenterX(), -sr.getCenterY());
//			
//			master.setShape(at.createTransformedShape(master.getShape()));
//			dragStart = nowPoint;
//
//			for (Point2D.Float point : master.getPoints()) {
//				Point2D.Float cpoint;
//				cpoint = transformPoint(at, point);
//				point.setLocation(cpoint.x, cpoint.y);
//			}
//		}
	}
	
	
	private Vector<Point2D.Float> getBeforeRotatePoints() {
		Vector<Point2D.Float> pointBeforeRotate =  new Vector<Point2D.Float>();
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), rect.getCenterX(), rect.getCenterY());
		for(Point2D.Float p : master.getPoints()) {pointBeforeRotate.add(transformPoint(at,p));}
		return pointBeforeRotate;
	}
	
	private Rectangle2D getBeforeRotateAnchorBorder() {
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), rect.getCenterX(), rect.getCenterY());
		return at.createTransformedShape(anchors.get(7-n).getBounds()).getBounds();
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		Vector<Point2D.Float> pointBeforeRotate =  new Vector<Point2D.Float>();
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), rect.getCenterX(), rect.getCenterY());
		for(Point2D.Float p : master.getPoints()) {pointBeforeRotate.add(transformPoint(at,p));}
		return master.getAShape().newShape(pointBeforeRotate).getBounds();
	}
	
	private Point2D.Float getBeforeRotatePoint(Point2D.Float point) {
		Rectangle2D rect = master.getShape().getBounds2D();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), rect.getCenterX(), rect.getCenterY());
		return transformPoint(at, point);
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		for(Shape s : anchors) {master.removeFunctionShape(s);}
		anchors.clear();
		if (master.isSelected()) {
			Rectangle2D masterBorder = getBeforeRotateBorder();
			g.setColor(anchorColor);
			
			float factor = master.getBorderThick();
			Shape anchor;
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			for(int h=0; h<3; h++) {
				for(int w=0; w<3; w++) {
					anchor = at.createTransformedShape(new Ellipse2D.Double(masterBorder.getX() + masterBorder.getWidth()/2*w - anchorSize / 2 - factor/2*(1-w), 
							masterBorder.getY() + masterBorder.getHeight()/2*h - anchorSize / 2 - factor/2*(1-h), anchorSize, anchorSize));
					anchors.add(anchor);
				}
			}
			anchors.remove(4);
			for(Shape s : anchors) {
				g.fill(s);
				master.addFunctionShape(s);
			}
		}
	}

	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1) {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);} catch (Exception e) {e.printStackTrace();}
		return p2;
	}

	public Point2D computeResizeFactor(Point2D previous, Point2D current) {
		double px = previous.getX();
		double py = previous.getY();
		double cx = current.getX();
		double cy = current.getY();
//		double width = this.master.getShape().getBounds().getWidth();
		double width = getBeforeRotateBorder().getWidth();
//		double height = this.master.getShape().getBounds().getHeight();
		double height = getBeforeRotateBorder().getHeight();
		double deltaW = 0;
		double deltaH = 0;
		
		switch (n) {
		case 0: deltaW =-(cx-px); deltaH=-(cy-py); break;//0
		case 1: deltaW = 0; deltaH=-(cy-py); break;//1
		case 2: deltaW = cx-px; deltaH=-(cy-py); break;//2
		case 3: deltaW =-(cx-px); deltaH= 0; break;//3
		case 4: deltaW = cx-px; deltaH= 0; break;//4
		case 5: deltaW =-(cx-px); deltaH= cy-py; break;//5
		case 6: deltaW = 0; deltaH= cy-py; break;//6
		case 7: deltaW = cx-px; deltaH= cy-py; break;//7
		default: break;
		}

		double xFactor = deltaW / width + 1.0;
		double yFactor = deltaH / height + 1.0;

		float limit = 10/DrawingPanelMoveAndZoom.getScale();
		if(width + deltaW < limit&&height + deltaH < limit) {
			n= 7-n;
			return new Point2D.Double(-1.1, -1.1);
		}else if(width + deltaW < limit) {
			switch (n) {
			case 0:  n=2; break;//0
			case 2:  n=0; break;//2
			case 3:  n=4; break;//3
			case 4:  n=3; break;//4
			case 5:  n=7; break;//5
			case 7:  n=5; break;//7
			default: break;
			}
			return new Point2D.Double(-1.1, 1);
		}else if(height + deltaH < limit) {
			switch (n) {
			case 0:  n=5; break;//0
			case 2:  n=7; break;//2
			case 1:  n=6; break;//3
			case 6:  n=1; break;//4
			case 5:  n=0; break;//5
			case 7:  n=2; break;//7
			default: break;
			}
			return new Point2D.Double(1, -1.1);
		}
		
		if(xFactor<0) {xFactor*=-1;}
		if(yFactor<0) {yFactor*=-1;}
		return new Point2D.Double(xFactor, yFactor);
	}
	
	public void mouseReleased(MouseEvent e) {resizeON = false;}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
