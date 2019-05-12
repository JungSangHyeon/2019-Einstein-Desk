package container_Stuff;

import java.awt.Point;
import java.awt.Rectangle;

public class Pixel {

	Rectangle rect;
	Point location;
	Item master;
	boolean occupied= false;
	
	public Pixel(int x, int y) {
		this.location = new Point(x,y);
	}
	
	public Item getMaster() {return master;}
	public void setMaster(Item i ) {master=i;}
	public Point getLocation() {return this.location;}
	public boolean isOccupied() {return this.occupied;}
	public void setOccupied(boolean boo) {this.occupied=boo;}
	public void setRect(int x, int y, int w, int h) {rect=new Rectangle(x, y, w, h);}
	public Rectangle getRect() {return rect;}
	
}
