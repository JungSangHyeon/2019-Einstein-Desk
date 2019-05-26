package function_SelectAndEvent;

import java.awt.event.MouseEvent;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCPanelStorage;
import function_Stuff.ATool;

public class GCPanelGiveActionTool extends ATool{
	private static final long serialVersionUID = -272528931530692482L;
	
	public void mouseWheelMoved(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mousePressed(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mouseClicked(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mouseMoved(MouseEvent e) {findMasterAndGiveEvent(e);}
	
	public void mouseDragged(MouseEvent e) {giveEventToMaster(e);}
	public void mouseReleased(MouseEvent e) {giveEventToMaster(e);}
	
	private void findMasterAndGiveEvent(MouseEvent e) {
		findMaster(e);
		giveEventToMaster(e);
	}
	
	private void findMaster(MouseEvent e) {
		Vector<GraphicComponent> panelVector = GCPanelStorage.getGCPanelVector();
		for(int i = panelVector.size()-1; i>-1; i--) {
			if(panelVector.get(i).getShape().contains(e.getPoint())) {
				master = panelVector.get(i);
				return;
			}
		}
		master = null;
	}
	
	private void giveEventToMaster(MouseEvent e) {
		if(master!=null) {master.processEvent(e);}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}