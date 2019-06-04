package fPaint;

import zStuff_Function.AFunction;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;

public class FKillMasterIfNotSelected extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	public void selectEvent(boolean selected) {
		if(!selected){
			if(GCPanelStorage.have(master)) {GCPanelStorage.remove(master);}
			if(GCStorage_Normal.have(master)) {GCStorage_Normal.removeGC(master);}
			if(GCStorage_Selected.have(master)) {GCStorage_Selected.removeSelectedGC(master);}
		}
	}
}
