package zStuff_GCPanel_LineSetting;

import java.awt.Color;

import fGCDataModify.FMove;
import fGlobalDataModify.FSetHighLightColorBTN;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

@SuppressWarnings("serial")
public class HighlightColorSelectGCPanel extends GCPanel_LayoutPixel {

	int pixelWH = 52; 
	public HighlightColorSelectGCPanel() {
		this.setPixelSize(pixelWH, pixelWH);
		this.setPixelGap(0, 0);
		this.setSize(6, 1);
		this.setItemDraggable(false);
		for(Color c : ColorConstant.highlightColors) {
			this.add(makeColorSelectCircle(c));
		}
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FSetHighLightColorBTN(c, pixelWH));
		GC.addFunction(new FMove());
		return GC;
	}
}
