package graphicComponent;

import GCPanel.HighlightSettingGCPanel;
import GCPanel.PenSettingGCPanel;
import GCPanel.ShapeSettingGCPanel;
import fGlobalDataModify.FRedo;
import fGlobalDataModify.FSetShape;
import fGlobalDataModify.FSetTool;
import fGlobalDataModify.FUndo;
import fImagePaint.FImageNormalPaint;
import fPaint.FGCpeekaboo;
import fPaint.FPaintMasterWithHighLightColor;
import fPaint.FPaintMasterWithPenColor;
import fPaint.FShowMouseOn;
import fPaint.FShowSelected;
import fSystem.FKillSystem;
import function_Stuff.eTool;
import imgLoad.ImgLoad;
import zStuff_Function.AFunction;
import zStuff_Shape.eShape;

public class ToolSelectGC {
	public static AFunction[] //add Functions Here
			handToolFunctions = {
					new FSetTool(eTool.eHandTool.getATool()), 
					new FImageNormalPaint("ToolBarImgs/hand.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			eraserToolFunctions = {
					new FSetTool(eTool.eEraserTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/eraser.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			shapeToolFunctions = {
					new FSetTool(eShape.rect.getAShape().getDrawer()),
					new FSetShape(eShape.rect.getAShape()),
					new FImageNormalPaint("ToolBarImgs/shape.png"),
					new FShowSelected(),
					new FGCpeekaboo(new ShapeSettingGCPanel()),
					new FShowMouseOn()
			},
			undoFunctions = {
					new FUndo(),
					new FImageNormalPaint("ToolBarImgs/back.png"),
					new FShowMouseOn()
			},
			redoFunctions = {
					new FRedo(),
					new FImageNormalPaint("ToolBarImgs/front.png"),
					new FShowMouseOn()
			},
			newSlideFunctions = {
					new ImgLoad(),
					new FImageNormalPaint("ToolBarImgs/newSlide.png"),
					new FShowMouseOn()
			},
			imageFunctions = {
					new ImgLoad(),
					new FImageNormalPaint("ToolBarImgs/Image.png"),
					new FShowMouseOn()
			},
			offFunctions = {
					new FKillSystem(),
					new FImageNormalPaint("ToolBarImgs/off1.png"),
					new FShowMouseOn()
			},
			canvasHandFunctions = {
					new FSetTool(eTool.eCanvasTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/Canvas.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			connectFunctions = {
					new FSetTool(eTool.eConnectTool.getATool()),
					new FImageNormalPaint("ToolBarImgs/Connect.png"),
					new FShowSelected(),
					new FShowMouseOn()
			},
			penFunctions = {
					new FSetTool(eShape.pen.getAShape().getDrawer()),
					new FSetShape(eShape.pen.getAShape()),
					new FPaintMasterWithPenColor(),
					new FImageNormalPaint("ToolBarImgs/pen.png"),
					new FShowSelected(),
					new FShowMouseOn(),
					new FGCpeekaboo(new PenSettingGCPanel()),
					new FShowMouseOn()
	        },
			highlightFunctions = {
					new FSetTool(eShape.highlight.getAShape().getDrawer()),
					new FSetShape(eShape.highlight.getAShape()),
					new FPaintMasterWithHighLightColor(),
					new FImageNormalPaint("ToolBarImgs/highlight.png"),
					new FShowSelected(),
					new FShowMouseOn(),
					new FGCpeekaboo(new HighlightSettingGCPanel()),
					new FShowMouseOn()
	        }
	;
}
