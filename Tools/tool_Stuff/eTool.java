package tool_Stuff;

import tool.CMCShapeDrawTool;
import tool.HandTool;
import tool.LineDrawTool;
import tool.PDRShapeDrawTool;

public enum eTool {
	ePDRShapeDrawTool(new PDRShapeDrawTool()),
	eCMCShapeDrawTool(new CMCShapeDrawTool()),
	eHandTool(new HandTool()),
	eLineDrawTool(new LineDrawTool()),
	;
	
	private ATool tool;
	private eTool(ATool tool) {this.tool=tool;}
	public ATool getTool() {return this.tool;}
}
