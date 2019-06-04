package GCPanel;

import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class SlideOnPanel extends GCPanel_LayoutPixel {

	public SlideOnPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(1, 1);
		
		this.setGCLocation(0, 0);
		this.setItemDraggable(false);

		this.add(GCCreator.create(ToolSelectGC.slideOnFunctions));
	}
}
