package lineSetting_Stuff;

import java.awt.Color;

import component_Stuff.GraphicComponent;
import data.GCPanel_LayoutPixel;
import function_Data.Data_ColorCircle_Highlight;
import function_Shape.Shape_Mover;
import global.ColorConstant;
import shape_Stuff.eShape;

@SuppressWarnings("serial")
public class HighlightColorSelectPanelTESTTTTTT extends GCPanel_LayoutPixel {

	static int pixelWH = 52; 
	
	public HighlightColorSelectPanelTESTTTTTT() {
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
		GC.addFunction(new Data_ColorCircle_Highlight(c, pixelWH));
		GC.addFunction(new Shape_Mover());
		return GC;
	}
}
