package container;

import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;
import component.ToolSelectItems;
import component.ToolSelectItems.eToolSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ToolSelectContainer extends AContainer {

	public ToolSelectContainer() {
		super(48, 48, 9, 1, 0, 0);
//		this.setItemDraggable(false);

		this.addItem(ShapeSelectItems.getLineItem(eShapeSelectItem.eFreeLineItem));
		this.addItem(ShapeSelectItems.getLineItem(eShapeSelectItem.eFreeHighlightItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eEraserToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eShapeToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eBackItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eFrontItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eNewSlideItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eOffItem));
		
	}

}
