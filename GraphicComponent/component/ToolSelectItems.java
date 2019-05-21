package component;

import component_Stuff.GraphicComponent;
import function_Data.Data_ToolSelector;
import function_Paint.Paint_IMG;
import function_Paint.Paint_showMouseOnMe;
import function_Paint.Paint_showSelectedTool;
import function_Shape.Shape_Mover;
import function_Stuff.eTool;
import function_System.System_Die;
import shape_Stuff.eShape;
import zFunction_Stuff.AFunction;

public class ToolSelectItems {

	public enum eToolSelectItem{
		eHandToolItem(new Data_ToolSelector(eTool.eHandTool.getTool()), new Paint_IMG("", "ToolBarImgs/hand.png")),//new Data_ToolSelector(item.getTool()) -> function
		eEraserToolItem(new Data_ToolSelector(eTool.eEraserTool.getTool()), new Paint_IMG("", "ToolBarImgs/eraser.png")),
		eShapeToolItem(new Data_ToolSelector(eTool.eHandTool.getTool()), new Paint_IMG("", "ToolBarImgs/shape.png")),
		eBackItem(new Data_ToolSelector(eTool.eHandTool.getTool()), new Paint_IMG("", "ToolBarImgs/back.png")),
		eFrontItem(new Data_ToolSelector(eTool.eHandTool.getTool()), new Paint_IMG("", "ToolBarImgs/front.png")),
		eNewSlideItem(new Data_ToolSelector(eTool.eHandTool.getTool()), new Paint_IMG("", "ToolBarImgs/newSlide.png")),
		eOffItem(new System_Die(), new Paint_IMG("", "ToolBarImgs/off1.png")),
		;
		private AFunction tool; private AFunction painter;
		private eToolSelectItem(AFunction tool, AFunction painter) {
			this.tool=tool;
			this.painter=painter;
		}
		public AFunction getFunction() {return this.tool;}
		public AFunction getPainter() {return this.painter;}
	}
	
	public static GraphicComponent getGCItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(item.getFunction());//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getShape());
		GC.addFunction(new Paint_showSelectedTool());
		GC.addFunction(new Paint_showMouseOnMe());
		GC.addFunction(new Shape_Mover());
		return GC;
	}
	
}
