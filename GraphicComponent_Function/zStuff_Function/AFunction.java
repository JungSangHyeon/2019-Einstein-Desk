package zStuff_Function;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

import component_Stuff.GraphicComponent;

public abstract class AFunction  implements Serializable{
	private static final long serialVersionUID = -1736900760929688622L;
	
	Vector<AFunction> aggregateFunction = new Vector<AFunction>();
	public void addAggregateFunction(AFunction function) {aggregateFunction.add(function);}
	
	//Set Master
	protected GraphicComponent master;
	public void setMaster(GraphicComponent graphicComponent) {
		this.master=graphicComponent;
		for(AFunction function : aggregateFunction) {function.setMaster(graphicComponent);}
	}
	
	//Paint
	public void paint(Graphics2D g, PaintZOrder targetOrder){
		Stroke beforeStroke = g.getStroke();
		Color beforeColor = g.getColor();
		Font beforeFont = g.getFont();
		Shape beforeClip = g.getClip();
		
		if(myPaintOrder == targetOrder) {realPaint(g);}
		for(AFunction function : aggregateFunction) {function.paint(g, targetOrder);}
		
		g.setStroke(beforeStroke);
		g.setColor(beforeColor);
		g.setFont(beforeFont);
		g.setClip(beforeClip);
	}
	public void realPaint(Graphics2D g) {}
	
	PaintZOrder myPaintOrder = PaintZOrder.MIDDLE;
	public enum PaintZOrder{BOTTOM, MIDDLE, TOP, NOPAINT}
	public void setPaintOrder(PaintZOrder paintZOrder) {myPaintOrder = paintZOrder;}
	
	
	//Action
	boolean actionOn = true;
	public boolean isActionOn() {return actionOn;}
	public void setActionOn(boolean boo) {actionOn = boo;}
	
	//Action - Process Select Event
	public void processSelectEvent(boolean selected){}
	
	//Action - Process Time On/Off Event
	public void timeIsMove(boolean boo) {}
	
	//Action - Process Mouse Event
	public void processMouseEvent(MouseEvent e) {//그냥 processEvent만 쓰려고 만듬.
		int eID = e.getID();
		if(eID == MouseEvent.MOUSE_PRESSED) {this.mousePressed(e);}
		else if(eID == MouseEvent.MOUSE_RELEASED) {this.mouseReleased(e);}
		else if(eID == MouseEvent.MOUSE_CLICKED) {this.mouseClicked(e);}
		else if(eID == MouseEvent.MOUSE_DRAGGED) {this.mouseDragged(e);}
		else if(eID == MouseEvent.MOUSE_MOVED) {this.mouseMoved(e);}
		else if(eID == MouseEvent.MOUSE_ENTERED) {this.mouseEntered(e);}
		else if(eID == MouseEvent.MOUSE_EXITED) {this.mouseExited(e);}
		
		for(AFunction function : aggregateFunction) {function.processMouseEvent(e);}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
}
