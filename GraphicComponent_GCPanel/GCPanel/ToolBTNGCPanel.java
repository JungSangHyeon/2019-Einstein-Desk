package GCPanel;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component_Stuff.GCEnum;
import component_Stuff.GCEnum.eGC;

@SuppressWarnings("serial")
public class ToolBTNGCPanel extends GCPanel_LayoutPixel {

	public ToolBTNGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(3, 1);
		
		this.setGCLocation(1920 - this.getWidth() - 48, 0);
		
		this.setItemDraggable(false);

		this.add(GCEnum.getGC(eGC.eUndo));
		this.add(GCEnum.getGC(eGC.eRedo));
		this.add(GCEnum.getGC(eGC.eNewSlide));
//		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eOffItem));
		this.add(GCEnum.getGC(eGC.eImage));
	}
}
