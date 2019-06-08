package testPanel;

import fPaint.FDummy;
import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;
import zStuff_GraphicComponent.GraphicComponent;

@SuppressWarnings("serial")
public class TestPanel extends GCPanel_LayoutPixel_Y {

	int w = 5;
	int h = 3;
	
	public TestPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(10, 10);
		this.setSize(w, h);
		this.setGCLocation(1600,100);
		this.saveClip();
		
		int dummyNum = 28;
		for(int i=0; i<dummyNum; i++) {
			GraphicComponent gc = GCCreator.create(ToolSelectGC.dummy);
			gc.addFunction(new FDummy(i));
			this.add(gc);
		}
	}
	
	public void resetFollowVector() {
		super.resetFollowVector();
		System.out.println("@@Test Panel Vector Change Test@@");
		for(int i=0; i<h; i++) {
			for(int v=0; v<w; v++) {
				FDummy d = (FDummy) itemVector.get(i*w+v).getInContainerGC().getFunctions().lastElement();
				System.out.print(d.getNum()+", ");
			}
			System.out.println();
		}
	}
	
}
