package container;

import container_Item.ShapeSelectItems;
import container_Item.ShapeSelectItems.eShapeSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ShapeSelector extends AContainer{

	public ShapeSelector() {
		super(100,100, 5, 1);
		this.setItemDraggable(false);
	
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectShape));
	}

}
