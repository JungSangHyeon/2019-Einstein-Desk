package fGCDataModify;

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

import component_Stuff.GraphicComponent;
import data.GCStorage_Selected;
import moveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;
import zStuff_Function.AFunction;

public class FResize extends AFunction implements Serializable {//��� ���߾��. ȣ��.
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
	Vector<Shape> changeAnchors = new Vector<Shape>();
	
	public FResize() {this.setPaintOrder(PaintZOrder.TOP);}
	
	public void mousePressed(MouseEvent e) {
		dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));
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
		if(AnchorPaint.isOn()) {
			AnchorPaint.off();
		}
		if (resizeON) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//�巡�� ������.
			Point2D.Float normalDragStart = new Point2D.Float(nowPoint.x, nowPoint.y);//���� �巡�׸� ����.
			Point2D resizeFactor = this.computeResizeFactor(getBeforeRotatePoint(master, dragStart), getBeforeRotatePoint(master, nowPoint));//���� ������ �������� ���� ����.
			
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				int thick = gc.getBorderThick();
				gc.setborderThick(0);
				if(resizeFactor.getY()<0) {gc.reverseUpsideDown();}
				Point2D.Float beforeCenter = new Point2D.Float(gc.getCenter().x, gc.getCenter().y);//���� �߽�����.
				
				AffineTransform at;
				if(resizeFactor.getX()<0||resizeFactor.getY()<0) {
					Rectangle2D sr = getBeforeRotateAnchorBorder(gc, 7-n);//��Ŀ�� ������ ������ ��
					at = new AffineTransform();
					at.setToTranslation(sr.getCenterX(), sr.getCenterY());
					at.scale(resizeFactor.getX(), resizeFactor.getY());
					at.translate(-sr.getCenterX(), -sr.getCenterY());//���� �Ǵ� AT�ϼ�?
				}else {
					Rectangle2D sr = getBeforeRotateAnchorBorder(gc, n);//��Ŀ�� ������ ������ ��
					at = new AffineTransform();
					at.setToTranslation(sr.getCenterX(), sr.getCenterY());
					at.scale(resizeFactor.getX(), resizeFactor.getY());
					at.translate(-sr.getCenterX(), -sr.getCenterY());//���� �Ǵ� AT�ϼ�?
				}
				
				Point2D.Float changeCenter = new Point2D.Float(gc.getCenter().x, gc.getCenter().y);//���� �߽�����.
				changeCenter = transformPoint(at, changeCenter);//�߽� �̵���Ŵ.
				
				Vector<Point2D.Float> beforePoint = getBeforeRotatePoints(gc);//ȸ�� ���� ����Ʈ��
				for (Point2D.Float point : beforePoint) {
					Point2D.Float cpoint = transformPoint(at, point);//�̵� ��Ŵ
					point.setLocation(cpoint.x, cpoint.y);
				}
				Shape beforeShape = at.createTransformedShape(getBeforeRotateShape(gc));//�̵���Ų�ɷ� ���� ����
				
				gc.setPoints(beforePoint);//
				gc.setShape(beforeShape);
				
				AffineTransform at2 = new AffineTransform();
				at2.setToRotation(Math.toRadians(gc.getAngle()), beforeCenter.getX(), beforeCenter.getY());//�̵� ���� �߽����� ȸ�� at����
				gc.setShape(at2.createTransformedShape(gc.getShape()));//�̵��� ����? ȸ����Ŵ
				changeCenter = transformPoint(at2, changeCenter);//�߽� ȸ����Ŵ
				gc.setMyCenter(changeCenter);
				
				for (Point2D.Float point : gc.getPoints()) {//���� ȸ����Ŵ
					Point2D.Float cpoint = transformPoint(at2, point);
					point.setLocation(cpoint.x, cpoint.y);
				}
				gc.setborderThick(thick);
				
				for(GraphicComponent aggreGc : gc.getAllAggregateGCs()) {//TODO ooooo
					aggreGc.setOtherCenter(beforeCenter);
					aggreGc.useOtherCenter();
					
					int aggrethick = aggreGc.getBorderThick();
					aggreGc.setborderThick(0);
					if(resizeFactor.getY()<0) {aggreGc.reverseUpsideDown();}
					Point2D.Double aggrebeforeCenter = new Point2D.Double(aggreGc.getCenter().x, aggreGc.getCenter().y);//���� �߽�����.
					Point2D.Float aggrechangeCenter = new Point2D.Float(aggreGc.getCenter().x, aggreGc.getCenter().y);//���� �߽�����.
					aggrechangeCenter = transformPoint(at, aggrechangeCenter);//�߽� �̵���Ŵ.
					
					Vector<Point2D.Float> aggrebeforePoint = getBeforeRotatePoints(aggreGc);//ȸ�� ���� ����Ʈ��
					for (Point2D.Float point : aggrebeforePoint) {
						Point2D.Float cpoint = transformPoint(at, point);//�̵� ��Ŵ
						point.setLocation(cpoint.x, cpoint.y);
					}
					Shape aggrebeforeShape = at.createTransformedShape(getBeforeRotateShape(aggreGc));//�̵���Ų�ɷ� ���� ����
					
					aggreGc.setPoints(aggrebeforePoint);//
					aggreGc.setShape(aggrebeforeShape);
					
					aggreGc.useMyCenter();
					AffineTransform aggreat2 = new AffineTransform();
					aggreat2.setToRotation(Math.toRadians(aggreGc.getAngle()), aggrebeforeCenter.getX(), aggrebeforeCenter.getY());//�̵� ���� �߽����� ȸ�� at����
					aggreGc.setShape(aggreat2.createTransformedShape(aggreGc.getShape()));//�̵��� ����? ȸ����Ŵ
					
					aggrechangeCenter = transformPoint(aggreat2, aggreGc.getCenter());//�߽� ȸ����Ŵ
					
					Point2D.Float newCenter = new Point2D.Float((float)aggreGc.getShape().getBounds().getCenterX(), (float)aggreGc.getShape().getBounds().getCenterY());
					aggreGc.setMyCenter(newCenter);
					aggreGc.useMyCenter();
					
					for (Point2D.Float point : aggreGc.getPoints()) {//���� ȸ����Ŵ
						Point2D.Float cpoint = transformPoint(aggreat2, point);
						point.setLocation(cpoint.x, cpoint.y);
					}
					aggreGc.setborderThick(aggrethick);
				}
			}
			
			
			
			
			dragStart = normalDragStart;
		}
		
	}
	
	private Shape getBeforeRotateShape(GraphicComponent gc) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		return at.createTransformedShape(gc.getShape());
	}

	private Vector<Point2D.Float> getBeforeRotatePoints(GraphicComponent gc) {
		Vector<Point2D.Float> pointBeforeRotate =  new Vector<Point2D.Float>();
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		for(Point2D.Float p : gc.getPoints()) {pointBeforeRotate.add(transformPoint(at,p));}
		return pointBeforeRotate;
	}
	
	private Rectangle2D getBeforeRotateAnchorBorder(GraphicComponent gc, int i) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		return at.createTransformedShape(makeAnchorForOther(gc, 7-i)).getBounds2D();
	}
	private Shape makeAnchorForOther(GraphicComponent gc, int n) {
		Vector<Shape> returnAnchors = new Vector<Shape>();
		
		Shape beforeRotateAnchor;
		float factor = gc.getBorderThick();
		Rectangle2D masterBorder = getBeforeRotateBorder(gc);//���� �׷� ������ ���� �ּ���.
		
		float scaleAnchorSize = realAnchorSize /DrawingPanelMoveAndZoom.getScale();
		
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		
		double startX = masterBorder.getX() - scaleAnchorSize / 2;
		double startY = masterBorder.getY() - scaleAnchorSize / 2;
		
		for(int h=0; h<3; h++) {
			for(int w=0; w<3; w++) {
				beforeRotateAnchor = new Ellipse2D.Double(
						startX + masterBorder.getWidth()/2*w  - factor/2*(1-w), 
						startY + masterBorder.getHeight()/2*h - factor/2*(1-h), 
						scaleAnchorSize, scaleAnchorSize);
				returnAnchors.add(at.createTransformedShape(beforeRotateAnchor));
			}
		}
		returnAnchors.remove(4);//������� �Ⱦ�����
		
		return returnAnchors.get(n);
	}
	
	private Rectangle2D getBeforeRotateBorder(GraphicComponent gc) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		return at.createTransformedShape(gc.getShape()).getBounds2D();
	}
	
	private Point2D.Float getBeforeRotatePoint(GraphicComponent gc, Point2D.Float point) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(-Math.toRadians(gc.getAngle()), gc.getCenter().x, gc.getCenter().y);
		return transformPoint(at, point);
	}
	
	public void realPaint(Graphics2D g) {
		if (master.isSelected()) {
			for(Shape s : anchors) {
				master.removeTopFunctionShape(s);
				master.removeFunctionShape(s);
			}
			anchors.clear();
			g.setColor(anchorColor);
			
			Rectangle2D masterBorder = getBeforeRotateBorder(master);
			
			float factor = master.getBorderThick();
			Shape anchor;
			Shape beforeRotateAnchor;
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
			
			float scaleAnchorSize = realAnchorSize/DrawingPanelMoveAndZoom.getScale();
			float scaleGap = gap/DrawingPanelMoveAndZoom.getScale();
			float scaleShowGap = showAnchorGap/DrawingPanelMoveAndZoom.getScale();
			
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
			anchors.remove(4);//������� �Ⱦ�����
			beforeanchors.remove(4);//������� �Ⱦ�����
			
			for(Shape s : anchors) {
				master.addTopFunctionShape(s);
				master.addFunctionShape(s);
			}
			
			changeAnchors.clear();
			changeAnchors.add(anchors.get(0));//HAHAHA
			changeAnchors.add(anchors.get(1));
			changeAnchors.add(anchors.get(2));
			changeAnchors.add(anchors.get(4));
			changeAnchors.add(anchors.get(7));
			changeAnchors.add(anchors.get(6));
			changeAnchors.add(anchors.get(5));
			changeAnchors.add(anchors.get(3));
			
			for(Shape s : beforeanchors) {
				Rectangle2D rect = s.getBounds2D();
				Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleShowGap, rect.getY()+scaleShowGap, rect.getWidth()-scaleShowGap*2, rect.getHeight()-scaleShowGap*2);
				if(AnchorPaint.isOn()){g.fill(at.createTransformedShape(beforeRotateInsideAnchor));}
			}
			
			g.setColor(insideAnchorColor);
			for(Shape s : beforeanchors) {
				Rectangle2D rect = s.getBounds2D();
				Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleGap, rect.getY()+scaleGap, rect.getWidth()-scaleGap*2, rect.getHeight()-scaleGap*2);
				if(AnchorPaint.isOn()){g.fill(at.createTransformedShape(beforeRotateInsideAnchor));}
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
		double width = getBeforeRotateBorder(master).getWidth();
		double height = getBeforeRotateBorder(master).getHeight();
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
		double factor = 1.01;
		
		float limit = 1/DrawingPanelMoveAndZoom.getScale();
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
			return new Point2D.Double(1,-factor);
		}
		
		if(xFactor<0) {xFactor*=-1;}
		if(yFactor<0) {yFactor*=-1;}
		return new Point2D.Double(xFactor, yFactor);
	}
	
	public void mouseReleased(MouseEvent e) {
		AnchorPaint.on();
		resizeON = false;
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()) {
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
					
					switch ((changeAnchors.indexOf(a)+nowAngleFactor)%8) {
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
					break;
				}
			}
		}
	}
}