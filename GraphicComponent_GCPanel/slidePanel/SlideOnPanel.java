package slidePanel;

import fPaint.FPanelOnOff;
import slide.SlidePanel;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class SlideOnPanel extends GCPanel_LayoutPixel_Y {

	public SlideOnPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(1, 1);
		this.setGCLocation(0, 0);
		this.saveClip();
		
		this.setItemDraggable(false);

		AFunction[] functions = {
				new FPanelOnOff(new SlidePanel()),
        };
		this.add(GCCreator.create(functions));
	}
}
