package container_Item;

import component_Stuff.GraphicComponent;
import painter.ImgPainter;
import painter_Stuff.AComponentPainter;
import processor.ShapeSelector;
import processor.ToolSelector;
import shape_Stuff.AShape;
import shape_Stuff.eShape;

public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectShape(eShape.rect.getShape(), new ImgPainter("", "Icons/jake_22X22.txt")),
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
		GC.addPainter(item.getPainter());
		GC.setBasicPaintNeed(false);
		return GC;
	}
	
}
