package lineSetting_Stuff;

import java.awt.Color;

import component_Stuff.GraphicComponent;
import container_Stuff.AContainer;
import function.Data_ColorCircle_Highlight;
import function.Shape_Mover;
import global.ColorConstant;

@SuppressWarnings("serial")
public class HighlightColorSelectPanel extends AContainer {

	static int pixelWH = 52; 
	
	public HighlightColorSelectPanel() {
		super(pixelWH, pixelWH, 6, 1, 0, 0);
		this.setItemDraggable(false);

		for(Color c : ColorConstant.highlightColors) {
			this.addItem(makeColorSelectCircle(c));
		}
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new Data_ColorCircle_Highlight(c, pixelWH));
		GC.addFunction(new Shape_Mover());
		return GC;
	}

}
