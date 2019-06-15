package rightClickPanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import fPaint.FShadow;
import graphicComponent.RightClickGC;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

public class ChoosePanel extends GCPanel_LayoutPixel_Y{
	private static final long serialVersionUID = -8183812910293145715L;

	int gapWithETCPanel = 14;
	
	public static AFunction[][] items = { 
			RightClickGC.FillColor, 
			RightClickGC.BorderColor, 
			RightClickGC.TextColor 
	};
	
	public ChoosePanel (Point mousePoint) {
		this.setPixelSize(42, 57);
		this.setPixelGap(3, 3);
		this.setSize(items.length, 1);
		this.setGCLocation(mousePoint.x, mousePoint.y - this.getHeight() - gapWithETCPanel);
		
		this.setBorderColor(new Color(198,198,198));
		this.setBackgroundColor(Color.WHITE);
		this.setBorderPaint(true);
		this.setborderThick(1);
		
		this.setItemDraggable(false);
		this.setShadow(false);

		this.addItems();
		
		Rectangle clip = this.getClip().getBounds();
		this.setClip(new Rectangle(clip.x, clip.y, clip.width, clip.height+1));
		
		this.addFunction(new FShadow());
	}
	
	private void addItems() {for(AFunction[] item : items) {this.add(GCCreator.create(item));}}
}
