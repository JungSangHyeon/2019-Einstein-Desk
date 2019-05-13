package container;

import container_Item.ShapeSelectItems;
import container_Item.ToolSelectItems;
import container_Item.ShapeSelectItems.eShapeSelectItem;
import container_Item.ToolSelectItems.eToolSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ToolSelectContainer extends AContainer{

	public ToolSelectContainer() {
		super(48,48, eToolSelectItem.values().length,1);//숫자 세야 한다.
//		this.setItemDraggable(false);
		this.setAutoChangeSeat(false);
		this.setGapW(0);
		this.setGapH(0);
		
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eFreeLineItem));
	}

}
