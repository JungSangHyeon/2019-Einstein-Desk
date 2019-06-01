package fGCDataModify;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import calculation.AffineMath;
import calculation.Calculator;
import canvasMoveAndZoom.DrawingPanelMoveAndZoom;
import onOff.AnchorPaint;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FRotate extends AFunction implements Serializable{
	private static final long serialVersionUID = 3215043540942308860L;
	
	Color insideAnchorColor = Color.WHITE;
	Color anchorColor = new Color(150, 150, 150);
	float anchorSize = 20, anchorDistance =30, lineThick = 2, gap = 2;
	
	Shape anchor;
	Point2D.Float dragStart;
	boolean rotateOn = false, rotated = false;
	
	public FRotate() {this.setPaintOrder(PaintZOrder.TOP);}
	
	public void mousePressed(MouseEvent e) {
		dragStart = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());
		if(anchor!=null&&anchor.contains(dragStart)) {rotateOn = true;}
	}

	public void mouseDragged(MouseEvent e) {
		if(AnchorPaint.isOn()) {AnchorPaint.off();}
		if(rotateOn) {
			rotated = true;
			Point2D.Float nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());//get Angle
			Point2D.Float center = master.getCenter();//모드로 나눌 수 있겠다. 각자의 센터 || 하나의 센터.
			double rotationAngle = Calculator.computeRotationAngle(center, dragStart, nowPoint);
			
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				AffineMath.rotateGC(rotationAngle, gc.getCenter(), gc);
				for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
					AffineMath.rotateGC(rotationAngle, gc.getCenter(), aggreGC);
				}
			}
			dragStart = nowPoint;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		rotateOn = false;
		AnchorPaint.on();
		if(rotated) {
			rotated = false;
			RedoUndo.saveNowInHistory();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()&&anchor!=null&&anchor.contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
			((JPanel) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	public void realPaint(Graphics2D g) {
		if(master.isSelected()) {
			master.removeTopFunctionShape(anchor);//reset
			master.removeFunctionShape(anchor);
			Rectangle2D beforeRotateBorder = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
			
			double angle = master.getAngle();
			if(master.getUpsideDown()) {angle+=180;}
			
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(angle), master.getCenter().x, master.getCenter().y);
			
			float scale = DrawingPanelMoveAndZoom.getScale();
			float scaleLineThick = lineThick/scale, scaleAnchorDistance = anchorDistance/scale, scaleAnchorSize = anchorSize/scale, scaleGap = gap/scale;
			Rectangle2D.Double beforeRotateBar = new Rectangle2D.Double(beforeRotateBorder.getCenterX()-scaleLineThick/2, beforeRotateBorder.getY()-scaleAnchorDistance-master.getBorderThick()/2, scaleLineThick, scaleAnchorDistance);
			Ellipse2D.Double beforeRotateAnchor = new Ellipse2D.Double(beforeRotateBorder.getCenterX()-scaleAnchorSize/2, beforeRotateBorder.getY()-scaleAnchorSize-scaleAnchorDistance-master.getBorderThick()/2,scaleAnchorSize,scaleAnchorSize);
			Rectangle2D rect = beforeRotateAnchor.getBounds2D();
			Ellipse2D.Double beforeRotateInsideAnchor = new Ellipse2D.Double(rect.getX()+scaleGap, rect.getY()+scaleGap, rect.getWidth()-scaleGap*2, rect.getHeight()-scaleGap*2);
			
			anchor = at.createTransformedShape(beforeRotateAnchor);
			master.addTopFunctionShape(anchor);
			master.addFunctionShape(anchor);
			
			if(AnchorPaint.isOn()) {
				g.setColor(anchorColor);
				g.setStroke(new BasicStroke(lineThick/DrawingPanelMoveAndZoom.getScale(), BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
				g.fill(at.createTransformedShape(beforeRotateBar));//rotate Bar
				g.fill(anchor);
				g.setColor(insideAnchorColor);
				g.fill(at.createTransformedShape(beforeRotateInsideAnchor));
			}
		}
	}
}
