package function_SelectAndEvent;

import java.awt.event.MouseEvent;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCStorage;
import dragAndDrop.DragAndDropManager;
import function_Stuff.ATool;
import moveAndZoom.DrawingPanelMoveAndZoom;

public class HandTool extends ATool{//Select(1 or Area) & give Event to Selected GC & Drag Drop
	private static final long serialVersionUID = -7463646428712999248L;
	
	AreaSelectTool areaSelectRect = new AreaSelectTool();
	boolean firstDrag=true, areaSelect = false;
	
	public void mousePressed(MouseEvent e) {
		findMaster(e);//set master
		if(master==null) {//press on back ground
			GCStorage.clearSelected();
			areaSelect = true;
		}else if(!GCStorage.isSelected(master)) {//press on new GC
			GCStorage.clearSelected();
			GCStorage.addSelectedGC(master);
		}else {//press on selected GC
			GCStorage.removeSelectedGC(master);
			GCStorage.addSelectedGC(master);
		}
		
		if(e.getButton() == MouseEvent.BUTTON2) {
			DragAndDropManager.setDADOn(true);
		}
		
		basicAction(e);
	}

	public void mouseDragged(MouseEvent e) {
		basicAction(e);
		if (firstDrag) {
			DragAndDropManager.setDraggingComponent(master);
			firstDrag = false;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		basicAction(e);
		if(DragAndDropManager.isDADOn()) {
			DragAndDropManager.drop();
		}
		DragAndDropManager.setDADOn(false);
		areaSelect = false;
		firstDrag = true;
		master=null;
	}
	
	private void findMaster(MouseEvent e) {
		Vector<GraphicComponent> Components = GCStorage.getGCVector();
		for(int i=Components.size()-1; i>-1; i--) {
			if(Components.get(i).getAShape().isSelected(Components.get(i), DrawingPanelMoveAndZoom.transformPoint(e.getPoint()))) {
				master = Components.get(i);
				break;
			}
		}
	}
	
	private void basicAction(MouseEvent e) {
		if(!areaSelect) {
			if(GCStorage.getSelectedGCVector().size()>0) {
				GCStorage.getSelectedGCVector().lastElement().processEvent(e);
			}
		}
		else {areaSelectRect.processEvent(e);}
	}
	
	public void mouseClicked(MouseEvent e) {basicAction(e);}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
