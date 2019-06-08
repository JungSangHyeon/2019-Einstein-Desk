package toolPanel;

import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class ToolBTNGCPanel extends GCPanel_LayoutPixel_Y {

	public ToolBTNGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(3, 1);
		this.setItemDraggable(false);
	}

	public void addItems() {
		this.add(GCCreator.create(ToolSelectGC.undo));
		this.add(GCCreator.create(ToolSelectGC.redo));
		this.add(GCCreator.create(ToolSelectGC.newSlide));
		this.add(GCCreator.create(ToolSelectGC.imageLoad));
		this.add(GCCreator.create(ToolSelectGC.saveAsImage));
	}
}
