package container_Item;

import component_Stuff.GraphicComponent;
import painter.ImgPainter;
import painter_Stuff.AComponentPainter;
import processor.Mover;
import processor.ToolSelector;
import tool_Stuff.ATool;
import tool_Stuff.eTool;

public class ToolSelectItems {

	public enum eToolSelectItem{
		ePDRShapeDrawToolIteam(eTool.ePDRShapeDrawTool.getTool(), new ImgPainter("", "Icons/cata_22X22.txt")),
		eCMCShapeDrawToolItem(eTool.eCMCShapeDrawTool.getTool(), new ImgPainter("", "Icons/cata_22X22.txt")),
		eHandToolItem(eTool.eHandTool.getTool(), new ImgPainter("", "Icons/jake_22X22.txt")),
		;
		private ATool tool; private AComponentPainter painter;
		private eToolSelectItem(ATool tool, AComponentPainter painter) {
			this.tool=tool;
			this.painter=painter;
		}
		public ATool getTool() {return this.tool;}
		public AComponentPainter getPainter() {return this.painter;}
	}
	
	public static GraphicComponent getGCItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.addPainter(item.getPainter());
		GC.addProcessor(new ToolSelector(item.getTool()));
		GC.addProcessor(new Mover());
		GC.setBasicPaintNeed(false);
		return GC;
	}
	
}
