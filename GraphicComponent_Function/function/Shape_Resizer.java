package function;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import data.GCStorage;
import function_Stuff.AFunction;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Resizer extends AFunction implements Serializable {//히야 길다.
	private static final long serialVersionUID = 181931757073293064L;

	Color anchorColor = new Color(150, 150, 150);
	Color insideAnchorColor = Color.WHITE;
	float realAnchorSize = 30;
	float showAnchorGap = 9;
	float gap = 11;
	
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

	public void mouseDragged(MouseEvent e) {//WOW LONGLONG
		if (resizeON) {
			Point2D.Double beforeCenter = new Point2D.Double(master.getCenter().x, master.getCenter().y);//현재 중심저장.
			
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//드래그 시작점.
			Point2D.Float normalDragStart = new Point2D.Float(nowPoint.x, nowPoint.y);//다음 드래그를 위함.
			
			nowPoint = getBeforeRotatePoint(nowPoint);//점을 회전 전으로 바꿈
			dragStart = getBeforeRotatePoint(dragStart);
			Point2D resizeFactor = this.computeResizeFactor(this.dragStart, nowPoint);//돌린 것으로 리사이즈 팩터 만듬.
			
			Rectangle2D sr = getBeforeRotateAnchorBorder();//앵커를 돌리기 전으로 함
			
			AffineTransform at = new AffineTransform();
			at.setToTranslation(sr.getCenterX(), sr.getCenterY());
			at.scale(resizeFactor.getX(), resizeFactor.getY());
			at.translate(-sr.getCenterX(), -sr.getCenterY());//원래 되는 AT완성?
			
			Point2D.Float changeCenter = new Point2D.Float(master.getCenter().x, master.getCenter().y);//현재 중심저장.
			changeCenter = transformPoint(at, changeCenter);//중심 이동시킴.
			
			Vector<Point2D.Float> beforePoint = getBeforeRotatePoints();//회전 전의 포인트들
			for (Point2D.Float point : beforePoint) {
				Point2D.Float cpoint = transformPoint(at, point);//이동 시킴
				point.setLocation(cpoint.x, cpoint.y);
			}
			Shape beforeShape = master.getAShape().newShape(beforePoint);//이동시킨걸로 쉐입 만듬
			
			master.setPoints(beforePoint);//
			master.setShape(beforeShape);
			
			AffineTransform at2 = new AffineTransform();
			at2.setToRotation(Math.toRadians(master.getAngle()), beforeCenter.getX(), beforeCenter.getY());//이동 전의 중심으로 회전 at만듬
			master.setShape(at2.createTransformedShape(master.getShape()));//이동된 도형? 회전시킴
			changeCenter = transformPoint(at2, changeCenter);//중심 회전시킴
			master.setCenter(changeCenter);
			
			for (Point2D.Float point : master.getPoints()) {//점들 회전시킴
				Point2D.Float cpoint = transformPoint(at2, point);
				point.setLocation(cpoint.x, cpoint.y);
			}
				
			dragStart = normalDragStart;
		}
	}
	
	
	private Vector<Point2D.Float> getBeforeRotatePoints() {
		Vector<Point2D.Float> pointBeforeRotate =  new Vector<Point2D.Float>();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		for(Point2D.Float p : master.getPoints()) {pointBeforeRotate.add(transformPoint(at,p));}
		return pointBeforeRotate;
	}
	
	private Rectangle2D getBeforeRotateAnchorBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(anchors.get(7-n).getBounds()).getBounds();
	}
	
	private Rectangle2D getBeforeRotateBorder() {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return at.createTransformedShape(master.getShape()).getBounds2D();
	}
	
	private Point2D.Float getBeforeRotatePoint(Point2D.Float point) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
		return transformPoint(at, point);
	}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		if (master.isSelected()) {
			for(Shape s : anchors) {master.removeFunctionShape(s);}
			anchors.clear();
			Rectangle2D masterBorder = getBeforeRotateBorder();
			g.setColor(anchorColor);
			
			float factor = master.getBorderThick()*2;
			Shape anchor;
			Shape beforeRotateAnchor;
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), masterBorder.getCenterX(), masterBorder.getCenterY());
			
			float scaleAnchorSize = realAnchorSize;
			float scaleGap = gap;
			float scaleShowGap = showAnchorGap;
			if(DrawingPanelMoveAndZoom.getScale()>1) {
				scaleAnchorSize /=DrawingPanelMoveAndZoom.getScale();
				scaleGap /=DrawingPanelMoveAndZoom.getScale();
				scaleShowGap /=DrawingPanelMoveAndZoom.getScale();
			}
			
			double startX = masterBorder.getX() - scaleAnchorSize / 2;
			double startY = masterBorder.getY() - scaleAnchorSize / 2;
			Vector<Shape> beforeanchors = new Vector<Shape>();
			
			for(int h=0; h<3; h++) {
				for(int w=0; w<3; w++) {
					beforeRotateAnchor = new Ellipse2D.Double(
							startX + masterBorder.getWidth()/2*w  - factor/2*(1-w), 
							startY + masterBorder.getHeight()/2*h - factor/2*(1-h), 
							scaleAnchorSize, scaleAnchorSize);
					beforeanchors.add(beforeRotateAnchor);
					anchor = at.createTransformedShape(beforeRotateAnchor);
					anchors.add(anchor);
				}
			}
			anchors.remove(4);//가운데꺼는 안쓸꺼여
			beforeanchors.remove(4);//가운데꺼는 안쓸꺼여
			
			for(Shape s : anchors) {
				master.addFunctionShape(s);
			}
			
			for(Shape s : beforeanchors) {
				Rectangle2D rect = s.getBounds2D();
				Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleShowGap, rect.getY()+scaleShowGap, rect.getWidth()-scaleShowGap*2, rect.getHeight()-scaleShowGap*2);
				g.fill(at.createTransformedShape(beforeRotateInsideAnchor));
			}
			
			g.setColor(insideAnchorColor);
			for(Shape s : beforeanchors) {
				Rectangle2D rect = s.getBounds2D();
				Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleGap, rect.getY()+scaleGap, rect.getWidth()-scaleGap*2, rect.getHeight()-scaleGap*2);
				g.fill(at.createTransformedShape(beforeRotateInsideAnchor));
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
		double width = getBeforeRotateBorder().getWidth();
		double height = getBeforeRotateBorder().getHeight();
		double deltaW = 0;
		double deltaH = 0;
		
		switch (n) {
		case 0: deltaW =-(cx-px); deltaH=-(cy-py); break;
		case 1: deltaW = 0; deltaH=-(cy-py); break;
		case 2: deltaW = cx-px; deltaH=-(cy-py); break;
		case 3: deltaW =-(cx-px); deltaH= 0; break;
		case 4: deltaW = cx-px; deltaH= 0; break;
		case 5: deltaW =-(cx-px); deltaH= cy-py; break;
		case 6: deltaW = 0; deltaH= cy-py; break;
		case 7: deltaW = cx-px; deltaH= cy-py; break;
		default: break;
		}

		double xFactor = deltaW / width + 1.0;
		double yFactor = deltaH / height + 1.0;
		double factor = 1.1;
		
		float limit = 10/DrawingPanelMoveAndZoom.getScale();
		if(width + deltaW < limit&&height + deltaH < limit) {
			n= 7-n;
			return new Point2D.Double(-factor,-factor);
		}else if(width + deltaW < limit) {
			switch (n) {
			case 0:  n=2; break;
			case 2:  n=0; break;
			case 3:  n=4; break;
			case 4:  n=3; break;
			case 5:  n=7; break;
			case 7:  n=5; break;
			default: break;
			}
			return new Point2D.Double(-factor,1);
		}else if(height + deltaH < limit) {
			switch (n) {
			case 0:  n=5; break;
			case 2:  n=7; break;
			case 1:  n=6; break;
			case 6:  n=1; break;
			case 5:  n=0; break;
			case 7:  n=2; break;
			default: break;
			}
			master.reverseUpsideDown();
			return new Point2D.Double(1,-factor);
		}
		
		if(xFactor<0) {xFactor*=-1;}
		if(yFactor<0) {yFactor*=-1;}
		return new Point2D.Double(xFactor, yFactor);
	}
	
	Vector<Shape> changeAnchors = new Vector<Shape>();
	public void mouseReleased(MouseEvent e) {
		resizeON = false;
		changeAnchors.clear();
		changeAnchors.add(anchors.get(0));//HAHAHA
		changeAnchors.add(anchors.get(1));
		changeAnchors.add(anchors.get(2));
		changeAnchors.add(anchors.get(4));
		changeAnchors.add(anchors.get(7));
		changeAnchors.add(anchors.get(6));
		changeAnchors.add(anchors.get(5));
		changeAnchors.add(anchors.get(3));
	}
	public void mouseMoved(MouseEvent e) {
		for(Shape a : changeAnchors) {
			if(a.contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
				int nowAngleFactor;
				int calee = (int)master.getAngle()/22;
				if(calee<1) {nowAngleFactor = 0;}
				else if(calee<3) {nowAngleFactor = 1;}
				else if(calee<5) {nowAngleFactor = 2;}
				else if(calee<7) {nowAngleFactor = 3;}
				else if(calee<9) {nowAngleFactor = 4;}
				else if(calee<11) {nowAngleFactor = 5;}
				else if(calee<13) {nowAngleFactor = 6;}
				else if(calee<15) {nowAngleFactor = 7;}
				else {nowAngleFactor = 0;}
				
				switch ((changeAnchors.indexOf(a)+nowAngleFactor)%8) {//+ now angle num %8
				case 0: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR)); break;
				case 1: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.N_RESIZE_CURSOR)); break;
				case 2: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR)); break;
				case 3: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.E_RESIZE_CURSOR)); break;
				case 4: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR)); break;
				case 5: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.S_RESIZE_CURSOR)); break;
				case 6: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR)); break;
				case 7: ((JPanel) e.getSource()).setCursor(new Cursor(Cursor.W_RESIZE_CURSOR)); break;
				default: break;
				}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
