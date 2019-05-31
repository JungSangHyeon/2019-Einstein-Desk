package GCPanel_LineSetting_Stuff;

import java.awt.Color;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import component_Stuff.GraphicComponent;
import dataModifyFunction.FSetPenColorBTN;
import function_Shape.Shape_Mover;
import global.ColorConstant;
import shape_Stuff.eShape;

@SuppressWarnings("serial")
public class PenColorSelectGCPanel extends GCPanel_LayoutPixel {

	int pixelWH = 52; 
	public PenColorSelectGCPanel() {
		this.setPixelSize(pixelWH, pixelWH);
		this.setPixelGap(0, 0);
		this.setSize(6, 5);
		this.setItemDraggable(false);
		for(Color c : ColorConstant.penColors) {
			this.add(makeColorSelectCircle(c));
		}
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FSetPenColorBTN(c, pixelWH));
		GC.addFunction(new Shape_Mover());
		return GC;
	}
}
