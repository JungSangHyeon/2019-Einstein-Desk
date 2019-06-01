package GCPanel;

import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCEnum;
import zStuff_GraphicComponent.GCEnum.eGC;

@SuppressWarnings("serial")
public class OffGCPanel extends GCPanel_LayoutPixel {

	public OffGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(1, 1);
		
		this.setGCLocation(1920 - this.getWidth(), 0);
		
		this.setItemDraggable(false);

		this.add(GCEnum.getGC(eGC.eOff));
	}
}
