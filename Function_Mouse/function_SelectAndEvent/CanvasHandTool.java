package function_SelectAndEvent;

import java.awt.event.MouseEvent;

import data.GCStorage_Selected;
import function_Stuff.ATool;
import global.GCCanvas;

public class CanvasHandTool extends ATool{
	private static final long serialVersionUID = -7463646428712999248L;
	
	public static void init() {
		GCStorage_Selected.addSelectedGC(GCCanvas.getCanvas());
	}
	
	public void mousePressed(MouseEvent e) {
		if(!GCCanvas.getCanvas().isSelected()) {GCStorage_Selected.addSelectedGC(GCCanvas.getCanvas());}
		GCCanvas.processEvent(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(!GCCanvas.getCanvas().isSelected()) {GCStorage_Selected.addSelectedGC(GCCanvas.getCanvas());}
		GCCanvas.processEvent(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		GCCanvas.processEvent(e);
	}
}
