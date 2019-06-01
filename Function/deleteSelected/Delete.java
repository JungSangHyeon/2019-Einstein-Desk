package deleteSelected;

import redoUndo.RedoUndo;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class Delete {

	public static void delete() {
		for(GraphicComponent gc : GCStorage_Selected.getSelectedGCVector()) {GCStorage_Normal.removeGC(gc);}
		GCStorage_Selected.clearSelected();
		RedoUndo.saveNowInHistory();
	}
}
