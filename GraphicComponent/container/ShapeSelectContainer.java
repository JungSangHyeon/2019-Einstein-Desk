package container;

import container_Item.ShapeSelectItems;
import container_Item.ShapeSelectItems.eShapeSelectItem;
import container_Stuff.AContainer;

@SuppressWarnings("serial")
public class ShapeSelectContainer extends AContainer{

	public ShapeSelectContainer() {
		super(48,48, 5, 8);//h는 알어서 늘어남.
//		this.setItemDraggable(false);
		this.setAutoChangeSeat(false);
		this.setGapW(0);
		this.setGapH(0);
		
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eEllipseItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eSpeechItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eStar4Item));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eTriangleItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.eStraightLineItem));
		this.addItem(ShapeSelectItems.getGCItem(eShapeSelectItem.ePolygonItem));
	}

}
