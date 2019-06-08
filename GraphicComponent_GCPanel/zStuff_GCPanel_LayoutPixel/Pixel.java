package zStuff_GCPanel_LayoutPixel;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

public class Pixel implements Serializable{
	private static final long serialVersionUID = -2741116421558428374L;
	
	Shape rect;
	Point location;
	Item master;
	boolean occupied= false;
	
	public Pixel(int x, int y) {this.location = new Point(x,y);}
	
	public Shape getRect() {return rect;}
	public Point getLocation() {return this.location;}
	public void setRectByXYWH(int x, int y, int w, int h) {rect=new Rectangle(x, y, w, h);}
	public void setRectByShape(Shape r) {rect=r;}
	public void setLocation(int x, int y) {location.move(x, y);}
}
