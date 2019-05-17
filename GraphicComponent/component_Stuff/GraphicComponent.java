package component_Stuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import function_Stuff.AFunction;
import global.InjectEnums.eColor;
import global.InjectEnums.eInt;
import shape_Stuff.AShape;

public class GraphicComponent  implements Serializable{
	private static final long serialVersionUID = 2228665649817385320L;
	
	private Shape shape;
	private Vector<Shape> aggreShape;
	private AShape ashape;
	private Vector<AFunction> functions;
	private Vector <Point2D.Float> points;
	private Color fillColor = eColor.ShapeBasicFillColor.getVal(), borderColor = eColor.ShapeBasicBorderColor.getVal();
	private int borderThick = eInt.ShapeBasicBorderThick.getVal(), strokeCap = BasicStroke.CAP_ROUND, strokeJoin = BasicStroke.JOIN_ROUND;
	private boolean paintFill = true, paintBorder = true;
	boolean selected = false;
	
	public GraphicComponent() {
		points = new Vector <Point2D.Float>();
		functions = new Vector <AFunction>();
		aggreShape = new Vector <Shape>();
	}
	
	//paint
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(borderThick, strokeCap, strokeJoin));
		if(paintFill) {g.setColor(fillColor); g.fill(shape);}
		if(paintBorder) {g.setColor(borderColor); g.draw(shape);}
		for(AFunction lump : functions) {lump.paintComponent(g,shape);}
		
		
		
		
		
		//아래는 테스트용
		if(points.size()>0) {
		g.setColor(Color.RED);//디버깅?
		GeneralPath p = new GeneralPath();
		p.moveTo(points.get(0).x, points.get(0).y);
		for(Point2D.Float pp : points) {
			p.lineTo(pp.x, pp.y);
		}
		g.draw(p);
		}
		
	
		AffineTransform at = new AffineTransform();
		at.scale(-1, 1);
		at.translate(-shape.getBounds().getX(),0);
		at.translate(-shape.getBounds().getX(),0);
		if(mirrored) {
			at.translate(-shape.getBounds().getWidth(),0);
			at.translate(-shape.getBounds().getWidth(),0);
			}
		Vector <Point2D.Float> nPoints = new Vector <Point2D.Float>();
		for(Point2D.Float p : points) {
			nPoints.add(transformPoint(at, p));
		}
		
		if (points.size() > 0) {
			g.setColor(Color.CYAN);
			GeneralPath p = new GeneralPath();
			p.moveTo(nPoints.get(0).x, nPoints.get(0).y);
			for (Point2D.Float pp : nPoints) {
				p.lineTo(pp.x, pp.y);
			}
			g.draw(p);
		}
		
		for(Shape s : aggreShape) {
			g.fill(s);
		}
	}
	boolean mirrored =false;
	public void doMirror() {
		//mirror
				AffineTransform at = new AffineTransform();
				at.scale(-1, 1);
				at.translate(-shape.getBounds().getX(),0);
				at.translate(-shape.getBounds().getX(),0);
				if(mirrored) {
				at.translate(-shape.getBounds().getWidth(),0);
				at.translate(-shape.getBounds().getWidth(),0);
				}
				Vector <Point2D.Float> nPoints = new Vector <Point2D.Float>();
				for(Point2D.Float p : points) {
					nPoints.add(transformPoint(at, p));
				}
				this.setPoints(nPoints);
//				this.setShape(this.ashape.newShape(points));
				mirrored = (!mirrored);
	}
	
	
	
	
	
	
	
	public Point2D.Float transformPoint(AffineTransform at, Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	
	//Process
	public void processEvent(MouseEvent e) {
		for(AFunction lump : functions) {lump.processEvent(e);}
	}
	
	//Function
	public Vector<AFunction> getFunctions() {return functions;}
	public void addFunction(AFunction f) {f.setMaster(this);functions.add(f);}
		
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
	
	public void addAggreShape(Shape s) {this.aggreShape.add(s);}
	public void removeAggreShape(Shape s) {this.aggreShape.remove(s);}
	public Vector<Shape> getAggreShape() {return this.aggreShape;}
	
}
