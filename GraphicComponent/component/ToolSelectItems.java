package component;

import GCPanel.ShapeSettingGCPanel;
import component_Stuff.GraphicComponent;
import dataModifyFunction.FRedo;
import dataModifyFunction.FSetShape;
import dataModifyFunction.FSetTool;
import dataModifyFunction.FUndo;
import function_Paint.FGCpeekaboo;
import function_Paint.Paint_NormalIMG;
import function_Paint.FPaintMasterIfMouseOn;
import function_Paint.FShowSelected;
import function_Stuff.eTool;
import function_System.FKillSystem;
import imgLoad.ImgLoad;
import shape_Stuff.eShape;
import zFunction_Stuff.AFunction;

public class ToolSelectItems {

	public enum eToolSelectItem{
		eHandToolItem(new FSetTool(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/hand.png")),//new Data_ToolSelector(item.getTool()) -> function
		eEraserToolItem(new FSetTool(eTool.eEraserTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/eraser.png")),
		eShapeToolItem(new FGCpeekaboo(new ShapeSettingGCPanel()), new Paint_NormalIMG("", "ToolBarImgs/shape.png")),
		eBackItem(new FUndo(), new Paint_NormalIMG("", "ToolBarImgs/back.png")),
		eFrontItem(new FRedo(), new Paint_NormalIMG("", "ToolBarImgs/front.png")),
		eNewSlideItem(new ImgLoad(), new Paint_NormalIMG("", "ToolBarImgs/newSlide.png")),
//		eNewSlideItem(new Data_ToolSelector(eTool.eHandTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/newSlide.png")),
		eImageItem(new ImgLoad(), new Paint_NormalIMG("", "ToolBarImgs/Image.png")),
		eOffItem(new FKillSystem(), new Paint_NormalIMG("", "ToolBarImgs/off1.png")),
		eCanvasTool(new FSetTool(eTool.eCanvasTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/Canvas.png")),
		eConnectTool(new FSetTool(eTool.eConnectTool.getATool()), new Paint_NormalIMG("", "ToolBarImgs/Connect.png")),
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
		GC.addFunction(new FPaintMasterIfMouseOn());
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
		GC.addFunction(new FPaintMasterIfMouseOn());
		return GC;
	}
	
	public static GraphicComponent getNoSelectGCItem(eToolSelectItem item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		
		GC.addFunction(item.getFunction());//순서 중요함. 페인트의 경우 덧 그려짐
		GC.addFunction(item.getPainter());
		GC.setAShape(eShape.rect.getAShape());
		GC.addFunction(new FPaintMasterIfMouseOn());
		return GC;
	}
	
}
