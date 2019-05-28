package data;

import function_SelectAndEvent.CanvasHandTool;
import function_Stuff.ATool;
import function_Stuff.eTool;

public class ToolData {
	private static ATool nowTool = eTool.eHandTool.getATool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {
		ToolData.nowTool = nowTool;
		GCStorage_Selected.clearSelected();
		if(ToolData.nowTool instanceof CanvasHandTool) {CanvasHandTool.init();}
	}
}
