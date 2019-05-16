package tool_Stuff;

import tool.CMCShapeDrawTool;
import tool.HandTool;
import tool.HighlightTool;
import tool.PDRShapeDrawTool;
import tool.PenTool;

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
