package zOrder;

import java.util.Vector;

import zStuff_GraphicComponent.GCStorage_Normal;
import zStuff_GraphicComponent.GraphicComponent;

public class Z_Order {
	
	public enum SendTo{Top, Hell, oneUP, oneDown;}
	
	public static void changeZOrder(SendTo task) {
		if(task == SendTo.Top) {sendSelectedToTop();}
		else if(task == SendTo.Hell) {sendSelectedToHell();}
		else if(task == SendTo.oneUP) {sendSelectedOneUp();}
		else if(task == SendTo.oneDown) {sendSelectedOneDown();}
	}
	
	private static void sendSelectedToTop() {
		Vector<GraphicComponent> gcStorage = GCStorage_Normal.getGCVector();
		Vector<GraphicComponent> temp = new Vector<GraphicComponent>();
		for(int i = gcStorage.size()-1; i>-1; i--) {
			GraphicComponent nowGC = gcStorage.get(i);
			if(nowGC.isSelected()) {
				gcStorage.remove(nowGC);
				temp.add(0,nowGC);
			}
		}
		gcStorage.addAll(temp);
	}

	private static void sendSelectedToHell() {
		Vector<GraphicComponent> gcStorage = GCStorage_Normal.getGCVector();
		Vector<GraphicComponent> temp = new Vector<GraphicComponent>();
		for(int i = gcStorage.size()-1; i>-1; i--) {
			GraphicComponent nowGC = gcStorage.get(i);
			if(nowGC.isSelected()) {
				gcStorage.remove(nowGC);
				temp.add(0, nowGC);
			}
		}
		for(int i = temp.size()-1; i>-1; i--) {
			gcStorage.add(0, temp.get(i));
		}
	}

	private static void sendSelectedOneUp() {
		Vector<GraphicComponent> gcStorage = GCStorage_Normal.getGCVector();
		for(int i = gcStorage.size()-1; i>-1; i--) {
			GraphicComponent nowGC = gcStorage.get(i);
			if(nowGC.isSelected()) {
				try {
					gcStorage.add(i+2, nowGC);
					gcStorage.remove(i);
				}catch(Exception e) {break;}//더이상 위로 못가는 경우, 암것도 안하게 함.
			}
		}
	}

	private static void sendSelectedOneDown() {
		Vector<GraphicComponent> gcStorage = GCStorage_Normal.getGCVector();
		for(int i=0; i<gcStorage.size(); i++) {
			GraphicComponent nowGC = gcStorage.get(i);
			if(nowGC.isSelected()) {
				try {
					gcStorage.add(i-1, nowGC);
					gcStorage.remove(i+1);
				}catch(Exception e) {break;}//더이상 아래로 못가는 경우, 암것도 안하게 함.
			}
		}
	}
	
}
