package testPanel;

import fPaint.FDummy;
import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public class TestPanel extends GCPanel_LayoutPixel {

	public TestPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(10, 10);
		this.setSize(5, 3);
		this.setGCLocation(1600,100);
		this.saveClip();
//		this.setItemDraggable(false);

//		int dummyNum = 28;
//		for(int i=0; i<dummyNum; i++) {
//			this.add(GCCreator.create(ToolSelectGC.dummy));
//		}
		
		int dummyNum = 28;
		for(int i=0; i<dummyNum; i++) {
			GraphicComponent gc = GCCreator.create(ToolSelectGC.dummy);
			gc.addFunction(new FDummy(i));
			this.add(gc);
		}
	}
}
