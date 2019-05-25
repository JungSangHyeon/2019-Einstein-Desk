package data;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public class PixelTest implements Serializable{
	private static final long serialVersionUID = -2741116421558428374L;
	
	Rectangle rect;
	Point location;
	ItemTest master;
	boolean occupied= false;
	
	public PixelTest(int x, int y) {
		this.location = new Point(x,y);
	}
	
	public ItemTest getMaster() {return master;}
	public void setMaster(ItemTest i ) {master=i;}
	public Point getLocation() {return this.location;}
	public boolean isOccupied() {return this.occupied;}
	public void setOccupied(boolean boo) {this.occupied=boo;}
	public void setRect(int x, int y, int w, int h) {rect=new Rectangle(x, y, w, h);}
	public Rectangle getRect() {return rect;}
	
}
