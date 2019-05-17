package function;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import data.GCStorage;
import function_Stuff.AFunction;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class Shape_Resizer_didIt1 extends AFunction implements Serializable{
	private static final long serialVersionUID = 181931757073293064L;
	
	Point2D.Float dragStart;
	Ellipse2D.Double anchor;
	boolean resizeON = false;
	
	public void mousePressed(MouseEvent e) {
		if(GCStorage.have(master)) {dragStart = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));}//패널은 이거.
		else {dragStart = new Point2D.Float(e.getXOnScreen(), e.getYOnScreen());}//acontainer는 이거.
		if(anchor!=null&&anchor.contains(dragStart)) {resizeON = true;}
	}
	
	public void mouseDragged(MouseEvent e) {// 하나로 다같이 하는건 이걸 스태틱으로 만들면 할 수 있겠다.
		if (resizeON) {
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(new Point(e.getX(), e.getY()));
			Rectangle2D rect = master.getShape().getBounds2D();
			
			AffineTransform at = new AffineTransform();
			Point2D resizeFactor = this.computeResizeFactor(this.dragStart, nowPoint);
//			at.setToTranslation(rect.getX(), rect.getY());//축의 중심으로 만든다.
			if(!changeGrap) {
				at.setToTranslation(rect.getX(), rect.getY());//축의 중심으로 만든다.
				at.scale(resizeFactor.getX(), resizeFactor.getY());
				at.translate(-rect.getX(), -rect.getY());
			}else {
				System.out.println(213123);
				at.setToTranslation(rect.getX()+rect.getWidth(), rect.getY());//축의 중심으로 만든다.
				at.scale(-resizeFactor.getX(), resizeFactor.getY());
				at.translate(-rect.getX(), -rect.getY());
			}
			
			master.setShape(at.createTransformedShape(master.getShape()));
			dragStart = nowPoint;

			for (Point2D.Float point : master.getPoints()) {
				Point2D.Float cpoint = transformPoint(at, point);
				point.setLocation(cpoint.x, cpoint.y);
			}
		}
	}

	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
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
		
//		switch (this.selectedShape.getSelectedAnchor()) {
//			case E:  deltaW =  cx-px; 	deltaH=  0; 	 break;
//			case W:  deltaW =-(cx-px);	deltaH=  0; 	 break;
//			case S:  deltaW =  0;		deltaH=  cy-py;  break;
//			case N:  deltaW =  0;		deltaH=-(cy-py); break;
//			case SE: deltaW =  cx-px; 	deltaH=  cy-py;	 break;
//			case NE: deltaW =  cx-px; 	deltaH=-(cy-py); break;
//			case SW: deltaW =-(cx-px);	deltaH=  cy-py;	 break;	
//			case NW: deltaW =-(cx-px);	deltaH=-(cy-py); break;
//			default: break;
//		}
		
		// compute resize 
		double xFactor = 1.0;
		double yFactor = 1.0;
		
		if(!changeGrap) {
			deltaW =(cx-px);	deltaH=  0; 
//			xFactor = deltaW / width + xFactor;
		}else {
			deltaW =-(cx-px);	deltaH=  0; 
//			xFactor = -(deltaW / width + xFactor);
		}
		xFactor = deltaW / width + xFactor;
		
		if (width < 30.0) {
			changeGrap=(!changeGrap);
			return new Point2D.Double(1.1, 1);
		}
		

		
		if (height > 30.0)	{
			yFactor = deltaH / height + yFactor;
		}else {
			changeGrap=(!changeGrap);
		}
		
		return new Point2D.Double(xFactor, yFactor);
	}
	
	
	public void mouseReleased(MouseEvent e) {resizeON = false;}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void paintComponent(Graphics2D g, Shape shape) {
		master.removeAggreShape(anchor);
		if (master.isSelected()) {
			Rectangle2D masterBorder = shape.getBounds2D();
			g.setColor(new Color(150, 150, 150));
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			float size = 20;
			anchor = new Ellipse2D.Double(masterBorder.getX() + masterBorder.getWidth() - size / 2, masterBorder.getY() + masterBorder.getHeight() - size / 2, size, size);
			master.addAggreShape(anchor);
			g.fill(anchor);
		}
	}
	
}
