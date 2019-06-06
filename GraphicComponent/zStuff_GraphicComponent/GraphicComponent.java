package zStuff_GraphicComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import PDR_NP_Shape.HighlightShape;
import PDR_NP_Shape.pen;
import global.InjectEnums.eColor;
import global.InjectEnums.eInt;
import zStuff_Function.AFunction;
import zStuff_Function.AFunction.PaintZOrder;
import zStuff_Shape.AShape;

public class GraphicComponent  implements Serializable{
	private static final long serialVersionUID = 2228665649817385320L;
	
	private Vector<AFunction> functions;
	
	public GraphicComponent() {
		points = new Vector <Point2D.Float>();
		functions = new Vector <AFunction>();
		aggregateGC = new Vector <GraphicComponent>();
		functionEventShape = new Vector <Shape>();
		topFunctionShape = new Vector <Shape>();
	}
	
	//Paint
	private Color fillColor = eColor.ShapeBasicFillColor.getVal(), borderColor = eColor.ShapeBasicBorderColor.getVal();
	private float borderThick = eInt.ShapeBasicBorderThick.getVal();
	private int strokeCap = BasicStroke.CAP_ROUND, strokeJoin = BasicStroke.JOIN_ROUND;
	private boolean paintFill = true, paintBorder = true;
	
	public float getBorderThick() {return borderThick;}
	public void setStrokeCap(int i) {strokeCap =i;}
	public void setStrokeJoin(int i) {strokeJoin =i;}
	public void setborderThick(float i) {borderThick =i;}
	public void setFillColor(Color c) {this.fillColor = c;}
	public void setBorderColor(Color c) {this.borderColor = c;}
	public Color getBorderColor() {return this.borderColor;}
	public void setFillPaint(boolean boo) {this.paintFill = boo;}
	public void setBorderPaint(boolean boo) {this.paintBorder = boo;}
	
	public void topPaint(Graphics2D g) {for(AFunction function : functions) {function.paint(g, PaintZOrder.TOP);}}
	public void bottumPaint(Graphics2D g) {for(AFunction function : functions) {function.paint(g, PaintZOrder.BOTTOM);}}
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(borderThick, strokeCap, strokeJoin));
		if(paintFill) {g.setColor(fillColor); g.fill(shape);}
		if(paintBorder) {g.setColor(borderColor); g.draw(shape);}
		for(AFunction function : functions) {function.paint(g, PaintZOrder.MIDDLE);}
		for(GraphicComponent gc : aggregateGC) {if(!(gc.getAShape() instanceof pen)) {gc.paint(g);}}//shape & etc
		for(GraphicComponent gc : aggregateGC) {if(gc.getAShape() instanceof HighlightShape) {gc.paint(g);}}//highlight
		for(GraphicComponent gc : aggregateGC) {if(!(gc.getAShape() instanceof HighlightShape)&&gc.getAShape() instanceof pen) {gc.paint(g);}}//pen
		
//		아래는 테스트용//TODO
//		if (points.size() > 0) {//points
//			g.setColor(Color.RED);// 디버깅?
//			GeneralPath p = new GeneralPath();
//			p.moveTo(points.get(0).x, points.get(0).y);
//			for (Point2D.Float pp : points) {
//				p.lineTo(pp.x, pp.y);
//			}
//			g.draw(p);
//		}
//		g.setColor(Color.cyan);//border
//		g.draw(shape.getBounds());
//		if(selected) {g.fill(new Ellipse2D.Float(getCenter().x-20, getCenter().y-20, 40,40));}
//		g.setColor(Color.green);//center
//		g.fill(new Ellipse2D.Float(getCenter().x-5, getCenter().y-5, 10,10));
//		for(Shape s : functionEventShape) {g.fill(s);}
	}
	
	//Shape
	private Shape shape;
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	//AShape
	private AShape ashape;
	public AShape getAShape() {return ashape;}
	public void setAShape(AShape ashape) {this.ashape = ashape;}
	
	//Points
	private Vector<Point2D.Float> points;
	public Vector<Point2D.Float> getPoints() {return this.points;}
	public void setPoint(int i, Point2D.Float p) {this.points.set(i, p);}
	public void addPoint(Point2D.Float point) {this.points.add(point);}
	public void setPoints(Vector<Point2D.Float> points) {this.points = points;}
	public void setLastPoint(Point2D.Float p) {this.points.set(points.size()-1, p);}

	//Process Event
	public void processEvent(MouseEvent e) {
		for(AFunction function : functions) {function.processMouseEvent(e);}
	}
	
	//Function
	public Vector<AFunction> getFunctions() {return functions;}
	public void addFunction(AFunction f) {f.setMaster(this);functions.add(f);}
	public void removeFunction(AFunction function) {
		for(AFunction f : functions) {
			if(f.getClass().equals(function.getClass())) {
				functions.remove(f);break;
			}
		}
	}

	//Selected
	boolean selected = false;
	int v =0;
	public void setSelected(boolean boo) {
		this.selected = boo;
		if(!selected) {clearFunctionEventShapes();}//TODO
		for (AFunction f : functions) {f.processSelectEvent(selected);}
	}
	public boolean isSelected() {return this.selected;}
	
	//Function Shape
	private Vector<Shape> functionEventShape;
	public void addFunctionShape(Shape s) {this.functionEventShape.add(s);}
	public void removeFunctionShape(Shape s) {this.functionEventShape.remove(s);}
	public Vector<Shape> getFunctionShape() {return this.functionEventShape;}
	public void clearFunctionEventShapes() {functionEventShape.clear();}
	
	//Top Function Shape
	private Vector<Shape> topFunctionShape;
	public void addTopFunctionShape(Shape s) {this.topFunctionShape.add(s);}
	public void removeTopFunctionShape(Shape s) {this.topFunctionShape.remove(s);}
	public Vector<Shape> getTopFunctionShape() {return this.topFunctionShape;}
	public boolean isTopSelected(Point2D.Float point) {
		for(Shape s : topFunctionShape) {if(s.contains(point)) {return true;}}
		return false;
	}
	
	//Text
	String text = "";
	int textSize = 50;
	public void setTextSize(int size) {this.textSize=size;}
	public int getTextSize() {return this.textSize;}
	public void setText(String text) {this.text=text;}
	public String getText() {return this.text;}

	//Aggregate GC
	private Vector<GraphicComponent> aggregateGC;
	public void addAggregateGC(GraphicComponent gc) {aggregateGC.add(gc);}
	public void addAllAggregateGC(Vector <GraphicComponent> gcs) {aggregateGC.addAll(gcs);}
	public void removeAggregateGC(GraphicComponent gc) {aggregateGC.remove(gc);}
	public int getAggregateGCSize() {return aggregateGC.size();}
	public Vector<GraphicComponent> getMyAggregateGCs() {return aggregateGC;}
	public Vector<GraphicComponent> getAllAggregateGCs() {
		Vector<GraphicComponent> meAndChildAggreCom = new Vector<GraphicComponent>();
		for(GraphicComponent gc : aggregateGC) {
			meAndChildAggreCom.add(gc);
			if(gc.getAggregateGCSize()>0) {
				meAndChildAggreCom.addAll(gc.getAllAggregateGCs());
			}
		}
		return meAndChildAggreCom;
	}
	
	//Event Control
	boolean takeEvent = true;
	public boolean isTakeEvent() {return takeEvent;}
	public void setTakeEvent(boolean takeEvent) {this.takeEvent = takeEvent;}
	
	//Time
	boolean timeMove = false;
	public boolean isTimeMoving() {return timeMove;}
	public void moveTime(boolean boo) {
		timeMove = boo;
		for (AFunction f : functions) {f.timeIsMove(timeMove);}
	}
	
	//Center
	private Point2D.Float center= new Point2D.Float();
	public Point2D.Float getCenter() {return center;}
	public void setCenter(Point2D.Float p) {center=p;}
	
	//Angle
	boolean upsideDown = false;
	public void reverseUpsideDown() {upsideDown = (!upsideDown);}
	public boolean getUpsideDown() {return upsideDown;}
	
	private double angle =0;
	public void addAngle(double da) {angle+=da; angle=angle%360;}
	public double getAngle() {return angle;}
}
