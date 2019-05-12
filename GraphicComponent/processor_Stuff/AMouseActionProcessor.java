package processor_Stuff;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import component_Stuff.GraphicComponent;

public abstract class AMouseActionProcessor  implements Serializable{
	private static final long serialVersionUID = 1814915772341129287L;
	
	protected GraphicComponent master;
	public void setMaster(GraphicComponent graphicComponent) {this.master=graphicComponent;}
	
	public void processEvent(MouseEvent e) {//그냥 processEvent만 쓰려고 만듬.
		int eID = e.getID();
		if(eID == MouseEvent.MOUSE_PRESSED) {this.mousePressed(e);}
		else if(eID == MouseEvent.MOUSE_RELEASED) {this.mouseReleased(e);}
		else if(eID == MouseEvent.MOUSE_CLICKED) {this.mouseClicked(e);}
		else if(eID == MouseEvent.MOUSE_DRAGGED) {this.mouseDragged(e);}
		else if(eID == MouseEvent.MOUSE_MOVED) {this.mouseMoved(e);}
		else if(eID == MouseEvent.MOUSE_ENTERED) {this.mouseEntered(e);}
		else if(eID == MouseEvent.MOUSE_EXITED) {this.mouseExited(e);}
	}
	
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	
}
