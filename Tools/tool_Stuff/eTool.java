package tool_Stuff;

import tool_Creat.CMCShapeDrawTool;
import tool_Creat.HighlightTool;
import tool_Creat.PDRShapeDrawTool;
import tool_Creat.PenTool;
import tool_SelectAndEvent.HandTool;

public enum eTool {
	ePDRShapeDrawTool(new PDRShapeDrawTool()),
	eCMCShapeDrawTool(new CMCShapeDrawTool()),
	eHandTool(new HandTool()),
	ePenDrawTool(new PenTool()),
	eHighlightDrawTool(new HighlightTool()),
	;
	
	private ATool tool;
	private eTool(ATool tool) {this.tool=tool;}
	public ATool getTool() {return this.tool;}
}
