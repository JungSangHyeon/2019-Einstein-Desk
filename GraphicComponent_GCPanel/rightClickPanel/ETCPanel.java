package rightClickPanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import fImagePaint.FImageAndTextPaint_X;
import fPaint.FShadow;
import fPaint.FShowMouseOn;
import zStuff_Function.AFunction;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GCCreator;

public class ETCPanel extends GCPanel_LayoutPixel_Y{
	private static final long serialVersionUID = 3390225195290912805L;
	
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

	private void addItems() {
		for(AFunction[] item : items) {
			this.add(GCCreator.create(item));
		}
	}
	
	static int textSize = 12;
	public static AFunction[][] items = 
			{ 
					{ //zOrder_Top 
						new FImageAndTextPaint_X("맨 위로 보내기", textSize, "RightClickPanelImage/Top.png"), 
						new FShowMouseOn(), 
					},
					{ //zOrder_Front
						new FImageAndTextPaint_X("앞으로 보내기", textSize, "RightClickPanelImage/Front.png"), 
						new FShowMouseOn(), 
					},
					{ //zOrder_Hell 
						new FImageAndTextPaint_X("맨 뒤로 보내기", textSize, "RightClickPanelImage/Hell.png"), 
						new FShowMouseOn(), 
					},
					{ //zOrder_Back
						new FImageAndTextPaint_X("뒤로 보내기", textSize, "RightClickPanelImage/Back.png"), 
						new FShowMouseOn(), 
					},
			};
}
