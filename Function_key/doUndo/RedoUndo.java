package doUndo;

import java.util.Vector;

import component_Stuff.GraphicComponent;
import data.GCStorage_Normal;
import data.GCStorage_Selected;
import deepClone.DeepClone;

public class RedoUndo {

	static Vector <Vector<GraphicComponent>> history = new Vector <Vector<GraphicComponent>>();
	static Vector<GraphicComponent> temp = new Vector<GraphicComponent>(); 
	static int nowHistoryNum=0;
	
	public static void setFirst() {addCopySaveInHistory();}//암것도 없는 상태 추가. 
	
	public static void  saveNowInHistory() {//pdr & cmc create, erase, delete, copy&paste, 
		if(history.size()>0){for(int i = history.size()-1; i>0; i--) {if(i>nowHistoryNum) {history.remove(i);}}}//앞부분 버리는 겨.
		addCopySaveInHistory();
		nowHistoryNum=history.size()-1;
	}
	
	private static void addCopySaveInHistory() {
		temp = new Vector<GraphicComponent>();
		for(GraphicComponent c : GCStorage_Normal.getGCVector()) {temp.add((GraphicComponent)DeepClone.clone(c));}
		history.add(temp);
	}
	
	public static void undo() {if(nowHistoryNum>0) {nowHistoryNum--;changeNow();}}
	public static void redo() {if(nowHistoryNum<history.size()-1) {nowHistoryNum++;changeNow();}}
	private static void changeNow() {GCStorage_Normal.clearGC(); GCStorage_Normal.addAllToGC(history.get(nowHistoryNum)); GCStorage_Selected.clearSelected();}
	
	public static void historyClear() {history.clear();}
	public static void clearAll() {
		temp.clear();
		history.clear();
		GCStorage_Normal.clearGC();
	}	
}
