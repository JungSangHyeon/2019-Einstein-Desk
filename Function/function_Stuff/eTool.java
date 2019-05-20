package function_Stuff;

import function_Creat.CMCShapeDrawTool;
import function_Creat.HighlightTool;
import function_Creat.PDRShapeDrawTool;
import function_Creat.PenTool;
import function_Kill.eraserTool;
import function_SelectAndEvent.HandTool;

public enum eTool {
	ePDRShapeDrawTool(new PDRShapeDrawTool()),
	eCMCShapeDrawTool(new CMCShapeDrawTool()),
	
	eHandTool(new HandTool()),
	ePenDrawTool(new PenTool()),
	eHighlightDrawTool(new HighlightTool()),
	eEraserTool(new eraserTool()),
	;
	
	private ATool tool;
	private eTool(ATool tool) {this.tool=tool;}
	public ATool getTool() {return this.tool;}
}
