package container_Stuff;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import component_Stuff.GraphicComponent;

public class Item  implements Serializable{
	private static final long serialVersionUID = 6702642196441912004L;
	
	GraphicComponent gc;
	int x, y, w, h;//pixel

	public Item(GraphicComponent gc) {this.gc=gc;}
	
	public void paint(Graphics2D g2) {
		gc.paint(g2);
	}
	public void processEvent(MouseEvent e) {
		gc.processEvent(e);
	}
	
	public GraphicComponent getGraphicComponent() {return gc;}
	public void setRect(Rectangle rect) {gc.setShape(rect);}
	public Shape getRect() {return gc.getShape();}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getH() {return h;}
	
	public void setPoint(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void setSize(int w, int h) {
		this.w=w;
		this.h=h;
	}

	public void loadShape() {
		gc.setShape(gc.getAShape().newShape(gc.getPoints()));
	}
}
