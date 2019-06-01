package GCPanel_Stuff;

import java.awt.event.MouseEvent;

import GCPanel_LayoutNull_Stuff.GCPanel_LayoutNull;
import data.GCPanelStorage;

public class PeekabooPanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = 3141453841486592808L;

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		GCPanelStorage.remove(this);
	}
}
