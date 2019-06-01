package GCPanel_ShapeSetting_Stuff;

import java.awt.Color;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCEnum;
import zStuff_GraphicComponent.GCEnum.eGC;

@SuppressWarnings("serial")
public class CMCShapeSelectGCPanel extends GCPanel_LayoutPixel {

	public CMCShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 1);
		
		this.setItemDraggable(false);
		 
		this.add(GCEnum.getGC(eGC.ePolygon));
	}
}
