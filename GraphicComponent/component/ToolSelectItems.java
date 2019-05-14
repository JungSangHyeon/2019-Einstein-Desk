package component;

import component_Stuff.GraphicComponent;
import function.Paint_IMG;
import function.Shape_Mover;
import function.Data_ToolSelector;
import function.Paint_showSelected;
import function_Stuff.AFunction;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class ToolSelectItems {

	public enum eToolSelectItem{
		ePDRShapeDrawToolIteam(eTool.ePDRShapeDrawTool.getTool(), new Paint_IMG("", "Icons/jake_22X22.txt")),
		eCMCShapeDrawToolItem(eTool.eCMCShapeDrawTool.getTool(), new Paint_IMG("", "Icons/jake_22X22.txt")),
		eHandToolItem(eTool.eHandTool.getTool(), new Paint_IMG("", "Icons/jake_22X22.txt")),
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
		
		GC.addFunction(new Data_ToolSelector(item.getTool()));
		GC.addFunction(new Paint_showSelected());
		GC.addFunction(item.getPainter());
		GC.addFunction(new Shape_Mover());
		return GC;
	}
	
}
