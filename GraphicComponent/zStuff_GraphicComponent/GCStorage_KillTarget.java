package zStuff_GraphicComponent;

import java.util.Vector;

import zStuff_GCPanel.GCPanelStorage;

public class GCStorage_KillTarget {
	private static Vector<GraphicComponent> killGCVector = new Vector<GraphicComponent>();
	public static void killGCAtNextPaint(GraphicComponent target) {killGCVector.add(target);}
	
	public static void killTargets() {
		for(GraphicComponent target : killGCVector) {
			if(GCStorage_Normal.have(target)) {GCStorage_Normal.removeGC(target);}
			else if(GCStorage_Selected.have(target)) {GCStorage_Selected.removeSelectedGC(target);}
			else if(GCPanelStorage.have(target)) {GCPanelStorage.remove(target);}
		}
	}
}
