package lineSetting_Stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import component_Stuff.GraphicComponent;

@SuppressWarnings("serial")
public abstract class SizeBarAndLineTesttttttt extends GraphicComponent{

	GeneralPath line;
	boolean mouseIn=false;
	RoundRectangle2D.Float handle;
	
	int roundRectF = 5;
	int barH = 2, barW = 300, barStartX = 7;
	int hadleLocation = 80, handleW = 8, handleH = 24, handleY = 78;
	
	Color handleColor, basicHandleC = new Color(0, 183, 195), mouseOnHandleC = Color.BLACK, pressedHandleColor = new Color(204, 204, 204);
	Color leftBarC = new Color(247, 99, 12), rightBarC = new Color(145, 145, 145);
	Point mouseDragStart;
	
	public SizeBarAndLineTesttttttt() {
		this.setShape(new Rectangle(0,0,312,110)); //this.setSize(312,110);
		init();
	}
	public void setLocation(int x, int y) {
		this.setShape(new Rectangle(x, y, 312, 110));
		init();
	}
	
	
	private void init() {
		barStartX+=this.getX();
		hadleLocation+=this.getX();
		handleY+=this.getY();
		
		handleColor = basicHandleC;
		int lineX = (int) (3+this.getX()), lineY = (int) (25+this.getY());//야는 냅둡시다
		line = new GeneralPath();
		line.moveTo(30+lineX,27+lineY);//잘 모르것음.
		line.curveTo(110+lineX, -33+lineY, 190+lineX, 69+lineY, 270+lineX, 5+lineY);
	}
	private double getX() {return this.getShape().getBounds().getX();}
	private double getY() {return this.getShape().getBounds().getY();}

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
	
	public void mousePressed(MouseEvent e) {if(mouseIn) {mouseDragStart = e.getPoint();}System.out.println("on press");}
	public void mouseReleased(MouseEvent e) {handleColor = basicHandleC;}	
	public void mouseDragged(MouseEvent e) {
		System.out.println("on drag");
		if(mouseIn&&hadleLocation>0&&hadleLocation<barW+(int)getX()) {
			handleColor = pressedHandleColor;
			hadleLocation += e.getPoint().x - mouseDragStart.x;
			if(hadleLocation<=0) {hadleLocation=1;}
			if(hadleLocation>=barW+(int)getX()) {hadleLocation=barW-1;}
			mouseDragStart = e.getPoint();
			setThick(hadleLocation- (int)getX());
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(handle.contains(e.getPoint())) {
			mouseIn=true;
			handleColor = mouseOnHandleC;
		}else {
			mouseIn=false;
			handleColor = basicHandleC;
		}		
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	@Override
	public void paint(Graphics2D g) {
		g.setColor(new Color(242,242,242));  g.fill(this.getShape().getBounds());
		g.setColor(getColor()); g.setStroke(getTargetStroke()); g.draw(line);
		g.setColor(rightBarC); g.fillRect(barStartX, handleY+handleH/2-barH/2, barW, barH);
		g.setColor(leftBarC); g.fillRect(barStartX, handleY+handleH/2-barH/2, hadleLocation - (int)this.getX() -handleW/2, barH);
		handle = new  RoundRectangle2D.Float(hadleLocation,handleY,handleW,handleH,roundRectF,roundRectF);
		g.setColor(handleColor); g.fill(handle);
	}
}
