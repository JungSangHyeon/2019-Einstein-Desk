package GCPanel;

import java.awt.Color;
import java.awt.Graphics2D;

import graphicComponent.ToolSelectGC;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel;
import zStuff_GraphicComponent.GCCreator;

@SuppressWarnings("serial")
public class ToolSelectGCPanel extends GCPanel_LayoutPixel {

	public ToolSelectGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(5, 1);
		this.setGCLocation(1920 - this.getWidth() - 48*4 -1, 0);
		this.saveClip();
		
		this.setItemDraggable(false);

		this.add(GCCreator.create(ToolSelectGC.penSelector));
		this.add(GCCreator.create(ToolSelectGC.highlightSelector));
		this.add(GCCreator.create(ToolSelectGC.eraserToolSelector));
		this.add(GCCreator.create(ToolSelectGC.shapeToolSelector));
		this.add(GCCreator.create(ToolSelectGC.handToolSelector));
		this.add(GCCreator.create(ToolSelectGC.canvasHandSelector));
		this.add(GCCreator.create(ToolSelectGC.connectSelector));
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		g2d.setColor(new Color(138, 138, 138));
		g2d.fillRect(this.getX()+this.getWidth(), this.getY(), 1, 48);
	}
}
