package eventListener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import SAL.ProjectManager;
import SAL.SaveAndLoad;
import copyAndPaste.CopyAndPaste;
import deleteSelected.Delete;
import group.GroupTool;
import onOff.Ctrl;
import onOff.Debug;
import onOff.ThickFollowScale;
import redoUndo.RedoUndo;
import zOrder.Z_Order;
import zOrder.Z_Order.SendTo;
import zStuff_Text.FTextWrite_Stuff;

public class KeyDispatcher implements KeyEventDispatcher {// 키이벤트가 포커스에 상관이 없게 해주는 것?
	
	int keyCode = 0;
	JPanel master;
	public KeyDispatcher(JPanel master) {
		this.master=master;
	}

	private boolean keyIs(int vkControl) {return keyCode == vkControl;}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		keyCode = e.getKeyCode();
		if(!FTextWrite_Stuff.isTextEditAreaFocusOwner()) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {pressAction();}
			else if (e.getID() == KeyEvent.KEY_RELEASED) {releaseAction();}
		}
		master.repaint();
		return false;
	}
	
	private void pressAction() {
		if (keyIs(KeyEvent.VK_CONTROL)) {Ctrl.on();}
		if (keyIs(KeyEvent.VK_DELETE)) {Delete.delete();master.repaint();}
		if (keyIs(KeyEvent.VK_ESCAPE)) {Debug.onOff();}
		else if (keyIs(KeyEvent.VK_1)) {Z_Order.changeZOrder(SendTo.Top);}
		else if (keyIs(KeyEvent.VK_2)) {Z_Order.changeZOrder(SendTo.Hell);}
		else if (keyIs(KeyEvent.VK_3)) {Z_Order.changeZOrder(SendTo.oneUP);}
		else if (keyIs(KeyEvent.VK_4)) {Z_Order.changeZOrder(SendTo.oneDown);}
		if(Ctrl.isOn()) {
			if (keyIs(KeyEvent.VK_C)) {CopyAndPaste.copy();}
			else if (keyIs(KeyEvent.VK_V)) {CopyAndPaste.paste();}
			else if (keyIs(KeyEvent.VK_Z)) {RedoUndo.undo();}
			else if (keyIs(KeyEvent.VK_Y)) {RedoUndo.redo();}
			else if (keyIs(KeyEvent.VK_T)) {ThickFollowScale.onOff();}
			else if (keyIs(KeyEvent.VK_G)) {GroupTool.grouping();}
			else if (keyIs(KeyEvent.VK_U)) {GroupTool.unGrouping();}
			else if (keyIs(KeyEvent.VK_H)) {ProjectManager.toHome();}
//			else if (keyIs(KeyEvent.VK_H)) {ToHome.letsGoBack();}
			else if (keyIs(KeyEvent.VK_S)) {SaveAndLoad.save();}
			else if (keyIs(KeyEvent.VK_L)) {SaveAndLoad.load();}
			else if (keyIs(KeyEvent.VK_UP)) {}
			else if (keyIs(KeyEvent.VK_DOWN)) {}
		}
	}

	private void releaseAction() {
		if (keyIs(KeyEvent.VK_CONTROL)) {Ctrl.off();}
	}
}
