package rightClick;

import java.awt.Point;

import rightClickPanel.ChoosePanel;
import rightClickPanel.ETCPanel;
import zStuff_GCPanel.GCPanelStorage;

public class RightClickMenu {

	static ETCPanel eTCPanel;
	static ChoosePanel choosePanel;
	
	public static void OpenMenu(Point point) {
		eTCPanel = new ETCPanel(point);
		GCPanelStorage.add(eTCPanel);
		
		choosePanel = new ChoosePanel(point);
		GCPanelStorage.add(choosePanel);
	}

}
