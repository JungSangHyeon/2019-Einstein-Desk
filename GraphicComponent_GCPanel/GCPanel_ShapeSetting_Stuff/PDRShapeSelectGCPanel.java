package GCPanel_ShapeSetting_Stuff;

import java.awt.Color;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCEnum;
import zStuff_GraphicComponent.GCEnum.eGC;

@SuppressWarnings("serial")
public class PDRShapeSelectGCPanel extends GCPanel_LayoutPixel {

	public PDRShapeSelectGCPanel() {
		this.setFillColor(new Color(242,242,242));
		this.setPixelSize(52, 52);
		this.setPixelGap(0, 0);
		this.setSize(6, 2);
		
		this.setItemDraggable(false);
		 
		this.add(GCEnum.getGC(eGC.eRect));
		this.add(GCEnum.getGC(eGC.eEllipse));
		this.add(GCEnum.getGC(eGC.eSpeech));
		this.add(GCEnum.getGC(eGC.eStar4));
		this.add(GCEnum.getGC(eGC.eStarN));
		this.add(GCEnum.getGC(eGC.eTriangle));
		this.add(GCEnum.getGC(eGC.eStraightLine));
	}
}
