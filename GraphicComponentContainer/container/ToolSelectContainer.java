package container;

import component.ShapeSelectItems;
import component.ToolSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;
import component.ToolSelectItems.eToolSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ToolSelectContainer extends AContainer{

	public ToolSelectContainer() {
		super(48,48, 5, 1);//숫자 세야 한다.
		this.setItemDraggable(false);
		this.setAutoChangeSeat(false);
		this.setGapW(0);
		this.setGapH(0);
		
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eCMCShapeDrawToolItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eFreeLineItem));
	}

}
