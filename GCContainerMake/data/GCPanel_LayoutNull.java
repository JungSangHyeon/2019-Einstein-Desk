package data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.Serializable;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import shape_Stuff.eShape;

public abstract class GCPanel_LayoutNull extends GraphicComponent implements Serializable{//호호 드럽다. 
	private static final long serialVersionUID = -9220238498788652662L;
	
	//Bound
	int x=0, y=0, width=0, height=0;
	public int getX() {return x;}
	public int getY() {return y;}
	
	//Color
	public void setPanelBackgroundColor(Color c) {this.setFillColor(c);}
	public void setPanelBorderColor(Color c) {this.setBorderColor(c);}
	
	//Component
	Vector<GraphicComponent> gcVector;
	
	//Event Handling
	GraphicComponent eventMaster;
	
	public GCPanel_LayoutNull() {
		this.setAShape(eShape.rect.getAShape());
		this.setShape(new Rectangle(x, y, width, height));
		gcVector = new Vector<GraphicComponent>();
	}

	public void setBounds(int x, int y, int width, int height) {
		this.x=x; this.y=y; this.width = width; this.height = height;
		this.setShape(new Rectangle(x, y, width, height));
	}
	
	public Rectangle getBound(int x, int y, int width, int height) {
		return new Rectangle(this.x+x, this.y+y, width, height);
	}
	
	public void add(GraphicComponent gc) {gcVector.add(gc);}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		for(GraphicComponent gc : gcVector) {
			gc.paint(g2d);
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {findMasterAndGiveEvent(e);}
	public void mousePressed(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mouseClicked(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mouseMoved(MouseEvent e) {giveEventToAll(e);}
	public void mouseDragged(MouseEvent e) {giveEventToMaster(e);}
	public void mouseReleased(MouseEvent e) {giveEventToMaster(e);}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private void findMasterAndGiveEvent(MouseEvent e) {
		findMaster(e);
		giveEventToMaster(e);
	}
	private void findMaster(MouseEvent e) {
		for(int i = gcVector.size()-1; i>-1; i--) {
			if(gcVector.get(i).getShape().contains(e.getPoint())) {
				eventMaster = gcVector.get(i); return;
			}
		}
		eventMaster = null;
	}
	private void giveEventToMaster(MouseEvent e) {if(eventMaster!=null) {eventMaster.processEvent(e);}}
	private void giveEventToAll(MouseEvent e) {for(GraphicComponent gc : gcVector) {gc.processEvent(e);}}
	
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
}
