package GCPanel;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;
import component.ToolSelectItems;
import component.ToolSelectItems.eToolSelectItem;

@SuppressWarnings("serial")
public class ToolSelectGCPanel extends GCPanel_LayoutPixel {

	public ToolSelectGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(9, 1);
		
		this.setGCLocation(1920 - this.getWidth(), 0);
		
		this.setItemDraggable(false);

		this.add(ShapeSelectItems.getPenItem(eShapeSelectItem.eFreeLineItem));
		this.add(ShapeSelectItems.getHighlightItem(eShapeSelectItem.eFreeHighlightItem));
		
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eEraserToolItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eShapeToolItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eBackItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eFrontItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eNewSlideItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eOffItem));
	}
}
