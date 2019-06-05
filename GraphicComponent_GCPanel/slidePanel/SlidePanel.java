package slidePanel;

import java.awt.Color;
import java.awt.Graphics2D;

import slide.SlideManager;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GCPanel_LayoutPixel.Item;

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
		
		this.setShadow(false);
		this.setSeatNoticeColor(new Color(230,230,230));
		this.setPush(true);
		
		this.add(SlideManager.getSlideGC(0));
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		Item currentItem = this.getCurrentItem();
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(false);}
		super.paint(g2d);
		if(currentItem!=null) {currentItem.getInContainerGC().setBorderPaint(true);}
		if(this.getItems().size()<SlideManager.getSlideNum()) {
			this.getItems().clear();
			this.resetView();
			for(int i=0; i<SlideManager.getSlideNum(); i++) {
				this.add(SlideManager.getSlideGC(i));
			}
		}
	}

	@Override
	public void changeSeat() {
		super.changeSeat();
		
	}
}
