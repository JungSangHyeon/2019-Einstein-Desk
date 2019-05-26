package GCPanel_ShapeSetting_Stuff;

import java.awt.Color;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;

@SuppressWarnings("serial")
public class PDRShapeSelectGCPanel extends GCPanel_LayoutPixel {

	public PDRShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setGCLocation(100, 100);
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 1);
		
		this.setItemDraggable(false);
		 
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eEllipseItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eSpeechItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eStar4Item));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eTriangleItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eStraightLineItem));
	}
}
