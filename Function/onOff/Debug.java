package onOff;

import testPanel.TestPanel;
import testPanel.TestPanelX;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;

public class Debug {
	static boolean on = false;
	public static boolean isOn() {return on;}
	public static void on() {on = true; onAction();}
	public static void off() {on = false; offAction();}
	public static void onOff() {
		on=(!on);
		if(on) {onAction();}
		else {offAction();}
	}
	
	static GraphicComponent[] testPanel = {
			new TestPanel(),
			new TestPanelX(),
	};
	private static void onAction() {for(GraphicComponent gc : testPanel) {GCPanelStorage.add(gc);}}
	private static void offAction() {for(GraphicComponent gc : testPanel) {GCPanelStorage.remove(gc);}}
}
