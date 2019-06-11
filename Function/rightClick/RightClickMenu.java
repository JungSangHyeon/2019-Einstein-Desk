package rightClick;

import java.awt.Point;
import java.awt.event.MouseEvent;

import rightClickPanel.ChoosePanel;
import rightClickPanel.ETCPanel;
import rightClickPanel.ShapeColorSettingPanel;
import zStuff_GCPanel.GCPanelStorage;
import zStuff_GraphicComponent.GraphicComponent;

public class RightClickMenu {

	static ETCPanel eTCPanel;
	static ChoosePanel choosePanel;
	static ShapeColorSettingPanel shapeColorSelectPanel;
	static Point nowPoint;
	public static Point getNowPoint() {return nowPoint;}

	public static void OpenMenu(Point point) {
		nowPoint = point;
		
		eTCPanel = new ETCPanel(point);
		GCPanelStorage.add(eTCPanel);
		
		choosePanel = new ChoosePanel(point);
		GCPanelStorage.add(choosePanel);
	}

	public static void checkAndOff(MouseEvent e) {//Release ½Ã.
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			if(GCPanelStorage.have(eTCPanel)) {
				GCPanelStorage.remove(eTCPanel);
			}
			if(GCPanelStorage.have(choosePanel)&&!choosePanel.getShape().contains(e.getPoint())&&!mouseOnColorSettingPanel(e.getPoint())) {
				GCPanelStorage.remove(choosePanel);
			}
		} 
	}

	private static boolean mouseOnColorSettingPanel(Point point) {
		for(GraphicComponent gc : GCPanelStorage.getGCPanelVector()) {
			if(gc instanceof ShapeColorSettingPanel) {
				if(gc.getShape().contains(point)) {return true;}
			}
		}
		return false;
	}

}
