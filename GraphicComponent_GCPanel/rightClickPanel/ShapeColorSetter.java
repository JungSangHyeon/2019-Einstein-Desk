package rightClickPanel;

import java.awt.Color;
import java.awt.Rectangle;

import fGCDataModify.FSetShapeBorderColorBTN;
import fGCDataModify.FSetShapeFillColorBTN;
import fGCDataModify.FSetShapeTextColorBTN;
import fPaint.FInnerRect;
import zStuff_GCPanel_LayoutPixel.GCPanel_LayoutPixel_Y;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

public class ShapeColorSetter extends GCPanel_LayoutPixel_Y{
	private static final long serialVersionUID = 3390225195290912805L;
	
	int pixelWH = 13;
	Color mouseOnColor = new Color(242, 148, 54);
	int mouseOnBorder = 2;
	Target nowTarget;
	
	public enum Target{Fill, Border, Text};
	
	public ShapeColorSetter (Target target) {
		nowTarget = target;
		this.setPixelSize(pixelWH, pixelWH);
		this.setPixelGap(4, 0);
		
		this.setBackgroundColor(Color.WHITE);
		
		this.setItemDraggable(false);
		this.setShadow(false);

		Rectangle clip = this.getClip().getBounds();
		this.setClip(new Rectangle(clip.x, clip.y, clip.width, clip.height));
	}

	public GraphicComponent makeColorSelectCircle(Color c) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.setAShape(eShape.rect.getAShape());
		
		addFunctionFollowTarget(GC, c);
		GC.addFunction(new FInnerRect(mouseOnColor, mouseOnBorder));
		return GC;
	}

	private void addFunctionFollowTarget(GraphicComponent gC, Color c) {
		if(nowTarget == Target.Fill) {gC.addFunction(new FSetShapeFillColorBTN(c));}
		else if(nowTarget == Target.Border) {gC.addFunction(new FSetShapeBorderColorBTN(c));}
		else if(nowTarget == Target.Text) {gC.addFunction(new FSetShapeTextColorBTN(c));}
	}

	public void addItems(Color[] colorArr) {
		for(Color c : colorArr) {this.add(makeColorSelectCircle(c));}
	}
	
}
