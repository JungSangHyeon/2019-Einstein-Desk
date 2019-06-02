package zStuff_GraphicComponent;

import graphicComponent.ShapeSelectGC;
import graphicComponent.ToolSelectGC;
import zStuff_Function.AFunction;
import zStuff_Shape.eShape;

public class GCEnum {

	public enum eGC{
		//Shape
		eRect(ShapeSelectGC.rectFunctions),
		eEllipse(ShapeSelectGC.ellipseFunctions),
		eSpeech(ShapeSelectGC.speechFunctions),
		eStarN(ShapeSelectGC.starNFunctions),
		eStar4(ShapeSelectGC.star4Functions),
		eTriangle(ShapeSelectGC.triangleFunctions),
		eStraightLine(ShapeSelectGC.straightLineFunctions),
		ePolygon(ShapeSelectGC.polygonFunctions),
				
		//Tool
		ePen(ToolSelectGC.penFunctions),
		eHighlight(ToolSelectGC.highlightFunctions),
		eEraserTool(ToolSelectGC.eraserToolFunctions),
		eShapeTool(ToolSelectGC.shapeToolFunctions),
		eHandTool(ToolSelectGC.handToolFunctions),
		eCanvasHandTool(ToolSelectGC.canvasHandFunctions),
		eConnectTool(ToolSelectGC.connectFunctions),
		
		eUndo(ToolSelectGC.undoFunctions),
		eRedo(ToolSelectGC.redoFunctions),
		eNewSlide(ToolSelectGC.newSlideFunctions),
		eImage(ToolSelectGC.imageFunctions),
		eSaveImage(ToolSelectGC.saveImageFunctions),
		eOff(ToolSelectGC.offFunctions),
		;
		private AFunction[] functions;
		private eGC(AFunction[] functions) {this.functions=functions;}
		public AFunction[] getFunctions() {return this.functions;}
	}
	
	public static GraphicComponent getGC(eGC item) {
		GraphicComponent GC = new GraphicComponent();
		GC.setAShape(eShape.rect.getAShape());
		GC.setBorderPaint(false);
		GC.setFillPaint(false);
		for(AFunction function : item.getFunctions()) {GC.addFunction(function);}
		return GC;
	}
}
