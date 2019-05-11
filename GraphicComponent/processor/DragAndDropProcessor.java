package processor;

import java.awt.event.MouseEvent;
import java.io.Serializable;

import dragAndDrop.DragAndDropManager;
import stuff_Component.AMouseActionProcessor;

public class DragAndDropProcessor extends AMouseActionProcessor implements Serializable{
	private static final long serialVersionUID = -6286753664292758180L;
	
	boolean firstDrag=true;
	
	public void mouseDragged(MouseEvent e) {
		if(firstDrag) {
			DragAndDropManager.setDraggingComponent(master);
			firstDrag = false;
		}
	}

	public void mouseReleased(MouseEvent e) {
		DragAndDropManager.drop();
		DragAndDropManager.reset();
		firstDrag = true;
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
