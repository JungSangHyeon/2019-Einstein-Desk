package tool;

import java.awt.event.MouseEvent;
import java.util.Vector;

import data.GCStorage;
import moveAndZoom.DrawingPanelMoveAndZoom;
import stuff_Component.GraphicComponent;
import toolStuff.ATool;

public class HandTool extends ATool{//Select(1 or N) & give Event
	private static final long serialVersionUID = -7463646428712999248L;
	
	boolean areaSelect = false;
	AreaSelectTool areaSelectRect = new AreaSelectTool();
	
	public void mousePressed(MouseEvent e) {
		GraphicComponent nowSelected = null;
		Vector<GraphicComponent> Components = GCStorage.getGCVector();
		for(int i=Components.size()-1; i>-1; i--) {
			if(Components.get(i).getShape().contains(DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
				nowSelected = Components.get(i);
				break;
			}
		}
		if(nowSelected==null) {//press on back ground
			GCStorage.clearSelected();
			areaSelect = true;
		}else if(!GCStorage.isSelected(nowSelected)) {//press on new GC
			GCStorage.clearSelected();
			GCStorage.addSelectedGC(nowSelected);
		}//else {}//press on selected GC -> NOTHING
		basicAction(e);
	}

	public void mouseReleased(MouseEvent e) {
		basicAction(e);
		areaSelect = false;
	}
	
	private void basicAction(MouseEvent e) {
		if(!areaSelect) {for(GraphicComponent gc : GCStorage.getSelectedGCVector()) {gc.processEvent(e);}}
		else {areaSelectRect.processEvent(e);}
	}
	
	public void mouseDragged(MouseEvent e) {basicAction(e);}
	public void mouseClicked(MouseEvent e) {basicAction(e);}
	public void mouseMoved(MouseEvent e) {basicAction(e);}//need?
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
