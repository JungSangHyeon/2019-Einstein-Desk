package GCPanel_LineSetting_Stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public abstract class SizeBarAndLine extends GraphicComponent{

	//User //곡선 그리는거 이해하면 여기 정리하시우. 더럽소.
	int width = 312, height = 110;
	int barH = 2, barW = 300, barStartX = 7;
	int hadleX = 80, handleY = 78, handleW = 8, handleH = 24, handleRoundFactor = 5;
	
	Color handleColor, basicHandleC = new Color(0, 183, 195), mouseOnHandleC = Color.BLACK, pressedHandleColor = new Color(204, 204, 204);
	Color leftBarC = new Color(247, 99, 12), rightBarC = new Color(145, 145, 145);
	
	//System
	GeneralPath line;
	Point mouseDragStart;
	boolean mouseIn=false;
	RoundRectangle2D.Float handle;
	
	private void init() {
		barStartX+=this.getX();
		hadleX+=this.getX();
		handleY+=this.getY();
		handleColor = basicHandleC;
		int lineX = (int) (3+this.getX()), lineY = (int) (25+this.getY());
		line = new GeneralPath();
		line.moveTo(30+lineX,27+lineY);//잘 모르것음.
		line.curveTo(110+lineX, -33+lineY, 190+lineX, 69+lineY, 270+lineX, 5+lineY);
	}
	
	public SizeBarAndLine() {this.setShape(new Rectangle(0, 0, width, height)); init();}
	public void setGCLocation(int x, int y) {this.setShape(new Rectangle(x, y, width, height)); init();}
	private double getX() {return this.getShape().getBounds().getX();}
	private double getY() {return this.getShape().getBounds().getY();}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(new Color(242,242,242));  g.fill(this.getShape().getBounds());
		g.setColor(getColor()); g.setStroke(getTargetStroke()); g.draw(line);
		g.setColor(rightBarC); g.fillRect(barStartX, handleY+handleH/2-barH/2, barW, barH);
		g.setColor(leftBarC); g.fillRect(barStartX, handleY+handleH/2-barH/2, hadleX - (int)this.getX() -handleW/2, barH);
		handle = new  RoundRectangle2D.Float(hadleX,handleY,handleW,handleH,handleRoundFactor,handleRoundFactor);
		g.setColor(handleColor); g.fill(handle);
	}
	
	public void mousePressed(MouseEvent e) {if(mouseIn) {mouseDragStart = e.getPoint();}}
	public void mouseReleased(MouseEvent e) {handleColor = basicHandleC;}	
	public void mouseDragged(MouseEvent e) {
		if(mouseIn&&hadleX>0&&hadleX<barW+(int)getX()+handleW/2) {
			handleColor = pressedHandleColor;
			hadleX += e.getPoint().x - mouseDragStart.x;
			if(hadleX<=5+(int)getX()) {hadleX=6+(int)getX();}
		if(hadleX>=barW+(int)getX()+handleW/2) {hadleX=barW+(int)getX()+handleW/2-2;}
			mouseDragStart = e.getPoint();
			setThick(hadleX - (int)getX());
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(handle.contains(e.getPoint())) {mouseIn=true; handleColor = mouseOnHandleC;}
		else {mouseIn=false; handleColor = basicHandleC;}
	}
	
	public abstract Color getColor();
	public abstract Stroke getTargetStroke();
	public abstract void setThick(int hadleLocation);
	
	@Override
	public void processEvent(MouseEvent e) {
		switch(e.getID()) {
		case MouseEvent.MOUSE_PRESSED : this.mousePressed(e); break;
		case MouseEvent.MOUSE_RELEASED : this.mouseReleased(e); break;
		case MouseEvent.MOUSE_CLICKED : this.mouseClicked(e); break;
		case MouseEvent.MOUSE_DRAGGED : this.mouseDragged(e); break;
		case MouseEvent.MOUSE_MOVED : this.mouseMoved(e); break;
		case MouseEvent.MOUSE_ENTERED : this.mouseEntered(e); break;
		case MouseEvent.MOUSE_EXITED : this.mouseExited(e); break;
		case MouseEvent.MOUSE_WHEEL : this.mouseWheelMoved((MouseWheelEvent) e); break;
		default : break;
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {}
}
