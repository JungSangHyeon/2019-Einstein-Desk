package testPanel;

import fPaint.FDummy;
import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_X;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public class TestPanelX extends GCPanel_LayoutPixel_X {

	int w = 3;
	int h = 1;
	
	public TestPanelX() {
		this.setPixelSize(100,100);
		this.setPixelGap(10, 10);
		this.setSize(w, h);
		this.setGCLocation(1300,500);
		this.saveClip();
		
		int dummyNum = 28;
		for(int i=0; i<dummyNum; i++) {
			GraphicComponent gc = GCCreator.create(ToolSelectGC.dummy);
			gc.addFunction(new FDummy(i));
			this.add(gc);
		}
	}
	
	
}
