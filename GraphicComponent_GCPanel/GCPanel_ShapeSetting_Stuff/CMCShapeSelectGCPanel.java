package GCPanel_ShapeSetting_Stuff;

import java.awt.Color;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component_Stuff.GCEnum;
import component_Stuff.GCEnum.eGC;

@SuppressWarnings("serial")
public class CMCShapeSelectGCPanel extends GCPanel_LayoutPixel {

	public CMCShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setGCLocation(100, 100);
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 1);
		
		this.setItemDraggable(false);
		 
		this.add(GCEnum.getGC(eGC.ePolygon));
	}
}
