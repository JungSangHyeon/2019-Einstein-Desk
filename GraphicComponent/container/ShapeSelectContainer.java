package container;

import container_Item.ShapeSelectItems;
import container_Item.ShapeSelectItems.eShapeSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ShapeSelectContainer extends AContainer{

	public ShapeSelectContainer() {
		super(100,100, 5, 1);
		this.setItemDraggable(false);
	
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectShape));
	}

}
