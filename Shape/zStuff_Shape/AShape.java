package zStuff_Shape;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import function_Stuff.ATool;
import zStuff_GraphicComponent.GraphicComponent;

public abstract class AShape implements Serializable {
	private static final long serialVersionUID = -7570621592733255823L;
	
	public abstract Shape newShape(Vector<Point2D.Float> points);
	public abstract ATool getDrawer();
	public abstract boolean thisGCIsSelected(GraphicComponent gc, Point2D.Float point);
	
}
