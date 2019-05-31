package button_Stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import shape_Stuff.AShape;
import zFunction_Stuff.AFunction;

public class FAShapeButton extends AFunction implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape aShape;
	Color fillColor = new Color(68,114,196);
	int normalBorder = 10, mouseOnBorder = 8, border = normalBorder;
	
	public FAShapeButton(AShape shape) {this.aShape=shape;}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {border = mouseOnBorder;}
		else {border = normalBorder;}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {actionPerformed();}
	}
	public void actionPerformed() {}//Override �Ͽ� ���

	@Override
	public void realPaint(Graphics2D g) {
		Rectangle rect = master.getShape().getBounds();
		Vector<Point2D.Float> points = new Vector<Point2D.Float>();
		points.add(new Point2D.Float((float)rect.getX() + border, (float)rect.getY() + border));
		points.add(new Point2D.Float((float)rect.getX() + (float)rect.getWidth() - border, (float)rect.getY() +(float)rect.getHeight()- border));
		
		g.setColor(fillColor);
		g.fill(aShape.newShape(points));
		g.draw(aShape.newShape(points));
	}
}