package toolPanel;

import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class ToolSelectGCPanel extends GCPanel_LayoutPixel_Y {

	public ToolSelectGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(5, 1);
		this.setItemDraggable(false);
	}

	public void addItems() {
		this.add(GCCreator.create(ToolSelectGC.penSelector));
		this.add(GCCreator.create(ToolSelectGC.highlightSelector));
		this.add(GCCreator.create(ToolSelectGC.eraserToolSelector));
		this.add(GCCreator.create(ToolSelectGC.shapeToolSelector));
		this.add(GCCreator.create(ToolSelectGC.handToolSelector));
		this.add(GCCreator.create(ToolSelectGC.canvasHandSelector));
		this.add(GCCreator.create(ToolSelectGC.connectSelector));
	}
}
