package container;

import painter.ImgPainter;
import processor.DragAndDropProcessor;
import processor.Mover;
import processor.ToolSelector;
import stuff_Component.AComponentPainter;
import stuff_Component.GraphicComponent;
import stuff_Container.AContainer;
import tool.HandTool;
import tool.Make2PointShapeTool;
import toolStuff.ATool;

@SuppressWarnings("serial")
public class ToolBar extends AContainer{

	public enum eTool{
		eMake2PointShapeTool(new Make2PointShapeTool(), new ImgPainter("", "Icons/cata_22X22.txt")),
		eHandTool(new HandTool(), new ImgPainter("", "Icons/jake_22X22.txt")),
		;
		private ATool tool; private AComponentPainter painter;
		private eTool(ATool tool, AComponentPainter painter) {
			this.tool=tool;
			this.painter=painter;
		}
		public ATool getTool() {return this.tool;}
		public AComponentPainter getPainter() {return this.painter;}
	}
	
	public ToolBar() {
		super(100,100, eTool.values().length,1);
		
		GraphicComponent GCData;
		for(eTool tool : eTool.values()) {
			GCData = new GraphicComponent();
			GCData.addPainter(tool.getPainter());
			GCData.addProcessor(new ToolSelector(tool.getTool()));
			GCData.addProcessor(new Mover());
			GCData.addProcessor(new DragAndDropProcessor());
			GCData.setBasicPaintNeed(false);
			this.addItem(GCData);
		}
		
	}

}
