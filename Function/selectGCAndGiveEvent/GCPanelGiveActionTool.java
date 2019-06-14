package selectGCAndGiveEvent;

import java.awt.event.MouseEvent;
import java.util.Vector;

import onOff.Alt;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Tool.ATool;

public class GCPanelGiveActionTool extends ATool{
	private static final long serialVersionUID = -272528931530692482L;
	
	@Override
	public void processEvent(MouseEvent e) {
		super.processEvent(e);
	}
	
	public void mouseWheelMoved(MouseEvent e) {findMasterAndGiveEvent(e);}
	public void mousePressed(MouseEvent e) {findMasterAndGiveEvent(e);}
	
	public void mouseMoved(MouseEvent e) {giveEventToAll(e);}
	
	public void mouseClicked(MouseEvent e) {giveEventToMaster(e);}
	public void mouseDragged(MouseEvent e) {giveEventToMaster(e);}
//	public void mouseReleased(MouseEvent e) {giveEventToMaster(e);}//change to below to D&D. 0613 11:42
	public void mouseReleased(MouseEvent e) {if(Alt.isOn()) {findMasterAndGiveEvent(e);}else{giveEventToMaster(e);}}
	
	private void findMasterAndGiveEvent(MouseEvent e) {
		findMaster(e);
		giveEventToMaster(e);
	}
	
	public void newEvent() {master=null;}
	public boolean isTakeEvent() {return master!=null;}
	
	private void findMaster(MouseEvent e) {
		master = null;
		Vector<GraphicComponent> panelVector = GCPanelStorage.getGCPanelVector();
		for(int i = panelVector.size()-1; i>-1; i--) {
			if(master==null) {
				if(panelVector.get(i).getShape().contains(e.getPoint())) {
					master = panelVector.get(i);
				}else if(e.getID()==MouseEvent.MOUSE_PRESSED){panelVector.get(i).setSelected(false);}
			}else {panelVector.get(i).setSelected(false);}
		}
		if(master==null) {
			for(GraphicComponent gc : GCPanelStorage.getGCPanelVector()) {
				gc.setSelected(false);
			}
		}
	}
	private void giveEventToMaster(MouseEvent e) {
		if(master!=null) {master.processEvent(e);}
	}
	private void giveEventToAll(MouseEvent e) {
		for(GraphicComponent gc : GCPanelStorage.getGCPanelVector()) {
			gc.processEvent(e);
		}
	}
}
