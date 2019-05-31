package function_Paint;

import data.GCPanelStorage;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import zFunction_Stuff.AFunction;

public class FKillMasterIfNotSelected extends AFunction{
	private static final long serialVersionUID = 4299512141606163429L;
	
	boolean sel = false;
	public void processSelectEvent(boolean selected) {
		sel = selected;
		if(!selected){
			if(GCPanelStorage.have(master)) {GCPanelStorage.remove(master);}
			if(GCStorage_Normal.have(master)) {GCStorage_Normal.removeGC(master);}
			if(GCStorage_Selected.have(master)) {GCStorage_Selected.removeSelectedGC(master);}
		}
	}
}
