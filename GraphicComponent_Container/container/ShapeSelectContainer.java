package container;

import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ShapeSelectContainer extends AContainer {

	public ShapeSelectContainer() {
		super(48, 48, 5, 8, 0, 0);
		 this.setItemDraggable(false);
		 
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eEllipseItem));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eSpeechItem));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eStar4Item));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eTriangleItem));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eStraightLineItem));
		 this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.ePolygonItem));
	}

}
