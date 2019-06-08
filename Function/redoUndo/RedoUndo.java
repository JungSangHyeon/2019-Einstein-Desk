package redoUndo;

import java.util.Vector;

import deepClone.DeepClone;
import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_GraphicComponent.GraphicComponent;

public class RedoUndo {

	static Vector <Vector<GraphicComponent>> history = new Vector <Vector<GraphicComponent>>();
	static Vector<GraphicComponent> temp = new Vector<GraphicComponent>(); 
	static int nowHistoryNum=0;
	
	public static void setFirst() {addCopySaveInHistory();}//암것도 없는 상태 추가.
	
	//Save List : Copy & Paste, Delete Selected, Eraser, Group, Image Load, Z-Order, Move, Rotate, Resize, Text
	public static void  saveNowInHistory() {
		if(history.size()>0){for(int i = history.size()-1; i>0; i--) {if(i>nowHistoryNum) {history.remove(i);}}}//앞부분 버리는 겨.
		addCopySaveInHistory();
		nowHistoryNum=history.size()-1;
		if(history.size()>10) {
			history.remove(history.firstElement());
			nowHistoryNum--;
		}
	}
	
	private static void addCopySaveInHistory() {
		temp = new Vector<GraphicComponent>();
		for(GraphicComponent c : GCStorage_Normal.getGCVector()) {
			boolean restartTime = false;
			if(c.isTimeMoving()) {c.moveTime(false); restartTime = true;}
			temp.add((GraphicComponent)DeepClone.clone(c));
			if(restartTime) {c.moveTime(true);}
		}
		history.add(temp);
	}
	
	public static void undo() {if(nowHistoryNum>0) {nowHistoryNum--;changeNow();}}
	public static void redo() {if(nowHistoryNum<history.size()-1) {nowHistoryNum++;changeNow();}}
	private static void changeNow() {
		GCStorage_Normal.clearGC(); 
		GCStorage_Normal.addAllToGC(history.get(nowHistoryNum));
		GCStorage_Selected.clearSelected();
	}
	
	public static void clear() {
		history.clear();
		temp.clear();
		nowHistoryNum=0;
	}
}
