package container_Item;

import component_Stuff.GraphicComponent;
import painter.ImgPainter;
import painter_Stuff.AComponentPainter;
import shape.RectShape;
import shape_Stuff.AShape;

public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectShape(new RectShape(), new ImgPainter("", "Icons/jake_22X22.txt")),
		;
		
		private AShape shape;  private AComponentPainter painter;
		private eShapeSelectItem(AShape shape, AComponentPainter painter) {
			this.shape=shape;
			this.painter=painter;
		}
		public AShape getShape() {return this.shape;}
		public AComponentPainter getPainter() {return this.painter;}
	}
	
	public static GraphicComponent getGCItem(eShapeSelectItem item) {//add tool Select Processor
		GraphicComponent GC = new GraphicComponent();
		GC.addPainter(item.getPainter());
		GC.setBasicPaintNeed(false);
		return GC;
	}
	
}
