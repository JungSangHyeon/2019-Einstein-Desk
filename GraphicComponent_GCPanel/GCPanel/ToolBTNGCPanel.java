package GCPanel;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component.ToolSelectItems;
import component.ToolSelectItems.eToolSelectItem;

@SuppressWarnings("serial")
public class ToolBTNGCPanel extends GCPanel_LayoutPixel {

	public ToolBTNGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(3, 1);
		
		this.setGCLocation(1920 - this.getWidth() - 48, 0);
		
		this.setItemDraggable(false);

		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eBackItem));
		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eFrontItem));
		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eNewSlideItem));
//		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eOffItem));
		this.add(ToolSelectItems.getNoSelectGCItem(eToolSelectItem.eImageItem));
	}
}
