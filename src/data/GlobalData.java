package data;

import shapeMaker_Stuff.ShapeMakerEnum.e2PShapeMaker;
import tool.Make2PointShapeTool;
import tool_Stuff.ATool;

public class GlobalData {

	//Tool
	private static ATool nowTool = new Make2PointShapeTool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {GlobalData.nowTool = nowTool;}
	
	//2P Shape
	private static e2PShapeMaker now2PShape = e2PShapeMaker.rect;
	public static e2PShapeMaker getNow2PShape() {return now2PShape;}
	public static void setNow2PShape(e2PShapeMaker now2PShape) {GlobalData.now2PShape = now2PShape;}
	
}
