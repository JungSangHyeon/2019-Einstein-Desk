package zStuff_GCPanel_ShapeSetting;

import java.awt.Color;

import graphicComponent.ShapeSelectGC;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class CMCShapeSelectGCPanel extends GCPanel_LayoutPixel_Y {

	public static AFunction[][] items = { 
		ShapeSelectGC.polygonSelector,
	};
	
	public CMCShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 1);
		
		this.setItemDraggable(false);
	}
	
	public void addItems() {for(AFunction[] item : items) {this.add(GCCreator.create(item));}}
}
