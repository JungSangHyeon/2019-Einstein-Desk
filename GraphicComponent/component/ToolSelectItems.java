package component;

import component_Stuff.GraphicComponent;
import function.Data_ToolSelector;
import function.Paint_IMG;
import function.Paint_showMouseOnMe;
import function.Paint_showSelectedTool;
import function.Shape_Mover;
import function_Stuff.AFunction;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class ToolSelectItems {

	public enum eToolSelectItem{
		eHandToolItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/hand.png")),
		eEraserToolItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/eraser.png")),
		eShapeToolItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/shape.png")),
		eBackItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/back.png")),
		eFrontItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/front.png")),
		eNewSlideItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/newSlide.png")),
		eOffItem(eTool.eHandTool.getTool(), new Paint_IMG("", "ToolBarImgs/off1.png")),
		;
		private ATool tool; private AFunction painter;
		private eToolSelectItem(ATool tool, AFunction painter) {
			this.tool=tool;
			this.painter=painter;
		}
		public ATool getTool() {return this.tool;}
		public AFunction getPainter() {return this.painter;}
	}
	
	public static GraphicComponent getGCItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(new Data_ToolSelector(item.getTool()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(item.getPainter());
		GC.addFunction(new Paint_showSelectedTool());
		GC.addFunction(new Paint_showMouseOnMe());
		GC.addFunction(new Shape_Mover());
		return GC;
	}
	
}
