package function_Data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import data.ShapeData;
import shape.PolygonShape;
import shape_Stuff.AShape;
import zFunction_Stuff.AFunction;

public class Data_ShapeSelect extends AFunction implements Serializable{
	private static final long serialVersionUID = 787188459680824163L;
	
	AShape aShape;
	
	public Data_ShapeSelect(AShape shape) {this.aShape=shape;}
	public void mouseReleased(MouseEvent e) {ShapeData.setNowShapeMaker(aShape);}
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(master.getShape().contains(e.getPoint())) {
			gap = 8;
		}else {
			gap = 10;
		}
	}
	
	int gap = 10;
	public void paintComponent(Graphics2D g, Shape shape) {
		Rectangle rect = master.getShape().getBounds();
		Vector<Point2D.Float> points = new Vector<Point2D.Float>();
		points.add(new Point2D.Float((float)rect.getX() + gap, (float)rect.getY() + gap));
		points.add(new Point2D.Float((float)rect.getX() + (float)rect.getWidth() - gap, (float)rect.getY() +(float)rect.getHeight()- gap));
		
		g.setColor(new Color(68,114,196));
		g.fill(aShape.newShape(points));
//		g.setColor(new Color(47,82,143));
		g.draw(aShape.newShape(points));
	}
}
