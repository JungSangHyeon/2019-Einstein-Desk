package delete;

import component_Stuff.GraphicComponent;
import data.GCStorage;

public class Delete {

	public static void delete() {
		for(GraphicComponent gc : GCStorage.getSelectedGCVector()) {GCStorage.removeGC(gc);}
		GCStorage.clearSelected();
	}
	
}
