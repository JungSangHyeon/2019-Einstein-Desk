package component;

import component_Stuff.GraphicComponent;
import function.Data_ShapeSelect;
import function.Data_ToolSelector;
import function.Paint_IMG;
import function.Paint_penColorBGPaint;
import function.Paint_showMouseOnMe;
import function.Paint_showSelectedTool;
import function.Shape_Mover;
import function_Stuff.AFunction;
import shape_Stuff.AShape;
import shape_Stuff.eShape;


public class ShapeSelectItems {

	public enum eShapeSelectItem{
		eRectItem(eShape.rect.getShape(), new Paint_IMG("rect", "Icons/jake_22X22.txt")),
		eEllipseItem(eShape.ellipse.getShape(), new Paint_IMG("ellipse", "Icons/jake_22X22.txt")),
		eSpeechItem(eShape.speech.getShape(), new Paint_IMG("", "Icons/jake_22X22.txt")),
		eStar4Item(eShape.star4.getShape(), new Paint_IMG("", "Icons/jake_22X22.txt")),
		eTriangleItem(eShape.triangle.getShape(), new Paint_IMG("", "Icons/jake_22X22.txt")),
		eStraightLineItem(eShape.straightLine.getShape(), new Paint_IMG("straightLine", "Icons/jake_22X22.txt")),
		ePolygonItem(eShape.polygon.getShape(), new Paint_IMG("polygon", "Icons/jake_22X22.txt")),
		
		eFreeLineItem(eShape.freeLine.getShape(), new Paint_IMG("", "ToolBarImgs/pen.png")),
		eFreeHighlightItem(eShape.freeLine.getShape(), new Paint_IMG("", "ToolBarImgs/highlight.png")),
		;
		
		private AShape shape;  private AFunction function;
		private eShapeSelectItem(AShape shape, AFunction painter) {
			this.shape=shape;
			this.function=painter;
		}
		public AShape getSelectShape() {return this.shape;}
		public AFunction getPainter() {return this.function;}
	}
	
	public static GraphicComponent getGCItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new Data_ToolSelector(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new Data_ShapeSelect(item.getSelectShape()));
		GC.addFunction(item.getPainter());
		GC.addFunction(new Paint_showMouseOnMe());
		GC.addFunction(new Shape_Mover());
		return GC;
	}
	
	public static GraphicComponent getLineItem(eShapeSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new Data_ToolSelector(item.getSelectShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new Data_ShapeSelect(item.getSelectShape()));
		GC.addFunction(new Paint_penColorBGPaint());
		GC.addFunction(item.getPainter());
		GC.addFunction(new Paint_showSelectedTool());
		GC.addFunction(new Paint_showMouseOnMe());
		GC.addFunction(new Shape_Mover());
		return GC;
	}
}
