package eventListener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import copyAndPaste.CopyAndPaste;
import onOff.Ctrl;

public class KeyDispatcher implements KeyEventDispatcher {// 키이벤트가 포커스에 상관이 없게 해주는 것?
	
	int keyCode = 0;
	JPanel master;
	public KeyDispatcher(JPanel master) {
		this.master=master;
	}

	private boolean keyIs(int vkControl) {return keyCode == vkControl;}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		keyCode = e.getKeyCode();
		if (e.getID() == KeyEvent.KEY_PRESSED) {pressAction();}
		else if (e.getID() == KeyEvent.KEY_RELEASED) {releaseAction();}
		return false;
	}
	
	private void pressAction() {
		if (keyIs(KeyEvent.VK_CONTROL)) {Ctrl.on();}
		if (keyIs(KeyEvent.VK_DELETE)) {}
		if (keyIs(KeyEvent.VK_ESCAPE)) {}
		if(Ctrl.isOn()) {
			if (keyIs(KeyEvent.VK_C)) {CopyAndPaste.copy();}
			else if (keyIs(KeyEvent.VK_V)) {CopyAndPaste.paste(); master.repaint();}
			else if (keyIs(KeyEvent.VK_D)) {}
			else if (keyIs(KeyEvent.VK_Z)) {}
			else if (keyIs(KeyEvent.VK_Y)) {}
			else if (keyIs(KeyEvent.VK_UP)) {}
			else if (keyIs(KeyEvent.VK_DOWN)) {}
		}
	}

	private void releaseAction() {
		if (keyIs(KeyEvent.VK_CONTROL)) {Ctrl.off();}
	}
}
