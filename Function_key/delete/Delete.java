package delete;

import data.GCStorage_Normal;
import data.GCStorage_Selected;
import doUndo.RedoUndo;
import zStuff_GraphicComponent.GraphicComponent;

public class Delete {

	public static void delete() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {GCStorage_Normal.removeGC(gc);}
		GCStorage_Selected.clearSelected();
		RedoUndo.saveNowInHistory();
	}
}
