package zStuff_GCPanel;

import java.awt.event.MouseEvent;

import zStuff_GCPanel_LayoutNull.GCPanel_LayoutNull;

public class NullPeekabooPanel extends GCPanel_LayoutNull{
	private static final long serialVersionUID = 3141453841486592808L;

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		GCPanelStorage.remove(this);
	}
}
