package zStuff_Data;

import canvas.CanvasHandTool;
import zStuff_GraphicComponent.GCStorage_Selected;
import zStuff_Tool.ATool;
import zStuff_Tool.eTool;

public class ToolData {
	private static ATool nowTool = eTool.eHandTool.getATool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {
		ToolData.nowTool = nowTool;
		GCStorage_Selected.clearSelected();
		if(ToolData.nowTool instanceof CanvasHandTool) {CanvasHandTool.init();}
	}
}
