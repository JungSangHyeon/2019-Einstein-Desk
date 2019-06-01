package component;

import GCPanel.ShapeSettingGCPanel;
import component_Stuff.GraphicComponent;
import fGlobalDataModify.FRedo;
import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import fGlobalDataModify.FUndo;
import fImagePaint.FImageNormalPaint;
import fPaint.FGCpeekaboo;
import fPaint.FShowMouseOn;
import fPaint.FShowSelected;
import fSystem.FKillSystem;
import function_Stuff.eTool;
import imgLoad.ImgLoad;
import shape_Stuff.eShape;
import zStuff_Function.AFunction;

public class ToolSelectItems {

	public enum eToolSelectItem{
		eHandToolItem(new FSetTool(eTool.eHandTool.getATool()), new FImageNormalPaint("ToolBarImgs/hand.png")),//new Data_ToolSelector(item.getTool()) -> function
		eEraserToolItem(new FSetTool(eTool.eEraserTool.getATool()), new FImageNormalPaint("ToolBarImgs/eraser.png")),
		eShapeToolItem(new FGCpeekaboo(new ShapeSettingGCPanel()), new FImageNormalPaint("ToolBarImgs/shape.png")),
		eBackItem(new FUndo(), new FImageNormalPaint("ToolBarImgs/back.png")),
		eFrontItem(new FRedo(), new FImageNormalPaint("ToolBarImgs/front.png")),
		eNewSlideItem(new ImgLoad(), new FImageNormalPaint("ToolBarImgs/newSlide.png")),
//		eNewSlideItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/newSlide.png")),
		eImageItem(new ImgLoad(), new FImageNormalPaint("ToolBarImgs/Image.png")),
		eOffItem(new FKillSystem(), new FImageNormalPaint("ToolBarImgs/off1.png")),
		eCanvasTool(new FSetTool(eTool.eCanvasTool.getATool()), new FImageNormalPaint("ToolBarImgs/Canvas.png")),
		eConnectTool(new FSetTool(eTool.eConnectTool.getATool()), new FImageNormalPaint("ToolBarImgs/Connect.png")),
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
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FShowMouseOn());
		return GC;
	}
	
	public static GraphicComponent getShapeSelectItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FSetTool(eShape.rect.getAShape().getDrawer()));//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(new FSetShape(eShape.rect.getAShape()));
		GC.addFunction(item.getFunction());//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(item.getPainter());
		GC.addFunction(new FShowSelected());
		GC.addFunction(new FShowMouseOn());
		return GC;
	}
	
	public static GraphicComponent getNoSelectGCItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(item.getFunction());//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FShowMouseOn());
		return GC;
	}
	
}
