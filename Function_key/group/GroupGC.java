package group;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import function_Shape.Shape_Mover;
import function_Shape.Shape_Resizer;
import function_Shape.Shape_Rotator;
import shape_Stuff.eShape;
import time_Stuff.FTimeFunction;

public class GroupGC extends GraphicComponent{
	private static final long serialVersionUID = 4167132028348324648L;
	
	
	public GroupGC(Vector<GraphicComponent> groupMembers) {//클릭시 내부 편집 가능하게 하기.
		//remove member from gcnormal or selected
		this.addAllAggregateGC(groupMembers);
		
		this.setAShape(eShape.group.getAShape());
		this.setFillPaint(false);
		this.setBorderPaint(false);
		this.addFunction(new Shape_Mover());
		this.addFunction(new Shape_Rotator());
		this.addFunction(new Shape_Resizer());
		
		//size
		Path2D path = new Path2D.Float();
		for (GraphicComponent gc : this.getAllAggregateGCs()) {
			path.append(gc.getShape(), false);
		}
		Area compound = new Area(path);
		Rectangle2D bound = compound.getBounds2D();
		Point2D.Float lup = new Point2D.Float((float)bound.getX(), (float)bound.getY());
		Point2D.Float rdp = new Point2D.Float((float)bound.getMaxX(), (float)bound.getMaxY());
		this.addPoint(lup);
		this.addPoint(rdp);
		this.setShape(bound);
		
		//center
		this.setMyCenter(new Point2D.Float((float)bound.getCenterX(), (float)bound.getCenterY()));
	}
	
}
