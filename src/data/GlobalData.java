package data;

import java.awt.Color;

import shape_Stuff.AShape;
import shape_Stuff.eShape;
import tool.PDRShapeDrawTool;
import tool_Stuff.ATool;

public class GlobalData {

	//Tool
	private static ATool nowTool = new PDRShapeDrawTool();
	public static ATool getNowTool() {return nowTool;}
	public static void setNowTool(ATool nowTool) {GlobalData.nowTool = nowTool;}
	
	//Shape
	private static AShape nowShapeMaker = eShape.rect.getShape();//btn누르면 쉐잎이 버튼대로 맞춰지고, 쉐입을 통해 툴도 맞추낟.
	public static AShape getNowShapeMaker() {return nowShapeMaker;}
	public static void setNowShapeMaker(AShape shapeMaker) {GlobalData.nowShapeMaker = shapeMaker;}
	

	//Pen
	public static Color penColor = Color.BLACK;
	public static Color getPenColor() {return penColor;}
	public static void setPenColor(Color pc) {penColor = pc;}
	
	public static int penThick = 10;
	public static int getPenThick() {return penThick;}
	public static void setPenThick(int pt) {penThick = pt;}
	
	//HighLighter
	public static Color highlightColor = Color.YELLOW;
	public static Color getHighlightColor() {return highlightColor;}
	public static void setHighlightColor(Color hc) {highlightColor = hc;}
	
	public static int highlightThick = 30;
	public static int getHighlightThick() {return highlightThick;}
	public static void setHighlightThick(int ht) {highlightThick = ht;}
		
	
//	private static eShape nowShapeMaker = eShape.rect;//btn누르면 쉐잎이 버튼대로 맞춰지고, 쉐입을 통해 툴도 맞추낟.
//	public static eShape getNowShapeMaker() {return nowShapeMaker;}
//	public static void setNowShapeMaker(eShape shapeMaker) {GlobalData.nowShapeMaker = shapeMaker;}
}
