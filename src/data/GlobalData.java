package data;

import shape_Stuff.eShape;
import tool.PDRShapeDrawTool;
import tool_Stuff.ATool;

public class GlobalData {

	//Tool
	private static ATool nowTool = new PDRShapeDrawTool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {GlobalData.nowTool = nowTool;}
	
	//Shape
	private static eShape nowShapeMaker = eShape.rect;//btn누르면 쉐잎이 버튼대로 맞춰지고, 쉐입을 통해 툴도 맞추낟.
	public static eShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(eShape shapeMaker) {GlobalData.nowShapeMaker = shapeMaker;}
}
