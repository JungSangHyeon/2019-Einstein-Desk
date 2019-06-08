package toolPanel;

import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class OffGCPanel extends GCPanel_LayoutPixel_Y {

	public OffGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(1, 1);
		this.setItemDraggable(false);
	}

	public void addItems() {
		this.add(GCCreator.create(ToolSelectGC.off));
	}
}
