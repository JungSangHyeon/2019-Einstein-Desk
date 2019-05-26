package data;

import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;

public class testContainer1 extends GCPanel_LayoutPixel{
	private static final long serialVersionUID = 3141453841486592808L;

	public testContainer1() {
		
		this.setPixelSize(80, 80);
		this.setPixelGap(10, 10);
		
		this.setLocation(500,100);
		this.setSize(4,2);
		
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
		this.add(ShapeSelectItems.getGCItem(eShapeSelectItem.eRectItem));
	}
}
