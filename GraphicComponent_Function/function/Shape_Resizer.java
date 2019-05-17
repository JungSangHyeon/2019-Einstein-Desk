package function;

import java.awt.BasicStroke;
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

public class Shape_Resizer extends AFunction implements Serializable {
	private static final long serialVersionUID = 181931757073293064L;

	Point2D.Float dragStart;
	boolean resizeON = false;
	boolean noChange = false;
	int n = 0;
	
	public void mousePressed(MouseEvent e) {
		if (GCStorage.have(master)) {
			dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));
		} // 패널은 이거.
		else {
			dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());
		} // acontainer는 이거.
		
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
		if (resizeON) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));

			AffineTransform at = new AffineTransform();
			Point2D resizeFactor = this.computeResizeFactor(this.dragStart, nowPoint);
			Shape s = anchors.get(7-n);
			Rectangle sr = s.getBounds();
			
			at.setToTranslation(sr.getCenterX(), sr.getCenterY());// 축의 중심으로 만든다.
			at.scale(resizeFactor.getX(), resizeFactor.getY());
			at.translate(-sr.getCenterX(), -sr.getCenterY());
			
			master.setShape(at.createTransformedShape(master.getShape()));
			dragStart = nowPoint;

			for (Point2D.Float point : master.getPoints()) {
				Point2D.Float cpoint;
				cpoint = transformPoint(at, point);
				point.setLocation(cpoint.x, cpoint.y);
			}

		}
	}

	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1) {
		Point2D.Float p2 = new Point2D.Float();
		try {
			at.transform(p1, p2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p2;
	}

	boolean changeGrap = false;

	public Point2D computeResizeFactor(Point2D previous, Point2D current) {
		double px = previous.getX();
		double py = previous.getY();
		double cx = current.getX();
		double cy = current.getY();
		double width = this.master.getShape().getBounds().getWidth();
		double height = this.master.getShape().getBounds().getHeight();
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

		// compute resize
		double xFactor = 1.0;
		double yFactor = 1.0;

		xFactor = deltaW / width + xFactor;
		yFactor = deltaH / height + yFactor;
		
		if(width > 15.0&&height > 15.0) {
			noChange = false;
		}
		
		float limit = 10/DrawingPanelMoveAndZoom.getScale();
//		if(width + deltaW < limit&&height + deltaH < limit) {return new Point2D.Double(1.1, 1.1);}
//		if(width + deltaW < limit) {return new Point2D.Double(1.1, 1);}
//		if(height + deltaH < limit) {return new Point2D.Double(1, 1.1);}
		
		System.out.println(limit);
		if (!noChange) {//빠르면 120.
			if(width + deltaW < limit&&height + deltaH < limit) {
				n= 7-n;
				noChange = true;
				return new Point2D.Double(1.1, 1.1);
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
				noChange = true;
				return new Point2D.Double(1.1, 1);
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
				noChange = true;
				return new Point2D.Double(1, 1.1);
			}
			
		}
		
		if(xFactor<0) {xFactor*=-1;}
		if(yFactor<0) {yFactor*=-1;}
		return new Point2D.Double(xFactor, yFactor);
	}

	
	public void mouseReleased(MouseEvent e) {
		resizeON = false;
		changeGrap = false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	Vector<Shape> anchors = new Vector<Shape>();
	
	public void paintComponent(Graphics2D g, Shape shape) {
		for(Shape s : anchors) {
			master.removeAggreShape(s);
		}
		anchors.clear();
		if (master.isSelected()) {
			Rectangle2D masterBorder = shape.getBounds2D();
			g.setColor(new Color(150, 150, 150));
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			float size = 20;
			
			Shape anchor;
			for(int h=0; h<3; h++) {
				for(int w=0; w<3; w++) {
					anchor = new Ellipse2D.Double(masterBorder.getX() + masterBorder.getWidth()/2*w - size / 2, masterBorder.getY() + masterBorder.getHeight()/2*h - size / 2, size, size);
					anchors.add(anchor);
				}
			}
			anchors.remove(4);
			for(Shape s : anchors) {
				g.fill(s);
				master.addAggreShape(s);
			}
		}
	}

}
