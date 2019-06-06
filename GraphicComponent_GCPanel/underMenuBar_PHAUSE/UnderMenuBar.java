package underMenuBar_PHAUSE;

import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;

@SuppressWarnings("serial")
public class UnderMenuBar extends GCPanel_LayoutPixel {

	public UnderMenuBar() {
		this.setPixelSize(48, 40);
		this.setPixelGap(0, 0);
		this.setSize(40, 1);
//		this.setGCLocation(0, 1080-40);
		this.setGCLocation(0, 1080-240);
		this.saveClip();
		
//		this.add(GCCreator.create(ToolSelectGC.slideOnFunctions));
	}
}
