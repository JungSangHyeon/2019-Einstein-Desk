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
		GCStorage_Selected.addSelectedGC(group);
	}
	
	public static void unGrouping() {
		Vector<GraphicComponent> releaseGroupMember =  new Vector<GraphicComponent>();
		Vector<GraphicComponent> removeTarget =  new Vector<GraphicComponent>();
		Vector<GraphicComponent> selectGC =  GCStorage_Selected.getSelectedGCVector();
		for(GraphicComponent gc : selectGC) {
			if(gc.getAllAggregateGCs().size()>0) {
				removeTarget.add(gc);
				for(GraphicComponent gcChild : gc.getMyAggregateGCs()) {
					releaseGroupMember.add(gcChild);
				}
			}
		}
		for(GraphicComponent gc : removeTarget) {
			GCStorage_Normal.removeGC(gc);
			GCStorage_Selected.removeSelectedGC(gc);
		}
		GCStorage_Normal.addAllToGC(releaseGroupMember);//안의 것을 그냥 노말에 넣기만 하면 피피티의 컴포넌트 액션처럼 됨.
		GCStorage_Selected.addAllSelectedGC(releaseGroupMember);
	}
	
}
