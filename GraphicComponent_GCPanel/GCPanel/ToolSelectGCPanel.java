package GCPanel;

import java.awt.Color;
import java.awt.Graphics2D;

import GCPanel_LayoutPixel_Stuff.GCPanel_LayoutPixel;
import GCPanel_LayoutPixel_Stuff.Item;
import component.ShapeSelectItems;
import component.ShapeSelectItems.eShapeSelectItem;
import component.ToolSelectItems;
import component.ToolSelectItems.eToolSelectItem;

@SuppressWarnings("serial")
public class ToolSelectGCPanel extends GCPanel_LayoutPixel {

	public ToolSelectGCPanel() {
		this.setPixelSize(48, 48);
		this.setPixelGap(0, 0);
		this.setSize(5, 1);
		
		this.setGCLocation(1920 - this.getWidth() - 48*4 -1, 0);
		
		this.setItemDraggable(false);

		this.add(ShapeSelectItems.getPenItem(eShapeSelectItem.eFreeLineItem));
		this.add(ShapeSelectItems.getHighlightItem(eShapeSelectItem.eFreeHighlightItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eEraserToolItem));
		this.add(ToolSelectItems.getShapeSelectItem(eToolSelectItem.eShapeToolItem));
		this.add(ToolSelectItems.getGCItem(eToolSelectItem.eHandToolItem));
		
		for(Item item : this.getItems()) {
			for(Item connectItem : this.getItems()) {
				if(connectItem.getInContainerGC()!=item.getInContainerGC()) {
					item.getInContainerGC().addConnectGC(connectItem.getInContainerGC());
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		g2d.setColor(new Color(138, 138, 138));
		g2d.fillRect(this.getX()+this.getWidth(), this.getY(), 1, this.getHeight());
	}
}
