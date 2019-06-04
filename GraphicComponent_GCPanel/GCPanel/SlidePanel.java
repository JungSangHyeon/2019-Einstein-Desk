package GCPanel;

import java.awt.Color;

import slide.SlideManager;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;

@SuppressWarnings("serial")
public class SlidePanel extends GCPanel_LayoutPixel {

	public SlidePanel() {
		this.setPixelSize(254, 144);
		this.setPixelGap(21, 18);
		this.setSize(1, 6);
		this.setGCLocation(0, 48);
		this.saveClip();
		
		this.setBackgroundColor(new Color(230,230,230));
		this.setBorderColor(new Color(191,191,191));
		this.setBorderPaint(true);
		
		this.setItemDraggable(false);

		this.add(SlideManager.getSlideGC(0));
	}
	
}
