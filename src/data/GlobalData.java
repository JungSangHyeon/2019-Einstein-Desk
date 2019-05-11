package data;

import tool.Make2PointShapeTool; 
import toolStuff.ATool;
import twoPointShapeStuff.ShapeEnum.e2PShape;

public class GlobalData {
	private static ATool nowTool = new Make2PointShapeTool();
	private static e2PShape nowGC = e2PShape.star4;//버튼에 따라 달라지게 하자. (툴바)
	
	public static ATool getNowTool() {return nowTool;}
	public static e2PShape getNowGC() {return nowGC;}
	
	public static void setNowTool(ATool nowTool) {GlobalData.nowTool = nowTool;}
	public static void setNowGC(e2PShape nowGC) {GlobalData.nowGC = nowGC;}
	
}
