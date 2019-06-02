package canvas;

import java.awt.event.MouseEvent;

import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_Tool.ATool;

public class CanvasHandTool extends ATool{
	private static final long serialVersionUID = -7463646428712999248L;
	
	public static void init() {
		GCStorage_Selected.addSelectedGC(CanvasGC.getCanvas());
	}
	
	public void mousePressed(MouseEvent e) {
		if(!CanvasGC.getCanvas().isSelected()) {GCStorage_Selected.addSelectedGC(CanvasGC.getCanvas());}
		CanvasGC.processEvent(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(!CanvasGC.getCanvas().isSelected()) {GCStorage_Selected.addSelectedGC(CanvasGC.getCanvas());}
		CanvasGC.processEvent(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		CanvasGC.processEvent(e);
	}
}
