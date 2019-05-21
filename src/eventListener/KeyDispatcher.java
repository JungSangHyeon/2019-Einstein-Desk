package eventListener;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import onOff.Ctrl;

public class KeyDispatcher implements KeyEventDispatcher {// Ű�̺�Ʈ�� ��Ŀ���� ����� ���� ���ִ� ��?
	
	int keyCode = 0;
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
			if (keyIs(KeyEvent.VK_C)) {}//1
			else if (keyIs(KeyEvent.VK_V)) {}//2
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
