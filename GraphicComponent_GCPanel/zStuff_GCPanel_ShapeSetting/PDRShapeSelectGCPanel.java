package zStuff_GCPanel_ShapeSetting;

import java.awt.Color;

import graphicComponent.ShapeSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class PDRShapeSelectGCPanel extends GCPanel_LayoutPixel_Y {

	public PDRShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 2);
		
		this.setItemDraggable(false);
		 
		this.add(GCCreator.create(ShapeSelectGC.rectSelector));
		this.add(GCCreator.create(ShapeSelectGC.ellipseSelector));
		this.add(GCCreator.create(ShapeSelectGC.speechSelector));
		this.add(GCCreator.create(ShapeSelectGC.star4Selector));
		this.add(GCCreator.create(ShapeSelectGC.starNSelector));
		this.add(GCCreator.create(ShapeSelectGC.triangleSelector));
		this.add(GCCreator.create(ShapeSelectGC.straightLineSelector));
	}
}
