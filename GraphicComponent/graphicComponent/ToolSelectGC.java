package graphicComponent;

import GCPanel.HighlightSettingGCPanel;
import GCPanel.PenSettingGCPanel;
import GCPanel.ShapeSettingGCPanel;
import GCPanel.SlidePanel;
import fGlobalDataModify.FRedo;
import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import fGlobalDataModify.FUndo;
import fImagePaint.FImageNormalPaint;
import fPaint.FGCpeekaboo;
import fPaint.FPaintMasterWithHighLightColor;
import fPaint.FPaintMasterWithPenColor;
import fPaint.FPanelOnOff;
import fPaint.FShowMouseOn;
import fPaint.FShowSelected;
import fSystem.FKillSystem;
import imgLoad.ImgLoad;
import saveAsImage.SaveAsImage;
import slide.newSlide;
import zStuff_Function.AFunction;
import zStuff_Shape.eShape;
import zStuff_Tool.eTool;

public class ToolSelectGC {
	public static AFunction[] //add Functions Here
			handToolSelector = {
					new FSetTool(eTool.eHandTool.getATool()), 
					new FImageNormalPaint("ToolBarImgs/hand.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			eraserToolSelector = {
					new FSetTool(eTool.eEraserTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/eraser.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			shapeToolSelector = {
					new FSetTool(eShape.rect.getAShape().getDrawer()),
					new FSetShape(eShape.rect.getAShape()),
					new FImageNormalPaint("ToolBarImgs/shape.png"),
					new FShowSelected(),
					new FGCpeekaboo(new ShapeSettingGCPanel()),
					new FShowMouseOn()
			},
			undo = {
					new FUndo(),
					new FImageNormalPaint("ToolBarImgs/back.png"),
					new FShowMouseOn()
			},
			redo = {
					new FRedo(),
					new FImageNormalPaint("ToolBarImgs/front.png"),
					new FShowMouseOn()
			},
			newSlide = {
					new newSlide(),
					new FImageNormalPaint("ToolBarImgs/newSlide.png"),
					new FShowMouseOn()
			},
			imageLoad = {
					new ImgLoad(),
					new FImageNormalPaint("ToolBarImgs/Image.png"),
					new FShowMouseOn()
			},
			saveAsImage = {
					new SaveAsImage(),
					new FImageNormalPaint("ToolBarImgs/SaveCanvas.png"),
					new FShowMouseOn()
			},
			off = {
					new FKillSystem(),
					new FImageNormalPaint("ToolBarImgs/off1.png"),
					new FShowMouseOn()
			},
			canvasHandSelector = {
					new FSetTool(eTool.eCanvasTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/Canvas.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			connectSelector = {
					new FSetTool(eTool.eConnectTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/Connect.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			penSelector = {
					new FSetTool(eShape.pen.getAShape().getDrawer()),
					new FSetShape(eShape.pen.getAShape()),
					new FPaintMasterWithPenColor(),
					new FImageNormalPaint("ToolBarImgs/pen.png"),
					new FShowSelected(),
					new FGCpeekaboo(new PenSettingGCPanel()),
					new FShowMouseOn()
	        },
			highlightSelector = {
					new FSetTool(eShape.highlight.getAShape().getDrawer()),
					new FSetShape(eShape.highlight.getAShape()),
					new FPaintMasterWithHighLightColor(),
					new FImageNormalPaint("ToolBarImgs/highlight.png"),
					new FShowSelected(),
					new FGCpeekaboo(new HighlightSettingGCPanel()),
					new FShowMouseOn()
	        },
			slideOnFunctions = {
					new FPanelOnOff(new SlidePanel()),
	        }
	;
}
