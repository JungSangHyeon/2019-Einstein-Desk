package PDR_2P_Shape;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import zStuff_Shape.APDRShape;

public class RectShape extends APDRShape{
	private static final long serialVersionUID = -7771762657161778589L;

	public Shape newTwoPointShape(Point2D.Float p1, Point2D.Float p2) {
		return new Rectangle2D.Float(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
}
