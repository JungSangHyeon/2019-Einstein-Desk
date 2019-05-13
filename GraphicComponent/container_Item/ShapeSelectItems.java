package container_Item;

import component_Stuff.GraphicComponent;
import painter.ImgPainter;
import painter_Stuff.AComponentPainter;
import processor.Mover;
import processor.ShapeSelector;
import processor.ToolSelector;
import shape_Stuff.AShape;
import shape_Stuff.eShape;

public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectItem(eShape.rect.getShape(), new ImgPainter("rect", "Icons/jake_22X22.txt")),
		eEllipseItem(eShape.ellipse.getShape(), new ImgPainter("ellipse", "Icons/jake_22X22.txt")),
		eSpeechItem(eShape.speech.getShape(), new ImgPainter("", "Icons/jake_22X22.txt")),
		eStar4Item(eShape.star4.getShape(), new ImgPainter("", "Icons/jake_22X22.txt")),
		eTriangleItem(eShape.triangle.getShape(), new ImgPainter("", "Icons/jake_22X22.txt")),
		eStraightLineItem(eShape.straightLine.getShape(), new ImgPainter("straightLine", "Icons/jake_22X22.txt")),
		eFreeLineItem(eShape.freeLine.getShape(), new ImgPainter("freeLine", "Icons/pen_48X48.txt")),
		
		ePolygonItem(eShape.polygon.getShape(), new ImgPainter("polygon", "Icons/jake_22X22.txt")),
		;
		
		private AShape shape;  private AComponentPainter painter;
		private eShapeSelectItem(AShape shape, AComponentPainter painter) {
			this.shape=shape;
			this.painter=painter;
		}
		public AShape getSelectShape() {return this.shape;}
		public AComponentPainter getPainter() {return this.painter;}
	}
	
	public static GraphicComponent getGCItem(eShapeSelectItem item) {//add tool Select Processor
		GraphicComponent GC = new GraphicComponent();
		GC.addProcessor(new ToolSelector(item.getSelectShape().getDrawer()));
		GC.addProcessor(new ShapeSelector(item.getSelectShape()));
		GC.addProcessor(new Mover());
		GC.addPainter(item.getPainter());
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		return GC;
	}
	
}
