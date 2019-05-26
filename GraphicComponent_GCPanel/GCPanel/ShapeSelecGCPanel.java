package GCPanel;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;

@SuppressWarnings("serial")
public class ShapeSelecGCPanel extends GCPanel_LayoutPixel {

	public ShapeSelecGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(10, 10);
		this.setSize(5, 8);
		
		this.setItemDraggable(false);
		 
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eEllipseItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eSpeechItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eStar4Item));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eTriangleItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eStraightLineItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.ePolygonItem));
	}

}
