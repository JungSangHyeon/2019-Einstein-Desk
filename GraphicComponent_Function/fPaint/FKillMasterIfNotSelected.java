package fPaint;

import data.GCPanelStorage;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import zStuff_Function.AFunction;

public class FKillMasterIfNotSelected extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	public void processSelectEvent(boolean selected) {
		if(!selected){
			if(GCPanelStorage.have(master)) {GCPanelStorage.remove(master);}
			if(GCStorage_Normal.have(master)) {GCStorage_Normal.removeGC(master);}
			if(GCStorage_Selected.have(master)) {GCStorage_Selected.removeSelectedGC(master);}
		}
	}
}
