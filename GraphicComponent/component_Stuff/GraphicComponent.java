package component_Stuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import global.InjectEnums.eColor;
import global.InjectEnums.eInt;
import painter_Stuff.AComponentPainter;
import processor_Stuff.AMouseActionProcessor;

public class GraphicComponent  implements Serializable{
	private static final long serialVersionUID = 2228665649817385320L;
	
	private Shape shape;
	private Vector <Point2D.Float> points;
	private Vector<AComponentPainter> painters;
	private Vector<AMouseActionProcessor> processors;
	private Color fillColor = eColor.ShapeBasicFillColor.getVal();
	private Color borderColor = eColor.ShapeBasicBorderColor.getVal();
	private int borderThick = eInt.ShapeBasicBorderThick.getVal();
	private boolean basicPaintNeed = true;
	
	public GraphicComponent() {
		processors = new Vector<AMouseActionProcessor>();
		painters = new Vector<AComponentPainter>();
		this.points = new Vector <Point2D.Float>();
	}
	
	public void paint(Graphics2D g) {
		if(basicPaintNeed) {
			g.setStroke(new BasicStroke(borderThick));
			g.setColor(fillColor); g.fill(shape);
			g.setColor(borderColor); g.draw(shape);
		}
		for(AComponentPainter painter : painters) {painter.paintComponent(g,shape);}
	}
	
	public void processEvent(MouseEvent e) {
		for(AMouseActionProcessor processor : processors) {processor.processEvent(e);}
	}
	
	public Vector<AComponentPainter> getPainters() {return painters;}
	public void addPainter(AComponentPainter painter) {painters.add(painter);}
	
	public Vector<AMouseActionProcessor> getProcessors() {return processors;}
	public void addProcessor(AMouseActionProcessor processor) {processor.setMaster(this);processors.add(processor);}
	
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public void setBasicPaintNeed(boolean boo) {this.basicPaintNeed = boo;}
	
	public Vector<Point2D.Float> getPoints() {return this.points;}
	public void setPoints(Vector<Point2D.Float> points) {this.points = points;}
	public void setPoint(int i, Point2D.Float p) {this.points.set(i, p);}
	public void setLastPoint(Point2D.Float p) {this.points.set(points.size()-1, p);}
	public void addPoint(Point2D.Float point) {this.points.add(point);}

	public void setFillColor(Color c) {this.fillColor = c;}
	public void setBorderColor(Color c) {this.borderColor = c;}
	
}
