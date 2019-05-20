package lineSetting_Stuff;

import java.awt.Color;

import component_Stuff.GraphicComponent;
import container_Stuff.AContainer;
import function_Data.Data_ColorCircle_Pen;
import function_Shape.Shape_Mover;
import global.ColorConstant;

@SuppressWarnings("serial")
public class PenColorSelectPanel extends AContainer {

	static int pixelWH = 52; 
	
	public PenColorSelectPanel() {
		super(pixelWH, pixelWH, 6, 5, 0, 0);
		this.setItemDraggable(false);

		for(Color c : ColorConstant.penColors) {
			this.addItem(makeColorSelectCircle(c));
		}
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new Data_ColorCircle_Pen(c, pixelWH));
		GC.addFunction(new Shape_Mover());
		return GC;
	}

}
