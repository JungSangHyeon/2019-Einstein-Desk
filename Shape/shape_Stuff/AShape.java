package shape_Stuff;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import tool_Stuff.ATool;

public abstract class AShape implements Serializable {
	private static final long serialVersionUID = -7570621592733255823L;
	
	public abstract Shape newShape(Vector<Point2D.Float> points);
	public abstract ATool getDrawer();
	public abstract boolean isSelected(GraphicComponent gc, Point2D.Float point);
	
}
