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
import shape_Stuff.AShape;
import zFunction_Stuff.AFunction;

public class GraphicComponent  implements Serializable{
	private static final long serialVersionUID = 2228665649817385320L;
	
	private Shape shape;
	private Vector<Shape> functionShape, topFunctionShape;
	private AShape ashape;
	private Vector<AFunction> functions;
	private Vector <Point2D.Float> points;
	private Color fillColor = eColor.ShapeBasicFillColor.getVal(), borderColor = eColor.ShapeBasicBorderColor.getVal();
	private int borderThick = eInt.ShapeBasicBorderThick.getVal(), strokeCap = BasicStroke.CAP_ROUND, strokeJoin = BasicStroke.JOIN_ROUND;
	private boolean paintFill = true, paintBorder = true;
	boolean selected = false;
	
	private Point2D.Float center = new Point2D.Float();//죽이고픈것 1. //모르겠음. 로테이트를 바꿔야 하긴 함.
	public Point2D.Float getCenter() {return center;}
	public void setCenter(Point2D.Float p) {center=p;}
	
	boolean upsideDown = false;//죽이고픈것 2 //펑션간 통신으로 죽일 수 있겠음.
	public void reverseUpsideDown() {upsideDown = (!upsideDown);}
	public boolean getUpsideDown() {return upsideDown;}
	
	private double angle =0;
	public void addAngle(double da) {angle+=da; angle=angle%360;}
	public double getAngle() {return angle;}

	public GraphicComponent() {
		points = new Vector <Point2D.Float>();
		functions = new Vector <AFunction>();
		functionShape = new Vector <Shape>();
		topFunctionShape = new Vector <Shape>();
	}
	
	//paint
	public void topPaint(Graphics2D g) {
		for(AFunction function : functions) {if(function.isTopPaint()) {function.paintComponent(g,shape);}}
	}
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(borderThick, strokeCap, strokeJoin));
		if(paintFill) {g.setColor(fillColor); g.fill(shape);}
		if(paintBorder) {g.setColor(borderColor); g.draw(shape);}
		for(AFunction function : functions) {if(!function.isTopPaint()) {function.paintComponent(g,shape);}}
//		아래는 테스트용
//		if (points.size() > 0) {
//			g.setColor(Color.RED);// 디버깅?
//			GeneralPath p = new GeneralPath();
//			p.moveTo(points.get(0).x, points.get(0).y);
//			for (Point2D.Float pp : points) {
//				p.lineTo(pp.x, pp.y);
//			}
//			g.draw(p);
//		}
		
	}
	
	
	//Process
//	public void processTopEvent(MouseEvent e) {
//		for(AFunction lump : functions) {if(lump.isTopAction()) {lump.processEvent(e);}}
//	}
	
	public void processEvent(MouseEvent e) {
		for(AFunction lump : functions) {lump.processEvent(e);}
//		for(AFunction lump : functions) {if(!lump.isTopAction()) {lump.processEvent(e);}}
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
	
	//Shape
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	public AShape getAShape() {return ashape;}
	public void setAShape(AShape ashape) {this.ashape = ashape;}
	
	//Points
	public Vector<Point2D.Float> getPoints() {return this.points;}
	public void setPoint(int i, Point2D.Float p) {this.points.set(i, p);}
	public void addPoint(Point2D.Float point) {this.points.add(point);}
	public void setPoints(Vector<Point2D.Float> points) {this.points = points;}
	public void setLastPoint(Point2D.Float p) {this.points.set(points.size()-1, p);}

	//Paint Info
	public void setStrokeCap(int i) {strokeCap =i;}
	public void setStrokeJoin(int i) {strokeJoin =i;}
	public void setborderThick(int i) {borderThick =i;}
	public void setFillColor(Color c) {this.fillColor = c;}
	public void setBorderColor(Color c) {this.borderColor = c;}
	public void setFillPaint(boolean boo) {this.paintFill = boo;}
	public void setBorderPaint(boolean boo) {this.paintBorder = boo;}
	
	public int getBorderThick() {return borderThick;}

	//Selected
	public void setSelected(boolean boo) {this.selected = boo;}
	public boolean isSelected() {return this.selected;}
	
	//Function Shape
	public void addFunctionShape(Shape s) {this.functionShape.add(s);}
	public void removeFunctionShape(Shape s) {this.functionShape.remove(s);}
	public Vector<Shape> getFunctionShape() {return this.functionShape;}
	
	public void addTopFunctionShape(Shape s) {this.topFunctionShape.add(s);}
	public void removeTopFunctionShape(Shape s) {this.topFunctionShape.remove(s);}
	public Vector<Shape> getTopFunctionShape() {return this.topFunctionShape;}
	
	public boolean isTopSelected(Point2D.Float point) {
		for(Shape s : topFunctionShape) {
			if(s.contains(point)) {
				return true;
			}
		}
		return false;
	}
	
	
}
