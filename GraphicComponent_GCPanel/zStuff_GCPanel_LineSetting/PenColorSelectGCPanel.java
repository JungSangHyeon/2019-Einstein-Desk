package zStuff_GCPanel_LineSetting;

import java.awt.Color;

import fGCDataModify.FMove_Item;
import fGlobalDataModify.FSetPenColorBTN;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

@SuppressWarnings("serial")
public class PenColorSelectGCPanel extends GCPanel_LayoutPixel_Y {

	int pixelWH = 52; 
	public PenColorSelectGCPanel() {
		this.setPixelSize(pixelWH, pixelWH);
		this.setPixelGap(0, 0);
		this.setSize(6, 5);
		this.setGCLocation(400,100);
		this.setItemDraggable(false);
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FSetPenColorBTN(c, pixelWH));
		GC.addFunction(new FMove_Item());
		return GC;
	}

	public void addItems() {
		for(Color c : ColorConstant.penColors) {this.add(makeColorSelectCircle(c));}
	}
}
