package fGCDataModify;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import calculation.AffineMath;
import onOff.AnchorPaint;
import redoUndo.RedoUndo;
import zStuff_Function.AFunction;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class FMove extends AFunction implements Serializable{
	private static final long serialVersionUID = 2509847208800494236L;
	
	int rectBorderThick =2;
	Color rectColor = new Color(150, 150, 150);
	
	Point2D.Float dragStart;
	boolean moveOn = false, moved = false;
	
	public FMove() {this.setPaintOrder(PaintZOrder.TOP);}
	
	public void mousePressed(MouseEvent e) {
		dragStart = new Point2D.Float(e.getPoint().x, e.getPoint().y);//진짜 쉐입이 바뀌어서, 이거 안바꿔도 되게좀 해버ㅑㅏ.
		if(master.getAShape().thisGCIsSelected(master, dragStart)) {
			moveOn = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(dragStart)) {moveOn = false;}
			}
		}else {moveOn = false;}
	}

	public void mouseDragged(MouseEvent e) {
		if(AnchorPaint.isOn()) {AnchorPaint.off();}
		if(moveOn) {
			moved = true;
			Point2D.Float nowPoint = new Point2D.Float(e.getPoint().x, e.getPoint().y);
			AffineTransform at = AffineMath.getMoveAffineTransform(dragStart, nowPoint);
			for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {
				AffineMath.applyAffineTransformToGC(at, gc);
				for(GraphicComponent aggreGC : gc.getAllAggregateGCs()) {
					AffineMath.applyAffineTransformToGC(at, aggreGC);
				}
			}
			dragStart = nowPoint;
		}
	}
	
	public void realPaint(Graphics2D g) {
		if(master.isSelected()) {
			Rectangle2D masterBeforeRotateBorder = AffineMath.getRotateShape(master.getShape(), -master.getAngle(), master.getCenter()).getBounds2D();
			float factor = master.getBorderThick();
			Rectangle2D.Double beforeRotateRect = new Rectangle2D.Double(
					masterBeforeRotateBorder.getX()-factor/2,
					masterBeforeRotateBorder.getY()-factor/2,
					masterBeforeRotateBorder.getWidth()+factor,
					masterBeforeRotateBorder.getHeight()+factor
			);
			g.setColor(rectColor);
			g.setStroke(new BasicStroke(rectBorderThick, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
			
			AffineTransform at = new AffineTransform();
			at.setToRotation(Math.toRadians(master.getAngle()), master.getCenter().x, master.getCenter().y);
			if(AnchorPaint.isOn()) {g.draw(at.createTransformedShape(beforeRotateRect));}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		moveOn = false; AnchorPaint.on();
		if(moved) {
			moved = false;
			RedoUndo.saveNowInHistory();
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(master.isSelected()&&master.getShape().contains(new Point2D.Float(e.getPoint().x, e.getPoint().y))) {//mouse On this
			boolean onlyOnShape = true;
			for(Shape s : master.getFunctionShape()) {
				if(s.contains(new Point2D.Float(e.getPoint().x, e.getPoint().y))) {onlyOnShape = false;}
			}
			if(onlyOnShape) {((JPanel) e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));}
		}
	}
	
}
