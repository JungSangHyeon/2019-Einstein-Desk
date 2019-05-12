package container;

import container_Item.ShapeSelectItems;
import container_Item.ToolSelectItems;
import container_Item.ShapeSelectItems.eShapeSelectItem;
import container_Item.ToolSelectItems.eToolSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ToolSelectContainer extends AContainer{

	public ToolSelectContainer() {
		super(100,100, eToolSelectItem.values().length,1);//숫자 세야 한다.
//		this.setItemDraggable(false);
		
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.eCMCShapeDrawToolItem));
		this.addItem(ToolSelectItems.getGCItem(eToolSelectItem.ePDRShapeDrawToolIteam));
		
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectShape));
	}

}
