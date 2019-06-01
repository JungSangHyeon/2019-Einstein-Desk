package component_Stuff;

import component.ShapeSelectItems;
import component.ToolSelectItems;
import fPaint.FShowMouseOn;
import shape_Stuff.eShape;
import zStuff_Function.AFunction;

public class GCEnum {

	public enum eGC{
		//Tool
		ePen(ToolSelectItems.penFunctions),
		eHighlight(ToolSelectItems.highlightFunctions),
		eEraserTool(ToolSelectItems.eraserToolFunctions),
		eShapeTool(ToolSelectItems.shapeToolFunctions),
		eHandTool(ToolSelectItems.handToolFunctions),
		eCanvasHandTool(ToolSelectItems.canvasHandFunctions),
		eConnectTool(ToolSelectItems.connectFunctions),
		
		eUndo(ToolSelectItems.undoFunctions),
		eRedo(ToolSelectItems.redoFunctions),
		eNewSlide(ToolSelectItems.newSlideFunctions),
		eImage(ToolSelectItems.imageFunctions),
		eOff(ToolSelectItems.offFunctions),
		
		//Shape
		eRect(ShapeSelectItems.rectFunctions),
		eEllipse(ShapeSelectItems.ellipseFunctions),
		eSpeech(ShapeSelectItems.speechFunctions),
		eStarN(ShapeSelectItems.starNFunctions),
		eStar4(ShapeSelectItems.star4Functions),
		eTriangle(ShapeSelectItems.triangleFunctions),
		eStraightLine(ShapeSelectItems.straightLineFunctions),
		ePolygon(ShapeSelectItems.polygonFunctions),
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
		GC.addFunction(new FShowMouseOn());
		return GC;
	}
}
