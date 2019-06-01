package function_Stuff;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import zStuff_GraphicComponent.GraphicComponent;

public abstract class ATool  implements Serializable{//AFUnction하고 똑같음. 따로 있는게 더 좋을 것 같아서 나눔.
	private static final long serialVersionUID = -6420030261551624512L;
	
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
		else if(eID == MouseEvent.MOUSE_WHEEL) {this.mouseWheelMoved(e);}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseEvent e) {}
}
