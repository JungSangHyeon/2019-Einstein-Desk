package eventListener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import SAL.ProjectManager;
import copyAndPaste.CopyAndPaste;
import deleteSelected.Delete;
import group.GroupTool;
import music.Speaker;
import onOff.Alt;
import onOff.Ctrl;
import onOff.Debug;
import onOff.ThickFollowScale;
import presentation.Presentation;
import redoUndo.RedoUndo;
import slide.SlidePanel;
import toHome.ToHome;
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
		if (keyIs(KeyEvent.VK_ALT)) {Alt.on();}
		if (keyIs(KeyEvent.VK_DELETE)) {Delete.delete();}
		if (keyIs(KeyEvent.VK_ESCAPE)) {Debug.onOff();}
		else if (keyIs(KeyEvent.VK_1)) {Z_Order.changeZOrder(SendTo.Top);}
		else if (keyIs(KeyEvent.VK_2)) {Z_Order.changeZOrder(SendTo.Hell);}
		else if (keyIs(KeyEvent.VK_3)) {Z_Order.changeZOrder(SendTo.oneUP);}
		else if (keyIs(KeyEvent.VK_4)) {Z_Order.changeZOrder(SendTo.oneDown);}
		else if (keyIs(KeyEvent.VK_F5)) {Presentation.startPresentation();}
		else if (keyIs(KeyEvent.VK_UP)) {Presentation.beforeSlide();SlidePanel.beforeSlide();}
		else if (keyIs(KeyEvent.VK_DOWN)) {Presentation.nextSlide();SlidePanel.nextSlide();}
		if(Ctrl.isOn()) {
			if (keyIs(KeyEvent.VK_C)) {CopyAndPaste.copy();}
			if (keyIs(KeyEvent.VK_X)) {CopyAndPaste.cut();}
			else if (keyIs(KeyEvent.VK_V)) {CopyAndPaste.paste();}
			else if (keyIs(KeyEvent.VK_Z)) {RedoUndo.undo();}
			else if (keyIs(KeyEvent.VK_Y)) {RedoUndo.redo();}
			else if (keyIs(KeyEvent.VK_T)) {ThickFollowScale.onOff();}
			else if (keyIs(KeyEvent.VK_G)) {GroupTool.grouping();}
			else if (keyIs(KeyEvent.VK_U)) {GroupTool.unGrouping();}
			else if (keyIs(KeyEvent.VK_H)) {ProjectManager.toHome();}
			else if (keyIs(KeyEvent.VK_B)) {ToHome.letsGoBack();}
			else if (keyIs(KeyEvent.VK_M)) {musicControl();}
		}
	}

	private void musicControl() {
		if(Speaker.isOn()) {Speaker.off();}
		else {Speaker.on("music/Golden Hour.wav", true);}
	}

	private void releaseAction() {
		if (keyIs(KeyEvent.VK_CONTROL)) {Ctrl.off();}
		if (keyIs(KeyEvent.VK_ALT)) {Alt.off();}
	}
}
