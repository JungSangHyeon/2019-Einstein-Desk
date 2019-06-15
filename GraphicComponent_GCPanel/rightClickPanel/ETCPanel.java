package rightClickPanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import fPaint.FShadow;
import graphicComponent.RightClickGC;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

public class ETCPanel extends GCPanel_LayoutPixel_Y{
	private static final long serialVersionUID = 3390225195290912805L;
	
	public static AFunction[][] items = { 
			RightClickGC.Copy,
			RightClickGC.Paste,
			RightClickGC.Group,
			RightClickGC.UnGroup,
			RightClickGC.Top,
			RightClickGC.Front,
			RightClickGC.Hell,
			RightClickGC.Back
	};
	
	public ETCPanel (Point mousePoint) {
		this.setPixelSize(180, 28);
		this.setPixelGap(0, 0);
		this.setSize(1, items.length);
		this.setGCLocation(mousePoint.x, mousePoint.y);//BOOK BOOk
		
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
