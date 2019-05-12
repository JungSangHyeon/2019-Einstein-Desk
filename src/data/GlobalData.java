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
	private static eShape nowShapeMaker = eShape.rect;//btn������ ������ ��ư��� ��������, ������ ���� ���� ���߳�.
	public static eShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(eShape shapeMaker) {GlobalData.nowShapeMaker = shapeMaker;}
}
