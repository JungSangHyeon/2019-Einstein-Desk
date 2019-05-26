package component;

import GCPanel.ShapeSettingGCPanel;
import component_Stuff.GraphicComponent;
import function_Data.Data_ToolSelector;
import function_Paint.Paint_GCShowUp;
import function_Paint.Paint_NormalIMG;
import function_Paint.Paint_showMouseOnMe;
import function_Paint.Paint_showSelectedTool;
import function_Shape.Shape_Mover;
import function_Stuff.eTool;
import function_System.System_Die;
import shape_Stuff.eShape;
import zFunction_Stuff.AFunction;

public class ToolSelectItems {

	public enum eToolSelectItem{
		eHandToolItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/hand.png")),//new Data_ToolSelector(item.getTool()) -> function
		eEraserToolItem(new Data_ToolSelector(eTool.eEraserTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/eraser.png")),
		eShapeToolItem(new Paint_GCShowUp(new ShapeSettingGCPanel()), new Paint_NormalIMG("", "ToolBarImgs/shape.png")),
		eBackItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/back.png")),
		eFrontItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/front.png")),
		eNewSlideItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/newSlide.png")),
		eOffItem(new System_Die(), new Paint_NormalIMG("", "ToolBarImgs/off1.png")),
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
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new Paint_showSelectedTool());
		GC.addFunction(new Paint_showMouseOnMe());
		return GC;
	}
	
}
