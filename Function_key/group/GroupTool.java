package group;

import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;

public class GroupTool {

	public static void grouping() {
		
		Vector<GraphicComponent> groupMember = GCStorage_Selected.getSelectedGCVector();
		
		GroupGC group = new GroupGC(groupMember);
		GCStorage_Normal.addNewGC(group);
		
		for(int i=groupMember.size()-1; i>-1; i--) {
			GCStorage_Normal.removeGC(groupMember.get(i));
		}
		GCStorage_Selected.clearSelected();
		
	}
	
}
